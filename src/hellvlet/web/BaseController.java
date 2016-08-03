package hellvlet.web;

import hellvlet.annotation.Router;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class BaseController extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getServletPath();
        String controllerName = parseController(path);
        String routerName = parseRouter(path);

        System.out.printf("requested path: %s\n", path);
        System.out.printf("requested controller: %s\n", controllerName);
        System.out.printf("requested router: %s\n", routerName);

        for (Method method : getClass().getMethods()) {
            if (!method.isAnnotationPresent(Router.class)) {
                continue;
            }

            Router router = method.getAnnotation(Router.class);

            if (!router.path().equals(routerName)) {
                continue;
            }

            if (!router.method().name().equals(req.getMethod())) {
                continue;
            }

            try {
                method.invoke(this, req, resp);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    void render(String s, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher view = getServletContext().getRequestDispatcher(s);
        view.forward(request, response);
    }

    private String parseController(String path) {
        String[] uris = path.split("/");
        return uris.length > 1 ? "/" + uris[1] : "/";
    }

    private String parseRouter(String path) {
        String controller = parseController(path);
        return path.replace(controller, "");
    }

}
