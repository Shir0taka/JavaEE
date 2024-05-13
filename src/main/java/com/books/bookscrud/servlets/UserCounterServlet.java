package com.books.bookscrud.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserCounterServlet")
public class UserCounterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);

        Integer count = (Integer) session.getAttribute("userCount");
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        session.setAttribute("userCount", count);

        response.getWriter().println("User count: " + count);
    }
}
