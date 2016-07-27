package hellvlet.web;

import hellvlet.model.User;
import hellvlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserController extends BaseController {

    private final UserService mUserService = new UserService();

    public void userRegisterGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        render("/user/register.jsp", request, response);
    }

    public void userRegisterPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String account = String.valueOf(request.getParameter("account"));

        User existsUser = mUserService.findByAccount(account);
        if (existsUser != null) {
            request.setAttribute("error", "이미 사용중인 아이디입니다.");
            render("/user/register.jsp", request, response);
        }

        String password = String.valueOf(request.getParameter("password"));
        String phone = String.valueOf(request.getParameter("phone"));

        User user = new User(account, password, phone);
        mUserService.create(user);

        response.sendRedirect("/user/login");
    }

    public void userLoginGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("account")) {
                request.setAttribute("account", cookie.getValue());
                break;
            }
        }

        render("/user/login.jsp", request, response);
    }

    public void userLogoutGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getSession().invalidate();
        response.sendRedirect("/user/login");
    }

    public void userLoginPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        User user = mUserService.findByAccount(account);
        if (user == null || !user.getPassword().equals(password)) {
            request.setAttribute("error", "아이디 혹은 비밀번호가 틀립니다.");
            render("/user/login.jsp", request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("account", user.getAccount());

        if (request.getParameter("is_remember") != null) {
            Cookie cookie = new Cookie("account", user.getAccount());
            response.addCookie(cookie);
        } else {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("account")) {
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                    response.addCookie(cookie);
                }
            }
        }

        response.sendRedirect("/bbs/list");
    }

}
