package hyeseo.boot.service;

import hyeseo.boot.domain.Book;
import hyeseo.boot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findAllBooksWithAuthor(Pageable pageable) {
        return bookRepository.findAllWithAuthor();
    }

    @Override
    public List<Book> findAllBooksWithAuthor() {
        return (List<Book>) bookRepository.findAllWithAuthor();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Page<Book> findByTitleContaining(String title, Pageable pageable) {
        return bookRepository.findByTitleContaining(title, pageable);
    }

    @Override
    public Page<Book> searchBooks(String keyword, Pageable pageable) {
        return bookRepository.searchByTitleOrAuthorName(keyword, pageable);
    }

    @Override
    public List<Book> findByAuthorId(Long authorId) {
        return (List<Book>) bookRepository.findByAuthorId(authorId);
    }

    @Override
    public Page<Book> findByAuthorId(Long authorId, Pageable pageable) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}