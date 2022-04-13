package revagenda.persistence;

import revagenda.ConnectionManager;
import revagenda.models.ToDoItemModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ToDoItemDAO implements CRUDInterface<ToDoItemModel>{

    //Group A
    @Override
    public void create(ToDoItemModel model) {

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


            while(rs.next()) {
                model.setItemId(rs.getInt("item_id"));
                model.setTask(rs.getString("task"));
                model.setDate(rs.getString("due"));
                model.setCompleted(rs.getBoolean("completed"));
                model.setUserId(rs.getInt("user_id"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return model;
    }

    //Group C
    @Override
    public void update(ToDoItemModel model) {

    }

    //Group D
    //Authors: George Bakhoum, Robert Sutton, Arun Mohan, Brandon Le
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM to_do_items WHERE id = ?";

        try{
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Deletes an item from the to_do_items table
     * Group E
     * @author Steven Dowd
     * @author Randall Hale
     * @author Jarod Heng
     * @author Tony Henderson
     * @param model The item to delete from to_do_items table
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
    @Override
    public List<ToDoItemModel> getAll() {
        return null;
    }
}
