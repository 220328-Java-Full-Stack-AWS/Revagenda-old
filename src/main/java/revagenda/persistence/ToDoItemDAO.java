package revagenda.persistence;

import revagenda.ConnectionManager;
import revagenda.models.ToDoItemModel;

import java.sql.PreparedStatement;
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
        return null;
    }

    //    private int itemId;
    //    private String task;
    //    private String date;
    //    private boolean completed;
    //    private int userId;

    //Group C
    @Override
    public void update(ToDoItemModel model) {
        String sql = "UPDATE to_do_items SET user_id = ?, task = ?, due = ?, completed = ?  WHERE item_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, model.getUserId());
            pstmt.setString(2, model.getTask());
            pstmt.setString(3, model.getDate());
            pstmt.setBoolean(4, model.isCompleted());
            pstmt.setInt(5, model.getItemId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Group D
    @Override
    public void delete(int id) {

    }

    //Group E
    @Override
    public void delete(ToDoItemModel model) {

    }

    //Group F
    @Override
    public List<ToDoItemModel> getAll() {
        return null;
    }
}
