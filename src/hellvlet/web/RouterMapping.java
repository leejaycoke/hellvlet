package hellvlet.web;

import java.lang.reflect.Method;

public class RouterMapping {

    private String router = null;

    private Method method = null;

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
