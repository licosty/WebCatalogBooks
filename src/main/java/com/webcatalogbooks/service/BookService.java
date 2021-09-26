package com.webcatalogbooks.service;

import com.webcatalogbooks.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BookService {
        List<Book> getAllBooks(Specification<Book> specification, Sort sort);

        Book createBook(Book book);

        Book updateBook(Long id, Book book);

        void deleteBook(Long id);

        Book findByID(Long id);

        Specification<Book> findByAuthor(String author);

        Specification<Book> findByTitle(String title);

        Specification<Book> findByYear(Integer after, Integer before);

        Specification<Book> findByPublisher(String publisher);

        Specification<Book> findByISBN(String isbn);

        Specification<Book> findByPages(Integer pagesMin, Integer pagesMax);
}
