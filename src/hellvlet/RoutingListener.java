package hellvlet;

import hellvlet.annotation.Controller;
import hellvlet.annotation.Router;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class RoutingListener implements ServletContextListener {

    private final static String[] CONTROLLERS = new String[]{"UserController"};

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Map<String, Object> controllers = new HashMap<>();
        Map<Object, Map<String, Method>> routers = new HashMap<>();

        for (String name : CONTROLLERS) {
            try {
                Class<?> clazz = Class.forName("hellvlet.web." + name);
                System.out.println("clazz name: " + clazz.getName());

                if (!clazz.isAnnotationPresent(Controller.class)) {
                    System.out.println("not presented Controller class: " + clazz.getName());
                    continue;
                }

                Controller controllerAnnotation = clazz.getAnnotation(Controller.class);
                String basePath = controllerAnnotation.basePath();

                Object controller = clazz.newInstance();
                controllers.put(basePath, clazz.newInstance());

                HashMap<String, Method> routerMappings = new HashMap<>();

                for (Method method : controller.getClass().getMethods()) {
                    if (!method.isAnnotationPresent(Router.class)) {
                        continue;
                    }

                    Router routerAnnotation = method.getAnnotation(Router.class);
                    String key = routerAnnotation.path() + ":" + routerAnnotation.httpMethod();
                    if (routerMappings.containsKey(key)) {
                        System.out.printf("router already defined: %s\n", key);
                    }

                    System.out.printf("router httpmethod: %s\n", routerAnnotation.httpMethod().name());

                    routerMappings.put(key, method);
                }

                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>good");

                System.out.println(String.valueOf(controller));
                routers.put(controller, routerMappings);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        System.out.println("injecting controllers complete");

        servletContextEvent.getServletContext().setAttribute("controllers", controllers);
        servletContextEvent.getServletContext().setAttribute("routers", routers);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
