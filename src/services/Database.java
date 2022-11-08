package services;

import java.sql.*;

public class Database {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String PATH = "jdbc:mysql://localhost/Placement";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "ritesh";

    public Statement statement = null;

    public Database() {

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(PATH,USERNAME, PASSWORD);

            statement = connection.createStatement();

            System.out.println("Database connected successfully!!!!!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
