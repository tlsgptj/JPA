package hyeseo.boot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import hyeseo.boot.domain.Author;

public interface SpringDataJpaMariaAuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.id = :id")
    Author findByIdWithBooks(Long id);
}