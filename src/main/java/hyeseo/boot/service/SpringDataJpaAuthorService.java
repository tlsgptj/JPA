package hyeseo.boot.service;

import hyeseo.boot.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hyeseo.boot.domain.Author;
import hyeseo.boot.repository.SpringDataJpaMariaAuthorRepository;
import hyeseo.boot.repository.SpringDataJpaMariaBookRepository;
import java.util.List;
import java.util.Optional;

@Service
public class SpringDataJpaAuthorService implements AuthorService {
    @Autowired
    private SpringDataJpaMariaAuthorRepository authorRepository;
    @Autowired
    private SpringDataJpaMariaBookRepository bookRepository;

    @Override
    public Author insert(Author author) {
        author = authorRepository.save(author);
        pln("#SpringDataJpaAuthorService insert() author: " + author);
        return author;
    }
    @Override
    public List<Author> getAuthors() {
        List<Author> list = authorRepository.findAll();
        pln("#SpringDataJpaAuthorService getAuthors() list: " + list);
        return list;
    }
    @Override
    public Author getAuthor(Long authorId) {
        Author author = authorRepository.findByIdWithBooks(authorId);
        pln("#SpringDataJpaAuthorService getAuthor() author: " + author);
        List<Book> books = author.getBooks();
        pln(author.getName() + authorId);
        for(Book book : books) {
            pln("제목" + book.getTitle() + "책 번호" + book.getId());
        }
        return author;
    }
    /* 방법1
    @Override
    public Author update(Long authorId, String newName) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(optionalAuthor.isPresent()){
            Author author = optionalAuthor.get();
            author.setName(newName);
            author = authorRepository.save(author);
            pln("#SpringDataJpaAuthorService update()-1 author: " + author);
            return author;
        }else{
            return null;
        }
    }
    */
    @Override
    public Author update(Long authorId, String newName) { // 방법2
        Author author = authorRepository.findByIdWithBooks(authorId);
        author.setName(newName);
        author = authorRepository.save(author);
        pln("#SpringDataJpaAuthorService update()-2 author: " + author);
        return author;
    }
    @Override
    public void delete(Long authorId) {
        pln("#SpringDataJpaAuthorService delete()");
        authorRepository.deleteById(authorId);
    }

    void pln(String str){
        System.out.println(str);
    }
}