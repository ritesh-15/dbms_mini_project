package services;

import models.CompanyModel;
import models.DepartmentModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyService {

    Database db = new Database();

    public boolean insert(String name, int intake) {
        try {

            String query = "INSERT INTO Company(name, intake)" +
                    "" +
                    "VALUES(" + "'" + name + "'," + "'" + intake + "'" +
            ");";

            db.statement.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateById(int _id, String name, int intake) {
        String query = "UPDATE Company SET name= '" + name
                + "', intake='" + intake + "'" +
                "WHERE id=" + _id + ";";

        try {
            db.statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(int _id) {
        String query = "DELETE FROM Company WHERE id=" + _id + ";";

        try {
            db.statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public CompanyModel findById(int _id) {
        String query = "SELECT id, name, intake FROM Company WHERE id="+_id+";";

        try {
            ResultSet result = db.statement.executeQuery(query);

            CompanyModel model = null;

            while(result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                int intake = result.getInt(3);
                model = new CompanyModel(id, name, intake);
                break;
            }

            return model;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CompanyModel findByName(String _name) {
        String query = "SELECT id, name, intake FROM Company WHERE name='"+ _name  +"';";

        try {
            ResultSet result = db.statement.executeQuery(query);

            CompanyModel model = null;

            while(result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                int intake = result.getInt(3);
                model = new CompanyModel(id, name, intake);
                break;
            }

            return model;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<CompanyModel> findAll() {
        try {
            ResultSet result = db.statement.executeQuery("SELECT id, name, intake FROM Company;");

            ArrayList<CompanyModel> departments = new ArrayList<>();

            while(result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                int intake = result.getInt(3);
                departments.add(new CompanyModel(id, name, intake));
            }

            return departments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
