package com.books.bookscrud.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;

import java.util.List;

import com.books.bookscrud.entities.Book;
import com.books.bookscrud.BookFacade;

@Named
@RequestScoped
public class JsonController {

    @Inject
    private BookFacade bookFacade;

    private String bookJson;

    public String getBookJson() {
        return bookJson;
    }

    public void buildBooks() {
        List<Book> books = bookFacade.findAll();

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Book book : books) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                    .add("id", book.getId())
                    .add("title", book.getTitle())
                    .add("genre", book.getGenre())
                    .add("releaseYear", book.getReleaseYear());

            arrayBuilder.add(objectBuilder);
        }

        JsonArray jsonArray = arrayBuilder.build();
        bookJson = jsonArray.toString();
    }
}
