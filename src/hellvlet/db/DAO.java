package hellvlet.db;

import hellvlet.model.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T extends Model> {

    private final String mTableName;

    protected DAO(String tableName) {
        mTableName = tableName;
    }

    public abstract boolean create(T clazz);

    public T get(int id) {
        String sql = String.format("SELECT * FROM %s WHERE id = ?", mTableName);

        try {
            PreparedStatement stmt = Session.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return parse(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<T> getList() {
        List<T> items = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s ORDER BY id DESC", mTableName);

        try {
            PreparedStatement stmt = Session.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                items.add(parse(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public boolean delete(int id) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", mTableName);
        try {
            PreparedStatement stmt = Session.getConnection().prepareStatement(sql);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//
//    public abstract T update(int id, T model);

    public abstract T parse(ResultSet resultSet);
}
