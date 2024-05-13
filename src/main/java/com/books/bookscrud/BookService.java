//package com.books.bookscrud;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.enterprise.context.RequestScoped;
//import jakarta.inject.Named;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.Query;
//import jakarta.transaction.Transactional;
//import java.util.List;
//
//@Named
//@RequestScoped
//public class BookService {
//    private EntityManagerFactory entityManagerFactory;
//
//    @PostConstruct
//    public void init() {
//        entityManagerFactory = Persistence.createEntityManagerFactory("default");
//    }
//
//    @Transactional
//    public void createBook(Book book) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        try {
//            em.persist(book);
//        } finally {
//            em.close();
//        }
//    }
//
//    public String redirectToUpdatePage(Book book) {
//        return "update?faces-redirect=true";
//    }
//
//    public Book findBook(Long id) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        try {
//            return em.createNamedQuery("findBookById", Book.class)
//                    .setParameter("id", id)
//                    .getSingleResult();
//        } finally {
//            em.close();
//        }
//    }
//
//    public List<Book> findAllBooks() {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        try {
//            return em.createNamedQuery("findAllBooks", Book.class).getResultList();
//        } finally {
//            em.close();
//        }
//    }
//
//    @Transactional
//    public void updateBook(Book book) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        try {
//            Query query = em.createNamedQuery("updateBook")
//                    .setParameter("id", book.getId())
//                    .setParameter("title", book.getTitle())
//                    .setParameter("genre", book.getGenre())
//                    .setParameter("releaseDate", book.getReleaseDate());
//            query.executeUpdate();
//        } finally {
//            em.close();
//        }
//    }
//
//    @Transactional
//    public void deleteBook(Long id) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        try {
//            Query query = em.createNamedQuery("deleteBook").setParameter("id", id);
//            query.executeUpdate();
//        } finally {
//            em.close();
//        }
//    }
//}


package com.books.bookscrud;

import com.books.bookscrud.entities.Book;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped
public class BookService {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    @PersistenceContext
    private EntityManager em;
    private Book book;

    @PostConstruct
    public void init() {
        book = new Book();
    }
    @Transactional
    public void createBook(Book book) {
        em = entityManagerFactory.createEntityManager();
        em.persist(book);

    }

    public String redirectToUpdatePage(Book book) {
        this.book = book;
        return "update?faces-redirect=true";
    }


    public List<Book> findBooksReleasedAfterYear(int year) {
        em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT m FROM Book m WHERE m.releaseYear > :year", Book.class)
                .setParameter("year", year)
                .getResultList();
    }

    public List<Book> findAllBooks() {
        em = entityManagerFactory.createEntityManager();
        return em.createNamedQuery("findAllBooks", Book.class).getResultList();
    }
    @Transactional
    public void updateBook(Book book) {
        em = entityManagerFactory.createEntityManager();

        Query query = em.createNamedQuery("updateBook")
                .setParameter("id", book.getId())
                .setParameter("title", book.getTitle())
                .setParameter("genre", book.getGenre())
                .setParameter("releaseYear", book.getReleaseYear());
        em.joinTransaction();
        query.executeUpdate();

    }
    @Transactional
    public void deleteBook(Long id) {
        em = entityManagerFactory.createEntityManager();

        Query query = em.createNamedQuery("deleteBook").setParameter("id", id);
        em.joinTransaction();
        query.executeUpdate();

    }

    @Transactional
    public Book findBookById(Long id) {
        em = entityManagerFactory.createEntityManager();
        return em.createNamedQuery("findBookById", Book.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

