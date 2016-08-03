package hellvlet.web;

import hellvlet.annotation.Router;
import hellvlet.db.PostDAO;
import hellvlet.http.HttpMethod;
import hellvlet.model.Post;
import hellvlet.service.PostService;
import hellvlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostController extends BaseController {

    private final PostDAO mPostDAO = new PostDAO();

    private final PostService mPostService = new PostService();
    private final UserService mUserService = new UserService();

    @Router(path = "/list")
    public void postListView(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String filter = request.getParameter("filter");
        String q = request.getParameter("q");

        if (filter != null && !filter.equals("")) {
            if (filter.equals("title")) {
                request.setAttribute("posts", mPostService.findByTitle(q));
            } else {
                request.setAttribute("posts", mPostService.findByAuthor(q));
            }
        } else {
            request.setAttribute("posts", mPostService.getList());
        }
        render("/post/list.jsp", request, response);
    }

    @Router(path = "/write")
    public void writeView(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        render("/post/write.jsp", request, response);
    }

    @Router(path = "/edit?")
    public void editView(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        render("/post/edit.jsp", request, response);
    }

    @Router(path = "/write", method = HttpMethod.POST)
    public void write(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int userId = (int) request.getSession().getAttribute("id");

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setUserId(userId);

        mPostDAO.create(post);

        response.sendRedirect("/post/list");
    }
}
