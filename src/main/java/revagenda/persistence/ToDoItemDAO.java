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
