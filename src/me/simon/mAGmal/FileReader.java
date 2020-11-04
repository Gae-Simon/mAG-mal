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

    // --> output of the data / create a new object / save the information of each line in the object / output-Method from Person
    public void output() {
        int i = 0;
        Person p = new Person();
        while (filereader.hasNext()) {
            p.pid = ++i;
            p.vorname = filereader.next();
            if (filereader.hasNext()) {
                p.nachname = filereader.next();
            }
            if (filereader.hasNext()) {
                p.klasse = filereader.next();
            }
            if (filereader.hasNext()) {
                p.telefonnummer = filereader.next();
            }
            if (filereader.hasNext()) {
                p.status = filereader.next();
            }
            //System.out.println(p.status);
            p.savePerson(p);
            p.outputPerson();
        }
    }

    // --> close the file
    @Override
    public void close() {
        filereader.close();
    }
}