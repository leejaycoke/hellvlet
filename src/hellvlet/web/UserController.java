package hellvlet.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher view = getServletContext().getRequestDispatcher("/user/register.jsp");
//        RequestDispatcher view = request.getRequestDispatcher("register.jsp");
        view.forward(request, response);
    }
}
