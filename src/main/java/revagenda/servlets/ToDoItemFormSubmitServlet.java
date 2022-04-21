package revagenda.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet is built to accept posted ToDoItem data sent in query parameters by an HTML form action
 * This was necessary because we had already stubbed out a RESTful JSON body version of this in the ToDoItemServlet doPost()
 * So, rather than re-write that temporarily to showcase this, we just added another endpoint that will be outmoded very soon.
 */
public class ToDoItemFormSubmitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request URI: " + req.getRequestURI());
        System.out.println("Form data - message: " + req.getParameter("task"));
        System.out.println("Form data - duedate: " + req.getParameter("date"));
    }
}
