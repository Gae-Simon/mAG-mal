package me.simon.mAGmal;

import org.jetbrains.annotations.NotNull;
import org.mariadb.jdbc.MariaDbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLInstructions {

    // Insertion of Students
    public static void insertionStudents(@NotNull List<Person> schuelerList, Connection connection){
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

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Insertion of teachers
    public static void insertionTeacher (@NotNull List<Person> lehrerList, Connection connection){
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

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
