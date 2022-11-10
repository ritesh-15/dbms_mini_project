package models;

public class CompanyModel {

    public int id;
    public String name;
    public int intake;

   public CompanyModel(int _id, String _name, int _intake) {
        id = _id;
        name = _name;
        intake = _intake;
    }

    public String [] getData() {
        String [] result = new String[3];

        result[0] = String.valueOf(id);
        result[1] = name;
        result[2] = String.valueOf(intake);

        return result;
    }
}
