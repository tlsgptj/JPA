package hyeseo.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hyeseo.boot.domain.Author;
import hyeseo.boot.domain.Book;
import java.util.List;

public interface SpringDataJpaMariaBookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(Author author);
}