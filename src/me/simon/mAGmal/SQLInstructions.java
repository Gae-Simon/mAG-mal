package me.simon.mAGmal;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.sql.*;
import java.util.List;

import static me.simon.mAGmal.Main.printf;
import static me.simon.mAGmal.Main.println;

public class SQLInstructions {

    // Insertion of Students
    public static void insertionStudents(@NotNull List<Person> schuelerList, Connection connection) {
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

                String output = preparedStatement.toString();
                println(output);


                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        println("\n");
        println("\n");
    }

    // Insertion of teachers
    public static void insertionTeacher(@NotNull List<Person> lehrerList, Connection connection) {
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

                String output = preparedStatement.toString();
                println(output);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        println("\n");
        println("\n");
    }

    // Insertion of WG
    public static void insertionWG(@NotNull List<WG> wgList, Connection connection) {
        for (WG wg : wgList) {
            final String sql = "INSERT INTO ag (`agId`, `description`, `lId`) " +
                    "VALUES (?,?,?) " +
                    "ON DUPLICATE KEY UPDATE description = ?, lId = ?";

            try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, wg.getID());
                preparedStatement.setString(2, wg.description);
                preparedStatement.setInt(3, wg.getTeacherHash());
                preparedStatement.setString(4, wg.description);
                preparedStatement.setInt(5, wg.getTeacherHash());

                String output = preparedStatement.toString();
                println(output);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        println("\n");
        println("\n");
    }

    // Request Teacher together with WG
    public static void requestTeacherTogetherWG(@NotNull List<WG> wgList, @NotNull List<Person> lehrerList, @NotNull Connection connection) throws SQLException {

        // SQL Instruction
        final String sql = "SELECT ag.agId, ag.description, ag.lId, teachers.firstName, teachers.lastName " +
                "FROM ag " +
                "INNER JOIN teachers ON ag.lId = teachers.lId " +
                "WHERE 1 " +
                "ORDER BY ag.description";

        // creating statement and result set
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // count columns
        int columns = resultSet.getMetaData().getColumnCount();
        println("Alle Eintragungen: ");

        // output of the columns labels
        for (int i = 1; i <= columns; i++) {
            System.out.printf("%16s", resultSet.getMetaData().getColumnLabel(i));
        }
        println("\n");

        // output of each row
        while (resultSet.next()) {
            for (int j = 1; j <= columns; j++) {
                System.out.printf("%16s", resultSet.getString(j));
            }
            println("\n");
        }

        // --> statement and result set closed
        resultSet.close();
        statement.close();

        println("\n");
        println("\n");
    }

    // Allocation student to WG
    public static void studentAllocate(@NotNull List<WG> wgList, @NotNull List<Person> schuelerList, Connection connection) throws SQLException {
        //SQL Instruction
        final String sql = "INSERT INTO relationstudentag (`sId`, `agId`) " +
                "VALUES (?,?)";

        // Student query
        String studentInputFirstName = JOptionPane.showInputDialog("Wie heißt der Schüler mit Vornamen: ");
        String studentInputLastName = JOptionPane.showInputDialog("Wie heißt der Schüler mit Nachnamen: ");
        String wgInputDescription = JOptionPane.showInputDialog("In welche AG soll der Schüler eingeteilt werden?");

        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Person person : schuelerList) {

                if (person.vorname.equalsIgnoreCase(studentInputFirstName) && person.nachname.equalsIgnoreCase(studentInputLastName)) {
                    for (WG wg : wgList) {
                        if (wg.description.equalsIgnoreCase(wgInputDescription)) {
                            preparedStatement.setInt(1, person.getId());
                            preparedStatement.setInt(2, wg.getID());

                            String output = preparedStatement.toString();
                            println(output);

                            preparedStatement.executeUpdate();

                        }

                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        println("\n");
        println("\n");
    }

    // Output WG Members
    public static void wgMemberOutput(@NotNull Connection connection) throws SQLException {

        // Input
        String wgAuswahlInput = JOptionPane.showInputDialog("Welche AG willst du Ausgeben?");
        final String sql;
        ResultSet resultSet = null;
        Statement statement = connection.createStatement();

        if (wgAuswahlInput.equalsIgnoreCase("Alle")) {

            // SQL Instruction
            sql = "SELECT students.firstName, students.lastName, students.class, ag.description " +
                    "FROM relationstudentag " +
                    "INNER JOIN students ON relationstudentag.sId = students.sId " +
                    "INNER JOIN ag ON relationstudentag.agId = ag.agId " +
                    "WHERE 1 " +
                    "ORDER BY students.lastName";

            resultSet = statement.executeQuery(sql);

        } else {

            // SQL Instruction
            sql = "SELECT students.firstName, students.lastName, students.class, ag.description " +
                    "FROM relationstudentag " +
                    "INNER JOIN students ON relationstudentag.sId = students.sId " +
                    "INNER JOIN ag ON relationstudentag.agId = ag.agId " +
                    "WHERE ag.description = ? " +
                    "ORDER BY students.lastName";


            try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, wgAuswahlInput);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // count columns
        assert resultSet != null;
        int columns = resultSet.getMetaData().getColumnCount();
        println("Alle Eintragungen zu " + wgAuswahlInput + ": ");

        // output of the columns labels
        for (int i = 1; i <= columns; i++) {
            Object message = resultSet.getMetaData().getColumnLabel(i);
            printf("%16s", message);

            System.out.printf("%16s", resultSet.getMetaData().getColumnLabel(i));
        }
        println("\n");

        // output of each row
        while (resultSet.next()) {
            for (int j = 1; j <= columns; j++) {
                System.out.printf("%16s", resultSet.getString(j));
            }
            println("\n");
        }

        // --> statement and result set closed
        resultSet.close();

        println("\n");
        println("\n");
    }
}
