package me.simon.mAGmal;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
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

            // connection and instructions to database
            try (final Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {

                System.out.println("Connection is successful!");

                // get Lists
                ArrayList<Person> schuelerList = Person.getSchuelerList();
                ArrayList<Person> lehrerList = Person.getLehrerList();

                // JOptionPane selection
                Object [] options = {"Schüler einlesen", "Leher einlesen", "Beenden"};

                selection:
                while (true) {
                    int selected = JOptionPane.showOptionDialog(null, "Was möchten sie machen?", "Menü",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                    switch (selected) {
                        case 0:
                            // Insertion of the students
                            SQLInstructions.insertionStudents(schuelerList, connection);
                            break;
                        case 1:
                            // Insertion of the teacher
                            SQLInstructions.insertionTeacher(lehrerList, connection);
                            break;
                        case 2:
                            // Close Button
                            break selection;
                    }
                }
                // SQL finished
                System.out.println();
                System.out.println("SQL-Instruction executed!");
                System.out.println();
                System.out.println("Connection is closed!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}