package hellvlet.web;

import hellvlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends BaseController {

    private Map<String, User> mUsers = new HashMap<>();

    public void userRegisterGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        render("/user/register.jsp", request, response);
    }

    public void userRegisterPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String account = String.valueOf(request.getParameter("account"));
        if (mUsers.containsKey(account)) {
            request.setAttribute("error", "이미 사용중인 아이디입니다.");
            render("/user/register.jsp", request, response);
        }

        String password = String.valueOf(request.getParameter("password"));
        String phone = String.valueOf(request.getParameter("phone"));

        User user = new User(account, password, phone);
        mUsers.put(account, user);
    }

    public void userLoginGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        render("/user/register.jsp", request, response);
    }

}
