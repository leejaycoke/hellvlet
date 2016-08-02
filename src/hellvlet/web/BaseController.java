package hellvlet.web;

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

//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        routeMethod("Get", request, response);
//    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        routeMethod("Post", request, response);
//    }

//    private void routeMethod(String httpMethod, HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        String path = request.getServletPath();
//        System.out.printf("requestedPath: %s\n", path);
//
//        String methodName = parsePathToCarmelCase(path) + httpMethod;
//
//        System.out.printf("methodName: %s\n", methodName);
//
//        final Class[] args = new Class[2];
//        args[0] = HttpServletRequest.class;
//        args[1] = HttpServletResponse.class;
//
//        try {
//            Method method = getClass().getMethod(methodName, args);
//            method.invoke(this, request, response);
//        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }

    void render(String s, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher view = getServletContext().getRequestDispatcher(s);
        view.forward(request, response);
    }

//    private String parsePathToCarmelCase(String path) {
//        ArrayList<String> paths = new ArrayList<>(Arrays.asList(path.split("/")));
//        paths.removeIf(item -> item.trim().equals(""));
//
//        String first = paths.get(0).toLowerCase();
//
//        if (paths.size() == 1) {
//            return first;
//        }
//
//        StringBuilder s = new StringBuilder();
//
//        paths.subList(1, paths.size()).forEach(item -> {
//            s.append(item.substring(0, 1).toUpperCase());
//            s.append(item.substring(1, item.length()).toLowerCase());
//        });
//
//        return first + s.toString();
//    }

}
