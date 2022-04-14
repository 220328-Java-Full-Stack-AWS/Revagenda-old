package revagenda;


import revagenda.models.TestTableModel;
import revagenda.models.ToDoItemModel;
import revagenda.persistence.TestTableDAO;
import revagenda.persistence.ToDoItemDAO;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ToDoItemDAO dao = new ToDoItemDAO();
        ToDoItemModel toDoItem = new ToDoItemModel("Remember those cool associates you had in your cohort last year", "2023-10-10", false, 1);

        //Group A - Nice!
        toDoItem = dao.create(toDoItem);
        System.out.println("Create test: " + toDoItem);

        //Group B - Great job!
        ToDoItemModel queryModel = dao.read(toDoItem.getItemId());
        System.out.println("Read test: " + queryModel);

        //Group C - Excellent!
        toDoItem.setTask("Get oil changed");
        toDoItem.setDate("2022-05-01");
        toDoItem.setCompleted(true);
        toDoItem.setUserId(2);
        dao.update(toDoItem);
        queryModel = dao.read(toDoItem.getItemId());
        System.out.println("Update test: " + queryModel);

        //Group D - Awesome!
        dao.delete(queryModel.getItemId());
        ToDoItemModel empty = dao.read(queryModel.getItemId());
        System.out.println("Delete by ID test: " + empty);

        //Group E - Fantastic!
        toDoItem = dao.create(toDoItem);
        dao.delete(toDoItem);
        empty = dao.read(toDoItem.getItemId());
        System.out.println("Delete by model test: " + empty);


        //Group F - Amazing!
        List<ToDoItemModel> list = dao.getAll();
        System.out.println(list);


        ConnectionManager.close();
    }
}
        /*
        Need user input - console input via scanner
            later replaced with GUI - web interface in a browser
            HTTP request from client to server

        Output - console output
            later replaced with web GUI

        Validation - validate user input

        Data Persistence - store our info (to-do items) in a relational database - postgreSQL
                            store users - auth purposes

        client-server communication - our client should be able to send data and receive HTTP requests
        We need an API and we need to have our client "consume" the API

        Auth - We will want to allow users to register and login to their account. We want
                to-do items to be associated with particular users.

        Business logic - catch-all for everything not specified elsewhere.

        Hosting - we want to put our server in the cloud, we want our database also in the cloud, and we want to
        serve up our web GUI from some remotely accessible location.

        Translating from java memory to SQL schema. We will start with plain JDBC code (execute SQL scripts
        from java app), later we will put an ORM in place, which is a powerful tool that does this for us.

        Accessibility - make site user friendly.

        Documentation - javadocs, other inline documentation, as well as overview documentation, design docs like
                        ERD, UML models


         */

