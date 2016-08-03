package hellvlet.model;

import hellvlet.annotation.Column;

public class User extends Model {

    @Column
    public String account;

    public String phone;

    @Column
    public String password;

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
