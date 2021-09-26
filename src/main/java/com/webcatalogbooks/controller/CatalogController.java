package com.webcatalogbooks.controller;

import com.webcatalogbooks.entity.Book;
import com.webcatalogbooks.service.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class CatalogController {
    private BookServiceImp service;

    @Autowired
    public void setService(BookServiceImp service) {
        this.service = service;
    }

    @GetMapping(value = "/books")
    public List<Book> getAllBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer afterYear,
            @RequestParam(required = false) Integer beforeYear,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false, defaultValue = "n/a") String isbn,
            @RequestParam(required = false) Integer pagesMin,
            @RequestParam(required = false) Integer pagesMax,
            @RequestParam(required = false, defaultValue = "ID") BookOrder order
            ) {

        Specification<Book> specification = Specification
                .where(service.findByAuthor(author)
                .and(service.findByTitle(title)))
                .and(service.findByYear(afterYear, beforeYear))
                .and(service.findByPublisher(publisher))
                .and(service.findByISBN(isbn))
                .and(service.findByPages(pagesMin, pagesMax));

        return service.getAllBooks(specification, Sort.by(order.getFieldName()));
    }

    @PostMapping(value = "/books")
    public Book createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    @PostMapping(value = "/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    @DeleteMapping(value = "/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }
}
