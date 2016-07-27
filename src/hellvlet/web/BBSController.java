package hellvlet.web;

import hellvlet.model.Post;
import hellvlet.model.User;
import hellvlet.service.BBSService;
import hellvlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BBSController extends BaseController {

    private final BBSService mBBSService = new BBSService();

    private final UserService mUserService = new UserService();

    public void bbsListGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String filter = request.getParameter("filter");
        String q = request.getParameter("q");

        if (filter != null && !filter.equals("")) {
            if (filter.equals("title")) {
                request.setAttribute("posts", mBBSService.findByTitle(q));
            } else {
                request.setAttribute("posts", mBBSService.findByAuthor(q));
            }
        } else {
            request.setAttribute("posts", mBBSService.getAll());
        }
        render("/bbs/list.jsp", request, response);
    }

    public void bbsWriteGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        render("/bbs/write.jsp", request, response);
    }

    public void bbsEditGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        render("/bbs/edit.jsp", request, response);
    }

    public void bbsWritePost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int userId = (int) request.getSession().getAttribute("id");

        User user = mUserService.get(userId);
        Post post = new Post(user, title, content);
        mBBSService.create(post);

        response.sendRedirect("/bbs/list");
    }
}
