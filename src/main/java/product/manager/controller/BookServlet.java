package product.manager.controller;

import product.manager.model.Book;
import product.manager.model.Category;
import product.manager.service.book.BookService;
import product.manager.service.category.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/books")
public class BookServlet extends HttpServlet {
    private BookService bookService;
    private CategoryService categoryService;

    @Override
    public void init(){
        bookService = new BookService();
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreatForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteBook(request, response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/edit.jsp");
        request.setAttribute("categoryList", categoryService.findAll());
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void editBook(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/edit.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String idS = request.getParameter("category");
        int idCate = Integer.parseInt(idS);
        Category category = categoryService.findById(idCate);
        Book book = new Book(id, name, description, category);
        request.setAttribute("book", book);
        request.setAttribute("message", "Edit success!");
        try {
            bookService.update(book);
            dispatcher.forward(request, response);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }


    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            bookService.delete(id);
            try {
                response.sendRedirect("/books");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) {
        // ĐIỀU HƯỚNG ĐẾN book/create.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/create.jsp");
        request.setAttribute("categoryList", categoryService.findAll());
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void creatBook(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/create.jsp");
        try {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String id = request.getParameter("category");
        int idC = Integer.parseInt(id);
        Category category = categoryService.findById(idC);
        Book book = new Book(name, description, category);
        bookService.insert(book);
        request.setAttribute("categoryList", categoryService.findAll());
        request.setAttribute("message","Create success!");
        dispatcher.forward(request, response);
        } catch (ServletException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response) {
        List<Book> bookList;
        bookList = bookService.findAll();
        // ĐIỀU HƯỚNG ĐẾN book/list.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/list.jsp");
        // GÁN TÊN CHO OBJECT ĐỂ SỬ DỤNG ĐC TRONG VIEW (JSP)
        request.setAttribute("bookList", bookList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                creatBook(request, response);
                break;
            case "edit":
                editBook(request, response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }
}
