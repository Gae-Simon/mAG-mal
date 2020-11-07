package me.simon.mAGmal.reader;

import me.simon.mAGmal.Person;
import me.simon.mAGmal.PersonStatus;

import java.io.File;
import java.sql.Connection;
import java.util.Scanner;

public class PersonReader extends AbstractFileReader<Person> {

    public PersonReader(File file) {
        super(file);
    }

    @Override
    public void read(Scanner scanner) {
        while (scanner.hasNext()) {
            Person p = new Person();
            p.vorname = scanner.next();
            if (scanner.hasNext()) {
                p.nachname = scanner.next();
            }
            if (scanner.hasNext()) {
                p.klasse = scanner.next();
            }
            if (scanner.hasNext()) {
                p.telefonnummer = scanner.next();
            }
            if (scanner.hasNext()) {
                String statusStr = scanner.next();
                p.status = PersonStatus.get(statusStr);
            }
            add(p);
        }
    }

    @Override
    public void save(Connection connection, Person obj) {

    }

}