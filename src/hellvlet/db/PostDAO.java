package hellvlet.db;

import hellvlet.model.Post;
import hellvlet.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends DAO<Post> {

    private final UserDAO mUserDAO = new UserDAO();

    public PostDAO() {
        super("post");
    }

    @Override
    public boolean create(Post post) {
        String sql = "INSERT INTO post " +
                "(user_id, title, content) " +
                "VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt = Session.getConnection().prepareStatement(sql);
            stmt.setInt(1, post.getUserId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getContent());
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Post parse(ResultSet resultSet) {
        Post post = new Post();
        try {
            post.setId(resultSet.getInt("id"));
            post.setUserId(resultSet.getInt("user_id"));
            post.setTitle(resultSet.getString("title"));
            post.setContent(resultSet.getString("content"));
            post.setRegDate(resultSet.getTimestamp("reg_date").toLocalDateTime());
            post.setUser(mUserDAO.get(post.getUserId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    public List<Post> findByTitle(String title) {
        List<Post> posts = new ArrayList<>();

        String sql = "SELECT * FROM post WHERE title LIKE ? ORDER BY id DESC";

        try {
            PreparedStatement stmt = Session.getConnection().prepareStatement(sql);
            stmt.setString(1, "%" + title + "%");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                posts.add(parse(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public List<Post> findByAuthor(String author) {
        List<Post> posts = new ArrayList<>();

        String sql = "SELECT P.* FROM post P " +
                "JOIN user U ON P.user_id = U.id " +
                "WHERE U.account LIKE ? " +
                "ORDER BY P.id DESC";

        try {
            PreparedStatement stmt = Session.getConnection().prepareStatement(sql);
            stmt.setString(1, "%" + author + "%");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                posts.add(parse(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
