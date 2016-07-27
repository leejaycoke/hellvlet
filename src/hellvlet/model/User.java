package hellvlet.model;

public class User extends BaseModel {

    private static int nextId;

    private int id;

    private String account;

    private String phone;

    private String password;

    public User(String account, String password, String phone) {
        this.id = getNextId();
        this.account = account;
        this.password = password;
        this.phone = phone;
    }

    @Override
    public int getNextId() {
        return ++nextId;
    }

    @Override
    public int getId() {
        return id;
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
