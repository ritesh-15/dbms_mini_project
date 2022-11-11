package services;

import models.CompanyModel;
import models.PlacementModel;
import models.StudentModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlacementService {

    private Database db = new Database();
    private StudentService studentService = new StudentService();
    private CompanyService companyService = new CompanyService();

    public ArrayList<PlacementModel> findAll() {

        String query = """
                SELECT Placement.id, type, package, Student.name, prn_number, email, phone_number,\s
                Company.name as company
                FROM Placement
                INNER JOIN Student ON Placement.student_id = Student.id
                INNER JOIN Company ON Placement.company_id = Company.id
                WHERE Student.isPlaced = true;
                """;

        try {
            ResultSet result = db.statement.executeQuery(query);

            ArrayList<PlacementModel> placements = new ArrayList<>();

            while(result.next()) {
                int id = result.getInt(1);
                String type = result.getString(2);
                float totalPackage = Float.parseFloat(result.getString(3));
                String name = result.getString(4);
                String prn_number = result.getString(5);
                String email = result.getString(6);
                String phone_number = result.getString(7);
                String company = result.getString(8);


                placements.add(new PlacementModel(id, type, name, email, prn_number
                , phone_number, totalPackage, company));
            }

            return placements;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(int _id, String prnNumber) {
        String query = "DELETE FROM Placement WHERE id=" + _id + ";";

        try {
            db.statement.executeUpdate(query);

            studentService.updateStudentSetIsPlaced(prnNumber, false);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateById(int _id,String type, float totalPackage, String companyName) {

        try {
            CompanyModel company = companyService.findByName(companyName);

            if (company == null)
                return false;

            String query = "UPDATE Placement SET package= '" + totalPackage
                    + "', type='" + type + "'" +
                    ", company_id='" + company.id + "'" +
                    "WHERE id=" + _id + ";";


            db.statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public PlacementModel findById(int _id) {
        String query = """
                SELECT Placement.id, type, package, Student.name, prn_number, email, phone_number,\s
                Company.name as company
                FROM Placement
                INNER JOIN Student ON Placement.student_id = Student.id
                INNER JOIN Company ON Placement.company_id = Company.id
                WHERE Student.isPlaced = true 
                AND Placement.id = 
                """ + _id;

        try {
            ResultSet result = db.statement.executeQuery(query);

            PlacementModel model = null;

            while(result.next()) {
                int id = result.getInt(1);
                String type = result.getString(2);
                float totalPackage = Float.parseFloat(result.getString(3));
                String name = result.getString(4);
                String prn_number = result.getString(5);
                String email = result.getString(6);
                String phone_number = result.getString(7);
                String company = result.getString(8);


                model = new PlacementModel(id, type, name, email, prn_number
                        , phone_number, totalPackage, company);
            }

            return model;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert(String prnNumber, float getPackage, String _company, String type) {
        try {

            StudentModel student = studentService.findByPRN(prnNumber);
            CompanyModel company = companyService.findByName(_company);


            if (student == null || company == null) {
                return false;
            }

            String query = "INSERT INTO Placement(student_id, package, company_id, type)" +
                    "" +
                    "VALUES(" + "'" + student.id + "',"
                    + "'" + getPackage + "',"
                    + "'" + company.id  + "'," +
                    "'" + type  + "'" +
                    ");";



            db.statement.executeUpdate(query);

            studentService.updateStudentSetIsPlaced(student.id,true);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
