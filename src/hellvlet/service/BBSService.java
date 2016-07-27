package hellvlet.service;

import hellvlet.model.Post;

import java.util.ArrayList;
import java.util.List;

public class BBSService extends BaseService<Post> {

    private final static List<Post> mPosts = new ArrayList<>();

    public BBSService() {
        super(mPosts);
    }
}
