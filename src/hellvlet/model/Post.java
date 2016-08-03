package hellvlet.model;

import hellvlet.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post extends Model {

    private final UserService mUserService = new UserService();

    private int userId = 0;

    private String title = null;

    private String content = null;

    private LocalDateTime regDate = null;

    private User user = null;

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
        return regDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public int getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
