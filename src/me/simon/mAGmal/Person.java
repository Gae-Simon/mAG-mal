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
    ArrayList<Person> lehrerList = new ArrayList<>();
    ArrayList<Person> schuelerList = new ArrayList<>();

    // --> save each object in a specific list
    int i = 0;
    int j = 0;

    public void save_person(@NotNull Person p) {

        if ("lehrer".equalsIgnoreCase(p.status)) {
            lehrerList.add(i, p);
            i++;
        } else {
            schuelerList.add(j, p);
            j++;
        }
    }

    // --> output of the list
    public void person_output() {
        System.out.println("Lehrer:");
        for (int k = 0; k < lehrerList.size(); k++) {
            System.out.println(lehrerList.get(k));
        }
        System.out.println("Schüler:");
        for (int k = 0; k < schuelerList.size(); k++) {
            System.out.println(schuelerList.get(k));
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
