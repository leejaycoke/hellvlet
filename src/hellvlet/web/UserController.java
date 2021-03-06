package hellvlet.web;

import hellvlet.annotation.Router;
import hellvlet.db.UserDAO;
import hellvlet.http.HttpMethod;
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

    private final UserDAO mUserDAO = new UserDAO();

    @Router(path = "/register")
    public void registerView(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        render("/user/register.jsp", request, response);
    }

    @Router(path = "/register", method = HttpMethod.POST)
    public void register(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String account = request.getParameter("account");

        User user = mUserDAO.findByAccount(account);
        if (user != null) {
            request.setAttribute("error", "이미 사용중인 아이디입니다.");
            render("/user/register.jsp", request, response);
        }

        user = new User();
        user.setAccount(account);
        user.setPassword(request.getParameter("password"));

        mUserDAO.create(user);

        response.sendRedirect("/user/login");
    }

    @Router(path = "/login")
    public void loginView(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("account")) {
                request.setAttribute("account", cookie.getValue());
                break;
            }
        }

        render("/user/login.jsp", request, response);
    }

    @Router(path = "/logout")
    public void userLogoutGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getSession().invalidate();
        response.sendRedirect("/user/login");
    }

    @Router(path = "/login", method = HttpMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        User user = mUserService.doLogin(account, password);
        if (user == null) {
            request.setAttribute("error", "아이디 혹은 비밀번호가 틀립니다.");
            render("/user/login.jsp", request, response);
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

        response.sendRedirect("/post/list");
    }

}
