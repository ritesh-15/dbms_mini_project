package models;

public class PlacementModel {

    public int id;
    public String type;
    public String name;
    public String email;
    public String prn_number;
    public String phone_number;
    public float totalPackage;
    public String company;

    public PlacementModel(int id, String type, String name, String email, String prn_number, String phone_number, float
            totalPackage, String company) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.email = email;
        this.prn_number = prn_number;
        this.phone_number = phone_number;
        this.totalPackage = totalPackage;
        this.company = company;
    }

    public String[] getData() {
        String[] result = new String[8];

        result[0] = String.valueOf(id);
        result[1] = type;
        result[2] = name;
        result[3] = email;
        result[4] = prn_number;
        result[5] = phone_number;
        result[6] = String.valueOf(totalPackage);
        result[7] = company;


        return result;
    }
}
