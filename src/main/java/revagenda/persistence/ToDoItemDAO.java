package revagenda.persistence;

import revagenda.ConnectionManager;
import revagenda.models.TestTableModel;
import revagenda.models.ToDoItemModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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

    //Group E
    @Override
    public void delete(ToDoItemModel model) {

    }

    //Group F
    /**
    @author Chenxi
    @author Terrell
    @author Mohammad
     @author Kenneth
     */

    @Override
    public List<ToDoItemModel> getAll() {
        List<ToDoItemModel> list = new LinkedList<>();
        try {
            String SQL = "SELECT * FROM test_table";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            ResultSet rs = pstmt.executeQuery();


            while(rs.next()) {
                ToDoItemModel model = new ToDoItemModel();
                model.setItemId(rs.getInt("itemId"));
                model.setTask(rs.getString("task"));
                model.setDate(rs.getString("date"));
                model.setCompleted(rs.getBoolean("completed"));
                model.setUserId(rs.getInt("userId"));
                list.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

