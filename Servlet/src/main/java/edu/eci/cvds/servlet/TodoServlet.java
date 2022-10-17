package edu.eci.cvds.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.MalformedURLException;
import java.io.FileNotFoundException;

import edu.eci.cvds.servlet.model.Todo;

@WebServlet(urlPatterns = "/Todo")
public class TodoServlet extends HttpServlet {
    static final long serialVersionUID = 35L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            Writer responseWriter = resp.getWriter();
            Optional<String> optID = Optional.ofNullable(req.getParameter("id"));
            String id = optID.isPresent() && !optID.get().isEmpty() ? optID.get() : "";
            Todo todo = Service.getTodo(Integer.parseInt(id));
            resp.setStatus(HttpServletResponse.SC_OK);
            ArrayList<Todo> todos= new ArrayList<Todo>();
            todos.add(todo);
            responseWriter.write(Service.todosToHTMLTable(todos));
            responseWriter.flush();
        } catch (NumberFormatException nex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (MalformedURLException mex) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (FileNotFoundException fex) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        try {
            Writer responseWriter = resp.getWriter();
            Optional<String> optID = Optional.ofNullable(req.getParameter("id"));
            String id = optID.isPresent() && !optID.get().isEmpty() ? optID.get() : "";
            Todo todo = Service.getTodo(Integer.parseInt(id));
            resp.setStatus(HttpServletResponse.SC_OK);
            ArrayList<Todo> todos= new ArrayList<Todo>();
            todos.add(todo);
            responseWriter.write(Service.todosToHTMLTable(todos));
            responseWriter.flush();
        } catch (NumberFormatException nex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (MalformedURLException mex) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (FileNotFoundException fex) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
