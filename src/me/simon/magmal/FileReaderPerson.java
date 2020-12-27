package me.simon.magmal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderPerson implements AutoCloseable {

    // --> create scanner
    private Scanner filereaderPerson;

    // personFileReader --> give scanner a name and file and check if the file is available
    public FileReaderPerson(File personFile) {
        try {
            filereaderPerson = new Scanner(personFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // --> output of the data / create a new object / save the information of each line in the object / output-Method from Person
    public void readingPersons() {
        while (filereaderPerson.hasNext()) {
            Person p = new Person();
            p.vorname = filereaderPerson.next();
            if (filereaderPerson.hasNext()) {
                p.nachname = filereaderPerson.next();
            }
            if (filereaderPerson.hasNext()) {
                p.klasse = filereaderPerson.next();
            }
            if (filereaderPerson.hasNext()) {
                p.telefonnummer = filereaderPerson.next();
            }
            if (filereaderPerson.hasNext()) {
                String statusStr = filereaderPerson.next();
                p.status = PersonStatus.get(statusStr);
            }
            p.savePerson(p);
        }
        Person.outputPersonen();
    }

    // --> close the file
    @Override
    public void close() {
        filereaderPerson.close();
    }
}