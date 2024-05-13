package com.books.bookscrud;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import com.books.bookscrud.entities.Book;

@Stateless
public class BookFacade {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public void create(Book book) {
        entityManager.persist(book);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void remove(Long bookId) {
        Book book = entityManager.find(Book.class, bookId);
        if (book != null) {
            entityManager.remove(book);
        }
    }

    public Book find(Long bookId) {
        return entityManager.find(Book.class, bookId);
    }

    public List<Book> findAll() {
        return entityManager.createQuery("SELECT m FROM Book m", Book.class).getResultList();
    }
}