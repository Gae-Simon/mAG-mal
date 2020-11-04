package me.simon.mAGmal;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Person {

    private static final AtomicInteger idCount = new AtomicInteger();

    // attribute
    public int pid = idCount.incrementAndGet();
    public String vorname;
    public String nachname;
    public String klasse;
    public String telefonnummer;
    public PersonStatus status = PersonStatus.NONE;

    //constructor
    public Person() {

    }

    // --> create list for teacher and student
    private static ArrayList<Person> lehrerList = new ArrayList<>();
    private static ArrayList<Person> schuelerList = new ArrayList<>();

    // --> save each object in a specific list
    public void savePerson(@NotNull Person person) {

        switch (person.status) {
            case LEHRER:
                lehrerList.add(person);
                break;
            case SCHUELER:
                schuelerList.add(person);
                break;
            default:
                System.out.println("Warnung :: Status von " + person + " komisch");
        }
    }

    // --> output of the list
    public static void outputPersonen() {
        System.out.println();
        System.out.println("Lehrer:");
        for (Person lehrer : lehrerList) {
            System.out.println(lehrer);
            System.out.println();
        }
        System.out.println("Schüler:");
        for (Person schueler : schuelerList) {
            System.out.println(schueler);
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Person [pid=" + pid +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", klasse='" + klasse + '\'' +
                ", telefonnummer='" + telefonnummer + '\'' +
                ", status='" + status + '\'' + ']';

    }
}
