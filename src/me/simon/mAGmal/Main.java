package me.simon.mAGmal;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Main {

    // JDBC driver name and database URL
    static final String JDBC_Driver = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/magmal?autoReconnect=true&useSSL=false";

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
            try (final Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {

                System.out.println("Connection is successful");

                // get Lists
                ArrayList<Person> schuelerList = Person.getSchuelerList();
                ArrayList<Person> lehrerList = Person.getLehrerList();

                // Insertion of the students
                for (Person person : schuelerList) {
                    final String sql = "INSERT INTO students " +
                            "(`sId`, `firstName`, `lastName`, `class`, `phonenumber`) " +
                            "VALUES " +
                            "(?,?,?,?,?) " +
                            "ON DUPLICATE KEY UPDATE " +
                            "firstName = ?, lastName = ?, class = ?, phonenumber = ?;";
                    try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                        preparedStatement.setInt(1, person.getId());
                        preparedStatement.setString(2, person.vorname);
                        preparedStatement.setString(3, person.nachname);
                        preparedStatement.setString(4, person.klasse);
                        preparedStatement.setString(5, person.telefonnummer);
                        preparedStatement.setString(6, person.vorname);
                        preparedStatement.setString(7, person.nachname);
                        preparedStatement.setString(8, person.klasse);
                        preparedStatement.setString(9, person.telefonnummer);

                        System.out.println(preparedStatement.toString());

                        preparedStatement.executeUpdate();
                    }
                }
                // Insertion of the teachers
                for (Person person : lehrerList) {
                    final String sql = "INSERT INTO teachers " +
                            "(`lId`, `firstName`, `lastName`) " +
                            "VALUES " +
                            "(?,?,?) " +
                            "ON DUPLICATE KEY UPDATE " +
                            "firstName = ?, lastName = ?;";
                    try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                        preparedStatement.setInt(1, person.getId());
                        preparedStatement.setString(2, person.vorname);
                        preparedStatement.setString(3, person.nachname);
                        preparedStatement.setString(4, person.vorname);
                        preparedStatement.setString(5, person.nachname);

                        System.out.println(preparedStatement.toString());

                        preparedStatement.executeUpdate();

                    }
                }

                // SQL Statement execute
                System.out.println("SQL-Anweisung ausgeführt!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}