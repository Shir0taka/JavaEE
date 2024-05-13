package com.books.bookscrud.servlets;

import com.books.bookscrud.entities.Sequence;
import com.books.bookscrud.SequenceFacade;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/SequenceServlet")
public class SequenceServlet extends HttpServlet {

    @Inject
    private SequenceFacade sequenceFacade;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Sequence sequence = new Sequence();
        sequence.setTitle("Book4");
        sequence.setGenre("Thriller");

        sequenceFacade.create(sequence);

        response.getWriter().println("Book added to the database.");

        List<Sequence> sequences = sequenceFacade.findAll();
        response.getWriter().println("All Books in the Library:");
        for (Sequence s : sequences) {
            response.getWriter().println(s.getTitle() + " - Genre: " + s.getGenre());
        }
    }
}
