package hellvlet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Session {

    private static Connection mConnection = null;

    static {
        try {
            Class.forName("org.h2.Driver");
            mConnection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/tes", "sa", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static Connection getConnection() {
        return mConnection;
    }

}
