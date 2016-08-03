package hellvlet.service;

import hellvlet.db.UserDAO;
import hellvlet.model.User;

public class UserService {

    private final UserDAO mUserDAO = new UserDAO();

    public User doLogin(String account, String password) {
        User user = mUserDAO.findByAccount(account);
        if (user != null) {
            if (user.password.equals(password)) {
                return user;
            }
        }

        return null;
    }

}
