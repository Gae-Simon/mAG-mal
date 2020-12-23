package me.simon.mAGmal;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static me.simon.mAGmal.Main.println;

public class Person {

    // attribute
    public String vorname;
    public String nachname;
    public String klasse;
    public String telefonnummer;
    public PersonStatus status = PersonStatus.NONE;


    //constructor
    public Person() {

    }

    // get ID with Hash Code
    public int getId() {
        return (this.vorname + "$" + this.nachname).hashCode();
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
                println("Warnung :: Status von " + person + " komisch");
        }
    }

    // --> getter for lists
    public static ArrayList<Person> getSchuelerList() {
        return schuelerList;
    }

    public static ArrayList<Person> getLehrerList() {
        return lehrerList;
    }

    // --> output of the list
    public static void outputPersonen() {
        println("\n");
        println("Lehrer: ");
        for (Person lehrer : lehrerList) {
            String output = lehrer.toString();
            println(output);
            println("\n");
        }
        println("Schüler: ");
        for (Person schueler : schuelerList) {
            String output = schueler.toString();
            println(output);
        }
        println("\n");
    }

    @Override
    public String toString() {
        return "Person [pid=" + this.getId() +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", klasse='" + klasse + '\'' +
                ", telefonnummer='" + telefonnummer + '\'' +
                ", status='" + status + '\'' + ']';

    }


}
