package hellvlet;

import hellvlet.annotation.Controller;
import hellvlet.annotation.Router;
import hellvlet.web.BaseController;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RoutingListener implements ServletContextListener {

    private final static String[] CONTROLLERS = new String[]{"UserController"};

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Map<String, Class> requestMapper = new HashMap<>();

        for (String name : CONTROLLERS) {
            try {
                Class<?> clazz = Class.forName("hellvlet.web." + name);
                System.out.println("clazz name: " + clazz.getName());

                if (!clazz.isAnnotationPresent(Controller.class)) {
                    System.out.println("not presented Controller class: " + clazz.getName());
                    continue;
                }

                Controller controller = clazz.getAnnotation(Controller.class);
                String basePath = controller.basePath();
                requestMapper.put(basePath, clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        servletContextEvent.getServletContext().setAttribute("injectedControllers", requestMapper);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
