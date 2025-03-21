package hyeseo.boot.control;

import hyeseo.boot.domain.Author;
import hyeseo.boot.domain.Book;
import hyeseo.boot.dto.*;
import hyeseo.boot.service.AuthorService;
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
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    @Qualifier("authorServiceImpl")
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorService.findAllAuthors();
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/paging")
    public ResponseEntity<PageResponseDTO<AuthorDTO>> getAuthorsPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Author> authorPage = authorService.findAllAuthors(pageable);

        List<AuthorDTO> authorDTOs = authorPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(PageResponseDTO.of(authorPage, authorDTOs));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponseDTO<AuthorDTO>> searchAuthors(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Author> authorPage = authorService.searchAuthors(keyword, pageable);

        List<AuthorDTO> authorDTOs = authorPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(PageResponseDTO.of(authorPage, authorDTOs));
    }

    @PostMapping("/search")
    public ResponseEntity<PageResponseDTO<AuthorDTO>> searchAuthorsPost(@RequestBody SearchDTO searchDTO) {
        Sort sort = searchDTO.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(searchDTO.getSortBy()).ascending()
                : Sort.by(searchDTO.getSortBy()).descending();

        Pageable pageable = PageRequest.of(
                searchDTO.getPage(),
                searchDTO.getSize(),
                sort);

        Page<Author> authorPage = authorService.searchAuthors(searchDTO.getKeyword(), pageable);

        List<AuthorDTO> authorDTOs = authorPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(PageResponseDTO.of(authorPage, authorDTOs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return authorService.findByIdWithBooks(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorCreateDTO authorCreateDTO) {
        Author author = new Author();
        author.setName(authorCreateDTO.getName());

        Author savedAuthor = authorService.saveAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedAuthor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorCreateDTO authorCreateDTO) {
        return authorService.findById(id)
                .map(author -> {
                    author.setName(authorCreateDTO.getName());
                    Author updatedAuthor = authorService.saveAuthor(author);
                    return ResponseEntity.ok(convertToDTO(updatedAuthor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (authorService.findById(id).isPresent()) {
            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private AuthorDTO convertToDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());

        if (author.getBooks() != null && !author.getBooks().isEmpty()) {
            List<BookDTO> bookDTOs = author.getBooks().stream()
                    .map(this::convertBookToDTO)
                    .collect(Collectors.toList());
            dto.setBooks(bookDTOs);
        }

        return dto;
    }

    private BookDTO convertBookToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setAuthorName(book.getAuthor().getName());
        return dto;
    }
}