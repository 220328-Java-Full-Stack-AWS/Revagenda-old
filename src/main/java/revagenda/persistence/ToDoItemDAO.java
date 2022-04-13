package revagenda.persistence;

import revagenda.ConnectionManager;
import revagenda.models.TestTableModel;
import revagenda.models.ToDoItemModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ToDoItemDAO implements CRUDInterface<ToDoItemModel>{
    public static void main(String[] args) {
        ToDoItemDAO toDoItemDAO = new ToDoItemDAO();
        ToDoItemModel todoitem = new ToDoItemModel(6,"Study","2022045",false,1);
        toDoItemDAO.create(todoitem);
    }
    //Group A
    @Override
    public void create(ToDoItemModel model) {
        String sql = "INSERT INTO to_do_Items (item_id,task,due,completed,user_id) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, model.getItemId());
            pstmt.setString(2, model.getTask());
            pstmt.setString(3, model.getDate());
            pstmt.setBoolean(4, model.isCompleted());
            pstmt.setInt(5, model.getUserId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
