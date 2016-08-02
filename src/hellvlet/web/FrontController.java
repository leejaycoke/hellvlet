package hellvlet.web;


import hellvlet.annotation.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class FrontController extends HttpServlet {

    private Map<String, Object> mControllers = null;
    private Map<Object, Map<String, Method>> mMethods = new HashMap<>();

    public FrontController() {
        super();
        System.out.println("start FrontController");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (mControllers == null) {
            mControllers = (Map<String, Object>) getServletContext().getAttribute("controllers");
        }

        String uri = req.getRequestURI();
        String controllerName = parseControllerName(uri);
        String routerName = parseRouter(uri);

        System.out.printf("requestedPath: %s\n", uri);
        System.out.printf("controllerName: %s\n", controllerName);
        System.out.printf("routerName: %s\n", routerName);

        if (!mControllers.containsKey(controllerName)) {
            resp.getWriter().write("404 controller: " + controllerName);
            return;
        }

        Object controller = mControllers.get(controllerName);
        Method routerMethod = mMethods.get(controller).get(routerName);

        if (routerMethod != null) {
            try {
                routerMethod.invoke(controller);
                return;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        for (Method method : controller.getClass().getMethods()) {
            if (!method.isAnnotationPresent(Router.class)) {
                continue;
            }

            Router router = method.getAnnotation(Router.class);
            System.out.printf("router: %b\n", router != null);
            System.out.printf("router httpmethod: %s\n", router.httpMethod().name());

            if (!router.httpMethod().name().equals(req.getMethod())) {
                System.out.println("differences httpmethod");
                continue;
            }

            if (router.path().equals(routerName)) {
                System.out.println("differences router path");
                continue;
            }

            Map<String, Method> routerMapping = new HashMap<>();
            routerMapping
            mMethods.put(controller, );

            try {
                method.invoke(controller);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            resp.getWriter().write("404 router not found: " + routerName);
        }
    }

    private String parseControllerName(String uri) {
        String uris[] = uri.split("/");
        if (uris.length > 1) {
            return "/" + uris[1];
        }
        return "/";
    }

    private String parseRouter(String uri) {
        String controllerName = parseControllerName(uri);
        return uri.replace(controllerName, "");
    }

}
