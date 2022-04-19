package revagenda.services;

import revagenda.models.ToDoItemModel;
import revagenda.persistence.ToDoItemDAO;

import java.util.List;

public class ToDoItemService {
    private final ToDoItemDAO dao;

    public ToDoItemService() {
        this.dao = new ToDoItemDAO();
    }

    //dependency injection constructor - The reason I am not doing DI is because I would rather have this class/layer
    //be more tightly coupled to the persistence layer, than have the API layer be even slightly coupled with the persistence layer.
//    public ToDoItemService(ToDoItemDAO dao) {
//        this.dao = dao;
//    }

    //we want to include at least the 6 methods from the DAO

    public ToDoItemModel create(ToDoItemModel model) {
        return dao.create(model);
    }

    public ToDoItemModel read(int id) {
        return dao.read(id);
    }

    public void update(ToDoItemModel model) {
        dao.update(model);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void delete(ToDoItemModel model) {
        dao.delete(model);
    }

    public List<ToDoItemModel> getAll() {
        return dao.getAll();
    }



}
