package revagenda.persistence;

import revagenda.ConnectionManager;
import revagenda.models.ToDoItemModel;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ToDoItemDAO implements CRUDInterface<ToDoItemModel> {
    //Group A
    @Override
    public ToDoItemModel create(ToDoItemModel model) {
        String sql = "INSERT INTO to_do_Items (task,due,completed,user_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, model.getTask());
            pstmt.setDate(2, Date.valueOf(model.getDate()));
            pstmt.setBoolean(3, model.isCompleted());
            pstmt.setInt(4, model.getUserId());
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                int key = keys.getInt(1);
                model.setItemId(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    //Group B
    @Override
    public ToDoItemModel read(int id) {
        ToDoItemModel model = new ToDoItemModel();
        try {
            String SQL = "SELECT * FROM to_do_items WHERE item_id = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                model.setItemId(rs.getInt("item_id"));
                model.setTask(rs.getString("task"));
                model.setDate(rs.getString("due"));
                model.setCompleted(rs.getBoolean("completed"));
                model.setUserId(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }



    //Group C
    @Override
    public void update(ToDoItemModel model) {
        String sql = "UPDATE to_do_items SET user_id = ?, task = ?, due = ?, completed = ?  WHERE item_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, model.getUserId());
            pstmt.setString(2, model.getTask());
            pstmt.setDate(3, Date.valueOf(model.getDate()));
            pstmt.setBoolean(4, model.isCompleted());
            pstmt.setInt(5, model.getItemId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Group D
    //Authors: George Bakhoum, Robert Sutton, Arun Mohan, Brandon Le
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM to_do_items WHERE item_id = ?";

        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an item from the to_do_items table
     * Group E
     *
     * @param model The item to delete from to_do_items table
     * @author Steven Dowd
     * @author Randall Hale
     * @author Jarod Heng
     * @author Tony Henderson
     */
    @Override
    public void delete(ToDoItemModel model) {
        int id = model.getItemId();
        String sql = "delete from to_do_items where item_id = ?";

        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Group F

    /**
     * @author Chenxi
     * @author Terrell
     * @author Mohammad
     * @author Kenneth
     */

    @Override
    public List<ToDoItemModel> getAll() {
        List<ToDoItemModel> list = new LinkedList<>();
        try {
            String SQL = "SELECT * FROM to_do_items";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                ToDoItemModel model = new ToDoItemModel();
                model.setItemId(rs.getInt("item_id"));
                model.setTask(rs.getString("task"));
                model.setDate(rs.getString("due"));
                model.setCompleted(rs.getBoolean("completed"));
                model.setUserId(rs.getInt("user_id"));
                list.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

