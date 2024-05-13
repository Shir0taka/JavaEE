package com.books.bookscrud.beans;

import com.books.bookscrud.BookService;
import com.books.bookscrud.entities.Book;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/book")
public class BookResource {

    @Inject
    private BookService bookService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("id") Long id) {
        return bookService.findBookById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addBook(Book book) {
        bookService.createBook(book);
        return "{\"message\":\"Book added successfully\"}";
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delBook(@PathParam("id") Long id) {
        bookService.deleteBook(id);
        return "{\"message\":\"Book deleted successfully\"}";
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(
            @PathParam("id") Long id,
            Book updatedBook) {

        Book existingBook = bookService.findBookById(id);

        if (existingBook != null) {
            if (updatedBook.getTitle() != null) {
                existingBook.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getGenre() != null) {
                existingBook.setGenre(updatedBook.getGenre());
            }
            if (updatedBook.getReleaseYear() != null) {
                existingBook.setReleaseYear(updatedBook.getReleaseYear());
            }

            bookService.updateBook(existingBook);
            return Response.ok("{\"message\":\"Book updated successfully\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"Book not found\"}").build();
        }
    }
}


