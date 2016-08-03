package hellvlet.db;

import hellvlet.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO<User> {

    public UserDAO() {
        super("user");
    }

    public User findByAccount(String account) {
        String sql = "SELECT * FROM user WHERE account = ?";
        try {
            PreparedStatement stmt = Session.getConnection().prepareStatement(sql);
            stmt.setString(1, account);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return parse(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO USER " +
                "(account, password) " +
                "VALUES(?, ?)";
        try {
            PreparedStatement stmt = Session.getConnection().prepareStatement(sql);
            stmt.setString(1, user.getAccount());
            stmt.setString(2, user.getPassword());
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
