package hellvlet.model;

import java.time.LocalDateTime;

public class BBS {

    private String title;

    private String content;

    private LocalDateTime regDate;

    public BBS(String title, String content) {
        this.title = title;
        this.content = content;
        this.regDate = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

}
