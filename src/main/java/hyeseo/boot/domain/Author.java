package hyeseo.boot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author { //Author: Book = 1:N 관계가 있는거임
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //나중에 구현
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) { //연관 관계 메소드
        books.add(book);
        book.setAuthor(this);
    }
}
