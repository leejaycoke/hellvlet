package hellvlet.web;

import hellvlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends BaseController {

    private final Map<String, User> mUsers = new HashMap<>();

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
        render("/user/login.jsp", request, response);
    }

    public void userLoginPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String account = String.valueOf(request.getParameter("account"));
        String password = String.valueOf(request.getParameter("password"));

        User user = mUsers.get(account);
        if (user == null || !user.getPassword().equals(password)) {
            request.setAttribute("error", "아이디 혹은 비밀번호가 틀립니다.");
            render("/user/login.jsp", request, response);
        }

        HttpSession session = request.getSession();
        session.setAttribute("account", account);
    }

}
