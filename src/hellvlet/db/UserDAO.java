package hellvlet.db;

import hellvlet.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO<User> {

    public UserDAO() {
        super("USER");
    }

    @Override
    public User parse(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setPassword(resultSet.getString("password"));
            user.setAccount(resultSet.getString("account"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
