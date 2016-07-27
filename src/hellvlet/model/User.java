package hellvlet.model;

public class User extends BaseModel {

    private static int lastId = 0;

    private String account;

    private String phone;

    private String password;

    public User(String account, String password, String phone) {
        super(++lastId);
        this.account = account;
        this.password = password;
        this.phone = phone;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
