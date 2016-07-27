package hellvlet.web;

import hellvlet.model.Post;
import hellvlet.service.BBSService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BBSController extends BaseController {

    private final BBSService mBBSService = new BBSService();

    public void bbsListGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("posts", mBBSService.getAll());
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

    public void bbsSearchGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        render("/bbs/search.jsp", request, response);
    }

    public void bbsWritePost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int userId = (int) request.getSession().getAttribute("id");

        Post post = new Post(userId, title, content);
        mBBSService.create(post);

        response.sendRedirect("/bbs/list");
    }
}
