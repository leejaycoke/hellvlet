package hellvlet;

import hellvlet.annotation.Controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;


public class RoutingListener implements ServletContextListener {

    private final static String[] CONTROLLERS = new String[]{"UserController"};

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Map<String, Object> controllers = new HashMap<>();
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
                controllers.put(basePath, clazz.newInstance());
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
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
