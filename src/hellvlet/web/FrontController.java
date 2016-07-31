package hellvlet.web;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class FrontController extends HttpServlet {

    private final Map<String, Class> mControllers;

    public FrontController() {
        mControllers = (Map<String, Class>) getServletContext().getAttribute("injectedControllers");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedPath = req.getServletPath();
    }

}
