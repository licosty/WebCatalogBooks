package com.webcatalogbooks.service;

import com.webcatalogbooks.entity.Book;
import com.webcatalogbooks.exception.BadRequestException;
import com.webcatalogbooks.exception.NotFoundException;
import com.webcatalogbooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {
    private BookRepository repository;

    @Autowired
    public void setRepository(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAllBooks(Specification<Book> specification, Sort sort) {
        return repository.findAll(specification, sort);
    }

    @Override
    public Book createBook(Book book) {
        if (!isValidBook(book)) throw new BadRequestException("Required data not available");

        return repository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book updatedBook = findByID(id);
        if (book.getAuthor() != null) updatedBook.setAuthor(book.getAuthor());
        if (book.getTitle() != null) updatedBook.setTitle(book.getTitle());
        if (book.getYear() != null) updatedBook.setYear(book.getYear());
        if (book.getPublisher() != null) updatedBook.setPublisher(book.getPublisher());
        if (book.getIsbn() != null) updatedBook.setIsbn(book.getIsbn());
        if (book.getPages() != null) updatedBook.setIsbn(book.getIsbn());

        return repository.save(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        findByID(id);
        repository.deleteById(id);
    }

    @Override
    public Book findByID(Long id) {
        if (id < 0) throw new BadRequestException("Incorrect ID");
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Book is not found"));
    }

    private boolean isValidBook(Book book) {
        return book != null &&
                book.getAuthor() != null && !book.getAuthor().isEmpty() &&
                book.getTitle() != null && !book.getTitle().isEmpty() &&
                book.getYear() != null && book.getPublisher() != null;
    }

    /*--------------------------------- Filters-block ---------------------------------*/
    @Override
    public Specification<Book> findByAuthor(String author) {
        return (root, criteriaQuery, criteriaBuilder) ->
                author == null ? null : criteriaBuilder.like(root.get("author"), "%" + author + "%");
    }

    @Override
    public Specification<Book> findByTitle(String title) {
        return (root, criteriaQuery, criteriaBuilder) ->
                title == null ? null : criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    @Override
    public Specification<Book> findByYear(Integer after, Integer before) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (after == null && before == null)
                return null;
            else if (after == null)
                return criteriaBuilder.lessThanOrEqualTo(root.get("year"), before);
            else if (before == null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get("year"), after);
            else
                return criteriaBuilder.between(root.get("year"), after, before);
        };
    }

    @Override
    public Specification<Book> findByPublisher(String publisher) {
        return (root, criteriaQuery, criteriaBuilder) ->
                publisher == null ? null : criteriaBuilder.like(root.get("publisher"), "%" + publisher + "%");
    }

    @Override
    public Specification<Book> findByISBN(String isbn) {
        return (root, criteriaQuery, criteriaBuilder) ->
                isbn == null ? null : criteriaBuilder.like(root.get("isbn"), "%" + isbn + "%");
    }

    @Override
    public Specification<Book> findByPages(Integer pagesMin, Integer pagesMax) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (pagesMin == null && pagesMax == null)
                return null;
            else if (pagesMin == null)
                return criteriaBuilder.lessThanOrEqualTo(root.get("year"), pagesMax);
            else if (pagesMax == null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get("year"), pagesMin);
            else
                return criteriaBuilder.between(root.get("pages"), pagesMin, pagesMax);
        };
    }

    /*------------------------------- Filters-block end -------------------------------*/
}
