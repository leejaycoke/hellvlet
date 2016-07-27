package hellvlet.model;

import hellvlet.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post extends BaseModel {

    private static int currentId;

    private final UserService mUserService = new UserService();

    private int userId;

    private String title;

    private String content;

    private LocalDateTime regDate;

    private User user = null;

    public Post(int userId, String title, String content) {
        super(++currentId);
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.regDate = LocalDateTime.now();
    }

    public int getUserId() {
        return userId;
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
        User user = mUserService.findById(userId);
        return user;
    }

}
