package hellvlet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Router {

    String path();

    HttpMethod httpMethod() default HttpMethod.GET;
}

enum HttpMethod {

    GET, POST
}
