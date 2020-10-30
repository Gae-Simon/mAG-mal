package me.simon.mAGmal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    // --> create scanner
    Scanner s;

    // Constructor --> give scanner a name and file and check if the file is available
    FileReader(File f) {
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // --> output of the data / create a new object / save the information of each line in the object / output-Method from Person
    public void output() {
        int i = 0;
        while (s.hasNext()) {
            Person p = new Person();
            p.pid = ++i;
            p.vorname = s.next();
            p.nachname = s.next();
            p.klasse = s.next();
            p.telefonnummer = s.next();
            p.status = s.next();
            p.person_output();
        }
    }

    // --> close the file
    public void close() {
        s.close();
    }
}