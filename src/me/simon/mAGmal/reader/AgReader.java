package me.simon.mAGmal.reader;

import me.simon.mAGmal.Ag;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AgReader extends AbstractFileReader<Ag> {

    public AgReader(File file) {
        super(file);
    }

    @Override
    protected void read(Scanner scanner) {

    }

    @Override
    public void save(Connection connection, Ag obj) {
        final String query = "INSERT INTO ags VALUES (?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, obj.getId());
        preparedStatement.setString(2, obj.description);
    }

}