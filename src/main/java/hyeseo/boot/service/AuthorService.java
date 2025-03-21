package hyeseo.boot.service;

import hyeseo.boot.domain.Author;
import hyeseo.boot.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAllAuthors();

    // 페이징
    Page<Author> findAllAuthors(Pageable pageable);

    Optional<Author> findById(Long id);

    Optional<Author> findByIdWithBooks(Long id);

    Optional<Author> findByName(String name);

    // 페이징 + 이름 검색
    Page<Author> findByNameContaining(String name, Pageable pageable);

    // 페이징 + 키워드 검색
    Page<Author> searchAuthors(String keyword, Pageable pageable);

    Author saveAuthor(Author author);

    void deleteAuthor(Long id);

    Author addBookToAuthor(Long authorId, Book book);
}