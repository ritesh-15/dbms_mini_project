package services;

import models.StudentModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class StudentService {

    Database db = null;

    public StudentService() {
        db = new Database();
    }

    public ArrayList<StudentModel> findAllIsNotPlaced() {
        String query = """
                SELECT Student.id, Student.name, email,\s
                phone_number, prn_number,\s
                Department.name as department
                FROM Student INNER JOIN Department\s
                ON Student.department = Department.id
                WHERE isPlaced = false;
                """;

        try {
            ResultSet result = db.statement.executeQuery(query);

            ArrayList<StudentModel> output = new ArrayList<>();

            while(result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String email = result.getString(3);
                long phone_number = result.getLong(4);
                String prn_number = result.getString(5);
                String department = result.getString(6);

                output.add(new StudentModel(id, name, email,department,prn_number,phone_number));
            }

            return output;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean insert(String name, String email, String prn_number, long phone_number, int department) {
        try {

            String query = "INSERT INTO Student(name,email,prn_number,phone_number,department)" +
                    " " +
                    "VALUES(" +
                    "'" + name + "'" + "," +
                    "'" + email + "'" +  "," +
                    "'" + prn_number + "'" + "," +
                    "'" + phone_number + "'" + "," +
                    "'" + department + "'" +
                    ");";

            db.statement.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(int _id) {
        String query = """
                DELETE FROM Student
                WHERE id = \s
                """ + _id;

        try {
            db.statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudent(int _id,String name, String email, int department, long phone_number, String prn_number) {
        String query = "UPDATE Student "+
                "SET name=" + "'" + name + "'," +
                "email=" + "'" + email + "'," +
                "department=" + "'" + department + "'," +
                "phone_number=" + "'" + phone_number + "'," +
                "prn_number=" + "'" + prn_number + "'" +
                " WHERE id=" + _id + ";";

        System.out.println(query);

        try {
            db.statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public StudentModel findById(int _id) {
        String query = """
                SELECT Student.id, Student.name, email,\s
                phone_number, prn_number,\s
                Department.name as department
                FROM Student INNER JOIN Department\s
                ON Student.department = Department.id
                WHERE Student.id = \s
                """ + _id;

        try {
            ResultSet result = db.statement.executeQuery(query);

            StudentModel model = null;

            while(result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String email = result.getString(3);
                long phone_number = result.getLong(4);
                String prn_number = result.getString(5);
                String department = result.getString(6);

                model = new StudentModel(id,name,email,department,prn_number,phone_number);
            }

            return model;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<StudentModel> findAll() {
        String query = """
                SELECT Student.id, Student.name, email,\s
                phone_number, prn_number,\s
                Department.name as department
                FROM Student INNER JOIN Department\s
                ON Student.department = Department.id;""";

        try {
            ResultSet result = db.statement.executeQuery(query);

            ArrayList<StudentModel> output = new ArrayList<>();

            while(result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String email = result.getString(3);
                long phone_number = result.getLong(4);
                String prn_number = result.getString(5);
                String department = result.getString(6);

                output.add(new StudentModel(id, name, email,department,prn_number,phone_number));
            }

            return output;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
