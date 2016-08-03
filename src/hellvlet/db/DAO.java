package hellvlet.db;

import hellvlet.annotation.Column;
import hellvlet.model.Model;

import java.lang.reflect.Field;
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

    public T create(T clazz) {
        List<Field> columns = new ArrayList<>();
        for (Field field : clazz.getClass().getFields()) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            columns.add(field);
        }

        StringBuffer sql = new StringBuffer(String.format("INSERT INTO %s (id", mTableName));
        for (Field field : columns) {
            if (field.getName().equals("id")) {
                continue;
            }
            sql.append(", " + field.getName());
        }
        sql.append(") VALUES (?");
        for (Field field : columns) {
            sql.append(", ?");
        }
        sql.append(")");

        System.out.println(sql.toString());

        PreparedStatement stmt;

        try {
            stmt = Session.getConnection().prepareStatement(sql.toString());

            stmt.setInt(1, clazz.getId());

            for (int fieldIndex = ; fieldIndex < columns.size(); fieldIndex++) {
                Field field = columns.get(fieldIndex);
                if (field.getType() == String.class) {
                    System.out.println(">>>>>>>>>>>>>>>>>");
                    stmt.setString(fieldIndex + 1, String.valueOf(field.get(clazz)));
                } else {
                    stmt.setInt(fieldIndex + 1, Integer.parseInt(String.valueOf(field.get(clazz))));
                }
            }

            stmt.execute();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

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
        String sql = String.format("SELECT * FROM %s", mTableName);

        try {
            PreparedStatement stmt = Session.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
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
