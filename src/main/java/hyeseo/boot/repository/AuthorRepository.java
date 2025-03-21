package hyeseo.boot.repository;

import hyeseo.boot.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);

    // 이름으로 검색 + 페이징
    Page<Author> findByNameContaining(String name, Pageable pageable);

    // 특정 검색어로 이름 검색 + 페이징
    @Query("SELECT a FROM Author a WHERE a.name LIKE %:keyword%")
    Page<Author> searchByName(@Param("keyword") String keyword, Pageable pageable);

    // 기본 페이징
    Page<Author> findAll(Pageable pageable);

    // 저자 상세 조회 시 도서 정보 함께 로딩
    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.id = :id")
    Optional<Author> findByIdWithBooks(@Param("id") Long id);
}