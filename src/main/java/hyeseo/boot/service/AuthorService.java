package hyeseo.boot.service;

import hyeseo.boot.domain.Author;
import java.util.List;

public interface AuthorService {
    Author insert(Author author); //C
    List<Author> getAuthors(); //R
    Author getAuthor(Long authorId); //R
    Author update(Long authorId, String newName); //U
    void delete(Long authorId); //D
}