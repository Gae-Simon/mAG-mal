package me.simon.mAGmal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader implements AutoCloseable {

    // --> create scanner
    private Scanner filereader;

    // Constructor --> give scanner a name and file and check if the file is available
    public FileReader(File file) {
        try {
            filereader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Person[] person = new Person [0];

    // --> output of the data / create a new object / save the information of each line in the object / output-Method from Person
    public void output() {
        int i = 0;
        while (filereader.hasNext()) {
            Person p = new Person();
            p.pid = ++i;
            p.vorname = filereader.next();
            p.nachname = filereader.next();
            p.klasse = filereader.next();
            p.telefonnummer = filereader.next();
            p.status = filereader.next();
            p.person_output();
        }
    }

    // --> close the file
    @Override
    public void close() {
        filereader.close();
    }
}