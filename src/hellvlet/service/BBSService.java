package hellvlet.service;

import hellvlet.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BBSService extends Service<Post> {

    private final static List<Post> mPosts = new ArrayList<>();

    public BBSService() {
        super(mPosts);
    }

    public List<Post> findByTitle(String title) {
        return mPosts.stream()
                .filter(item -> item.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    public List<Post> findByAuthor(String author) {
        return mPosts.stream()
                .filter(item -> item.getUser().getAccount().equals(author))
                .collect(Collectors.toList());
    }
}
