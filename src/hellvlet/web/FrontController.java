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
    private Map<Object, Map<String, Method>> mRouters = null;

    public FrontController() {
        super();
        System.out.println("start FrontController");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (mControllers == null) {
            mControllers = (Map<String, Object>) getServletContext().getAttribute("controllers");
        }

        if (mRouters == null) {
            mRouters = (Map<Object, Map<String, Method>>) getServletContext().getAttribute("routers");
        }

        String uri = req.getRequestURI();
        String controllerName = parseControllerName(uri);
        String routerName = parseRouter(uri);

        System.out.printf("requestedPath: %s\n", uri);
        System.out.printf("controllerName: %s\n", controllerName);
        System.out.printf("routerName: %s\n", routerName);

        Object controller = mControllers.get(controllerName);
        if (controller == null) {
            resp.getWriter().write("404 controller: " + controllerName);
            return;
        }

        if (!mRouters.containsKey(controller)) {
            System.out.println("router not contains controller");
        }

        System.out.println(String.valueOf(">>>>>>>>>>>>>>>" + mRouters.keySet().size()));
        for (Object o : mRouters.keySet()) {
            System.out.println(String.valueOf(o));
        }

        Map<String, Method> router = mRouters.get(controller);
        String key = routerName + ":" + req.getMethod();
        Method method = router.get(key);

        if (method == null) {
            resp.getWriter().write("404 router: " + routerName);
            return;
        }

        try {
            System.out.println("invoke");
            method.invoke(controller, req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
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
