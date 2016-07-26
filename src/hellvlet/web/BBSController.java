package hellvlet.web;

import hellvlet.model.BBS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BBSController extends BaseController {


    private final List<BBS> mUsers = new ArrayList<>();

    public void bbsListGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
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
}
