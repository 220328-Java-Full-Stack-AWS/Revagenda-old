package revagenda.persistence;

import revagenda.ConnectionManager;
import revagenda.models.ToDoItemModel;

import java.sql.DriverManager;
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

    //Group C
    @Override
    public void update(ToDoItemModel model) {

    }

    //Group D
    @Override
    public void delete(int id) {

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
