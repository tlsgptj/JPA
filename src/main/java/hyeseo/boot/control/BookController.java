package hyeseo.boot.control;

import hyeseo.boot.domain.Book;
import hyeseo.boot.dto.*;
import hyeseo.boot.service.AuthorService;
import hyeseo.boot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @Qualifier("authorServiceImpl")
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.findAllBooksWithAuthor();
        List<BookDTO> bookDTOs = books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/paging")
    public ResponseEntity<PageResponseDTO<BookDTO>> getBooksPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Book> bookPage = bookService.findAllBooksWithAuthor(pageable);

        List<BookDTO> bookDTOs = bookPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(PageResponseDTO.of(bookPage, bookDTOs));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponseDTO<BookDTO>> searchBooks(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Book> bookPage = bookService.searchBooks(keyword, pageable);

        List<BookDTO> bookDTOs = bookPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(PageResponseDTO.of(bookPage, bookDTOs));
    }

    @PostMapping("/search")
    public ResponseEntity<PageResponseDTO<BookDTO>> searchBooksPost(@RequestBody SearchDTO searchDTO) {
        Sort sort = searchDTO.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(searchDTO.getSortBy()).ascending()
                : Sort.by(searchDTO.getSortBy()).descending();

        Pageable pageable = PageRequest.of(
                searchDTO.getPage(),
                searchDTO.getSize(),
                sort);

        Page<Book> bookPage = bookService.searchBooks(searchDTO.getKeyword(), pageable);

        List<BookDTO> bookDTOs = bookPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(PageResponseDTO.of(bookPage, bookDTOs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<PageResponseDTO<BookDTO>> getBooksByAuthorId(
            @PathVariable Long authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookService.findByAuthorId(authorId, pageable);

        List<BookDTO> bookDTOs = bookPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(PageResponseDTO.of(bookPage, bookDTOs));
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookCreateDTO bookCreateDTO) {
        return authorService.findById(bookCreateDTO.getAuthorId())
                .map(author -> {
                    Book book = new Book();
                    book.setTitle(bookCreateDTO.getTitle());
                    author.addBook(book);
                    Book savedBook = bookService.saveBook(book);
                    return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedBook));
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookCreateDTO bookCreateDTO) {
        return bookService.findById(id)
                .map(book -> {
                    book.setTitle(bookCreateDTO.getTitle());

                    if (!book.getAuthor().getId().equals(bookCreateDTO.getAuthorId())) {
                        return authorService.findById(bookCreateDTO.getAuthorId())
                                .map(newAuthor -> {
                                    book.setAuthor(newAuthor);
                                    Book updatedBook = bookService.saveBook(book);
                                    return ResponseEntity.ok(convertToDTO(updatedBook));
                                })
                                .orElse(ResponseEntity.badRequest().build());
                    }

                    Book updatedBook = bookService.saveBook(book);
                    return ResponseEntity.ok(convertToDTO(updatedBook));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());

        if (book.getAuthor() != null) {
            dto.setAuthorId(book.getAuthor().getId());
            dto.setAuthorName(book.getAuthor().getName());
        }

        return dto;
    }
}