package models;


public class StudentModel {

    public int id;
    public String name;
    public String email;
    public String department;
    public String prn_number;
    public long phone_number;

    StudentModel() {

    }

    public StudentModel(int _id, String _name, String _email, String _department, String _prn_number, long _phone_number) {
        id = _id;
        name = _name;
        email = _email;
        department = _department;
        prn_number = _prn_number;
        phone_number = _phone_number;
    }

    public String [] getData() {
        String [] result = new String[6];

        result[0] = String.valueOf(id);
        result[1] = name;
        result[2] = email;
        result[3] = String.valueOf(prn_number);
        result[4] = String.valueOf(phone_number);
        result[5] = department;

        return result;
    }

}
