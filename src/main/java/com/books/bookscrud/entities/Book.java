package com.books.bookscrud.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sequence")
@NamedQueries({
        @NamedQuery(name = "findAllBooks", query = "SELECT m FROM Book m"),
        @NamedQuery(name = "findBookById", query = "SELECT m FROM Book m WHERE m.id = :id"),
        @NamedQuery(name = "updateBook", query = "UPDATE Book m SET m.title = :title, m.genre = :genre, m.releaseYear = :releaseYear WHERE m.id = :id"),
        @NamedQuery(name = "deleteBook", query = "DELETE FROM Book m WHERE m.id = :id")
})

public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "release_year")
    private Integer releaseYear;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getReleaseYear() {
        return releaseYear != null ? releaseYear : 0;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
