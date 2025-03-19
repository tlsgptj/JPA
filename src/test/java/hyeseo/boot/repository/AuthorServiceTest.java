package hyeseo.boot.repository;

import hyeseo.boot.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import hyeseo.boot.domain.Author;
import hyeseo.boot.domain.Book;
import java.util.List;

@SpringBootTest
class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;

    @Test
    void insert() {
        Author author = new Author();
        author.setName("희진");
        Book book1 = new Book();
        book1.setTitle("해결");
        Book book2 = new Book();
        book2.setTitle("사");
        author.addBook(book1);
        author.addBook(book2);

        authorService.insert(author);
    }

    @Test
    void getAuthors() {
        List<Author> authors = authorService.getAuthors();
    }

    @Test
    void getAuthor() {
        long auhtorId = 3L;
        Author author = authorService.getAuthor(auhtorId);

        List<Book> books = author.getBooks();
        pln("<작가이름:"+author.getName() + "(작가ID: "+author.getId()+")의 책들>");
        for(Book book : books) {
            pln( "- 제목: " + book.getTitle() + ", 책ID: " + book.getId());
        }
    }

    @Test
    void update() {
        Long authorId = 3L;
        String newName = "홍감찬";
        Author author = authorService.update(authorId, newName);
    }

    @Test
    void delete() {
        Long authorId = 3L;
        authorService.delete(authorId);
    }

    void pln(String str){
        System.out.println(str);
    }
}