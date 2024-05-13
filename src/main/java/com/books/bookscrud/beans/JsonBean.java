package com.books.bookscrud.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.books.bookscrud.controllers.JsonController;

@Named
@RequestScoped
public class JsonBean {

    @Inject
    private JsonController jsonController;

    private String bookJson;

    public String getBookJson() {
        return bookJson;
    }

    public void loadBooks() {
        jsonController.buildBooks();
        bookJson = jsonController.getBookJson();
    }
}
