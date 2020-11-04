package me.simon.mAGmal;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Person {

    // attribute
    public int pid;
    public String vorname;
    public String nachname;
    public String klasse;
    public String telefonnummer;
    public String status;

    //constructor
    public Person() {
        pid = 0;
        vorname = "---";
        nachname = "---";
        klasse = "0";
        telefonnummer = "0";
        status = "---";
    }

    // --> create list for teacher and student
    private static ArrayList<Person> lehrerList = new ArrayList<>();
    private static ArrayList<Person> schuelerList = new ArrayList<>();

    // --> save each object in a specific list
    public void savePerson(@NotNull Person p) {

        if ("lehrer".equalsIgnoreCase(p.status)) {
            lehrerList.add(p);
        } else {
            schuelerList.add(p);
        }
    }

    // --> output of the list
    public void outputPerson() {
        System.out.println("Lehrer:");
        for (Person lehrer : lehrerList) {
            System.out.println(lehrer);
        }
        System.out.println("Schüler:");
        for (Person schueler : schuelerList) {
            System.out.println(schueler);
        }
    }

    @Override
    public String toString() {
        return "pid=" + pid +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", klasse='" + klasse + '\'' +
                ", telefonnummer='" + telefonnummer + '\'' +
                ", status='" + status + '\'';

    }
}
