package me.simon.mAGmal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/magmal?autoReconnect=true&useSSL=false";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "ZsMZHrSLf^6ycRQ";

    // create gui
    public static Gui gui = new Gui();

    public static void println(final String message) {
        System.out.println(message);

        // --> output message of Text Area
        gui.textArea1.append(message + "\n");
        gui.textArea1.setCaretPosition(gui.textArea1.getDocument().getLength() - 1);
    }

    public static void outputLabel(int columns, final ResultSet resultSet) throws SQLException {
        for (int i = 1; i <= columns; i++) {
            System.out.printf("%16s", resultSet.getMetaData().getColumnLabel(i));

            //--> output message of Text Area
            String output = resultSet.getMetaData().getColumnLabel(i);
            gui.textArea1.append(String.format("%16s", output));
        }
    }

    public static void outputRow(int columns, final ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            for (int i = 1; i <= columns; i++) {
                System.out.printf("%16s", resultSet.getString(i));
                gui.textArea1.append(String.format("%16s", resultSet.getString(i)));
            }
            println("\n");
        }
    }

    //--> main Method
    public static void main(String[] args) throws SQLException {


        //--> create a new file with pathname
        File personFile = new File("C://Users//simon//OneDrive//Desktop", "Personen_Liste.txt");
        File wGFile = new File("C://Users//simon//OneDrive//Desktop", "AG_Liste.txt");

        //--> give class FileReaderPerson the file and start output of the file and close the file
        try (FileReaderPerson fileReader = new FileReaderPerson(personFile)) {
            fileReader.readingPersons();
        }

        //--> give class FileReaderWG the file and start output of the file and close the file
        try (FileReaderWG fileReader = new FileReaderWG(wGFile)) {
            fileReader.readingWGs();
        }


        // connection and instructions to database
        try {
            Class.forName(JDBC_DRIVER);
        } catch (final ClassNotFoundException cnfex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Hey! Dir fehlt der JDBC-Treiber! Das Programm kann nicht auf die Datenbank zugreifen.",
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE
            );

            System.exit(1);
            return;
        }

        try {
            final Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Closing connection to database");
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }));
            println("Connection is successful!");
            println("\n");

            // get Lists
            ArrayList<Person> schuelerList = Person.getSchuelerList();
            ArrayList<Person> lehrerList = Person.getLehrerList();
            ArrayList<WG> wgList = WG.getWgList();

            //JFrame
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {

                    gui.setVisible(true);

                    gui.schuelerEinlesenButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Insertion of the students
                            SqlInstructions.insertionStudents(schuelerList, connection);
                        }
                    });

                    gui.lehrerEinlesenButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //Insertion of the teachers
                            SqlInstructions.insertionTeacher(lehrerList, connection);
                        }
                    });

                    gui.AGEinlesenButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Insertion of the WG
                            SqlInstructions.insertionWG(wgList, connection);
                        }
                    });

                    gui.lehrerZusammenMitAGButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Request Teacher together with WG
                            try {
                                SqlInstructions.requestTeacherTogetherWG(wgList, lehrerList, connection);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    });

                    gui.zuordnungSchuelerZuAGButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //Allocation student to WG
                            try {
                                SqlInstructions.studentAllocate(wgList, schuelerList, connection);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    });

                    gui.ausgabeAGMitgliederButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Output WG Members
                            try {
                                SqlInstructions.wgMemberOutput(connection);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    });


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}