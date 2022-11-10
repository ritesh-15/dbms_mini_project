package services;

import models.CompanyModel;
import models.StudentModel;

import java.sql.SQLException;

public class PlacementService {

    private Database db = new Database();
    private StudentService studentService = new StudentService();
    private CompanyService companyService = new CompanyService();

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
