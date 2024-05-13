package com.books.bookscrud.controllers;

import com.books.bookscrud.entities.Book;
import com.books.bookscrud.BookService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.NoResultException;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BookController implements Serializable {
    @Inject
    private BookService bookService;
    private Book book;
    private List<Book> allBooks;

    @PostConstruct
    public void init() {
        book = new Book();
        refreshBookList();
    }

    public void createBook() {
        bookService.createBook(book);
        refreshBookList();
        book = new Book();
    }

    public String updateBook() {
        bookService.updateBook(book);
        refreshBookList();
        return "books?faces-redirect=true";
    }

    public String redirectToUpdatePage(Book book) {
        this.book = book;
        return "update?faces-redirect=true";
    }

    public void deleteBook(Long id) {
        bookService.deleteBook(id);
        refreshBookList();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void refreshBookList() {
        allBooks = bookService.findAllBooks();
    }

    public String navigateToError() {
        return "error?faces-redirect=true";
    }

    public String navigateToHome() {
        return "index?faces-redirect=true";
    }

    public String findAndDisplayBook() {
        try {
            book = bookService.findBookById(book.getId());
            return "successOutcome";
        } catch (NoResultException e) {
            navigateToError();
            return "errorOutcome";
        }
    }



    public void findBooksReleasedAfterYear(int year) {
        allBooks = bookService.findBooksReleasedAfterYear(year);
    }
}


