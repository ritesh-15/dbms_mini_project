package services;

import models.DepartmentModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentService {

    Database db = null;

    public DepartmentService() {
        db = new Database();
    }

    public boolean insert(String name) {
        try {

            String query = "INSERT INTO Department(name)" +
                    "" +
                    "VALUES(" + "'" + name + "'" +
                    ");";

            db.statement.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(int _id) {
        String query = "DELETE FROM Department WHERE id=" + _id + ";";

        try {
            db.statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateById(int _id, String name) {
        String query = "UPDATE Department SET name= '" + name + "' WHERE id=" + _id + ";";

        try {
            db.statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public DepartmentModel findById(int _id) {
        String query = "SELECT id, name FROM Department WHERE id="+_id+";";

        try {
            ResultSet result = db.statement.executeQuery(query);

            DepartmentModel model = null;

            while(result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                model = new DepartmentModel(id, name);
                break;
            }

            return model;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<DepartmentModel> findAllDepartments() {

        try {
            ResultSet result = db.statement.executeQuery("SELECT id, name FROM Department;");

            ArrayList<DepartmentModel> departments = new ArrayList<>();

            while(result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                departments.add(new DepartmentModel(id, name));
            }

            return departments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
