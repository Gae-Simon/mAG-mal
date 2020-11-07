package me.simon.mAGmal;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {


    // JDBC driver name and database URL
    static final String JDBC_Driver = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/magmal";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "ZsMZHrSLf^6ycRQ";

    //--> main Method
    public static void main(String[] args) {

        //--> create a new file with pathname
        File file = new File("C://Users//simon//OneDrive//Desktop//test.txt");

        //--> give class FileReader the file f and start output of the file and close the file
        try (FileReader fileReader = new FileReader(file)) {
            fileReader.output();
        }


        try {
            // Open driver
            Class.forName(JDBC_Driver);

            // connection to database
            Connection connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/magmal", "root", "ZsMZHrSLf^6ycRQ");
            System.out.println("Connection is successful");

            // creating a statement
            Statement statement = connection.createStatement();

            // SQL Statement
            String sql = "INSERT INTO students VALUES(1, 'Simon', 'Gärtner', '10', 1234)";

            // SQL Statement execute
            statement.executeUpdate(sql);
            System.out.println("SQL-Anweisung ausgeführt!");

            // close connection
            connection.close();
            System.out.println("Connection closed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
