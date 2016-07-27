package hellvlet.model;

import hellvlet.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post extends Model {

    private final UserService mUserService = new UserService();

    private String title = null;

    private String content = null;

    private LocalDateTime regDate = null;

    private User user = null;

    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.regDate = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public String getRegDateStr() {
        return regDate.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    public User getUser() {
        return user;
    }
}
