package hyeseo.boot.repository;

import hyeseo.boot.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);

    // 기본 페이징
    Page<Book> findAll(Pageable pageable);

    // 저자 ID로 페이징
    Page<Book> findByAuthorId(Long authorId);

    // 제목 검색 + 페이징
    Page<Book> findByTitleContaining(String title, Pageable pageable);

    // 제목 또는 저자 이름으로 검색 + 페이징
    @Query("SELECT b FROM Book b JOIN b.author a WHERE b.title LIKE %:keyword% OR a.name LIKE %:keyword%")
    Page<Book> searchByTitleOrAuthorName(@Param("keyword") String keyword, Pageable pageable);

    // 모든 도서 및 저자 정보 함께 조회 + 페이징
    @Query(value = "SELECT b FROM Book b JOIN FETCH b.author",
            countQuery = "SELECT COUNT(b) FROM Book b")
    Page<Book> findAllWithAuthor();
}