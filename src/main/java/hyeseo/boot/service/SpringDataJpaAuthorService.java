package hyeseo.boot.service;

import hyeseo.boot.domain.Author;
import hyeseo.boot.domain.Book;
import hyeseo.boot.repository.SpringDataJpaMariaAuthorRepository;
import hyeseo.boot.repository.SpringDataJpaMariaBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpringDataJpaAuthorService implements AuthorService {

    private final SpringDataJpaMariaAuthorRepository authorRepository;
    private final SpringDataJpaMariaBookRepository bookRepository;

    @Autowired
    public SpringDataJpaAuthorService(SpringDataJpaMariaAuthorRepository authorRepository, SpringDataJpaMariaBookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Author insert(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthor(Long authorId) {
        return Optional.ofNullable(authorRepository.findByIdWithBooks(authorId));
    }

    @Override
    public Optional<Author> update(Long authorId, String newName) {
        return authorRepository.findById(authorId).map(author -> {
            author.setName(newName);
            return authorRepository.save(author);
        });
    }

    @Override
    public void delete(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public List<Author> findAllAuthors() {
        return List.of();
    }

    @Override
    public Page<Author> findAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByIdWithBooks(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Page<Author> findByNameContaining(String name, Pageable pageable) {
        return authorRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<Author> searchAuthors(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public Author saveAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {

    }

    @Override
    public Author addBookToAuthor(Long authorId, Book book) {
        return null;
    }

}
