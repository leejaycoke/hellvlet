package hellvlet.service;

import hellvlet.db.PostDAO;
import hellvlet.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostService {

    private final PostDAO mPostDAO = new PostDAO();

    public List<Post> getList() {
        return mPostDAO.getList();
    }

    public List<Post> findByTitle(String title) {
        return mPostDAO.findByTitle(title);
    }

    public List<Post> findByAuthor(String author) {
        return mPostDAO.findByAuthor(author);
    }
}
