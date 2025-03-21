package hyeseo.boot.service;

import hyeseo.boot.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBooks();

    // 페이징
    Page<Book> findAllBooks(Pageable pageable);

    // 페이징 + 연관 엔티티 함께 로딩
    Page<Book> findAllBooksWithAuthor(Pageable pageable);

    List<Book> findAllBooksWithAuthor();

    Optional<Book> findById(Long id);

    List<Book> findByTitle(String title);

    // 페이징 + 제목 검색
    Page<Book> findByTitleContaining(String title, Pageable pageable);

    // 페이징 + 키워드 검색(제목 또는 저자)
    Page<Book> searchBooks(String keyword, Pageable pageable);

    List<Book> findByAuthorId(Long authorId);

    // 페이징 + 저자별 도서
    Page<Book> findByAuthorId(Long authorId, Pageable pageable);

    Book saveBook(Book book);

    void deleteBook(Long id);
}