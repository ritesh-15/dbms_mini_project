package models;

public class DepartmentModel {
    public int id;
    public String name;

    public DepartmentModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    DepartmentModel() {

    }

    public String [] getData() {
        String [] row = new String[2];

        row[0] = String.valueOf(id);
        row[1] = name;

        return row;
    }
}
