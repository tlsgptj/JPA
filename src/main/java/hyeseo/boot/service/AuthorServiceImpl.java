package hyeseo.boot.service;

import hyeseo.boot.domain.Author;
import hyeseo.boot.domain.Book;
import hyeseo.boot.repository.AuthorRepository;
import hyeseo.boot.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author insert(Author author) {
        return null;
    }

    @Override
    public List<Author> getAuthors() {
        return List.of();
    }

    @Override
    public Optional<Author> getAuthor(Long authorId) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> update(Long authorId, String newName) {
        return Optional.empty();
    }

    @Override
    public void delete(Long authorId) {

    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
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
        return authorRepository.findByIdWithBooks(id);
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
        return authorRepository.searchByName(keyword, pageable);
    }

    @Override
    @Transactional
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Author addBookToAuthor(Long authorId, Book book) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + authorId));

        author.addBook(book);
        return authorRepository.save(author);
    }
}