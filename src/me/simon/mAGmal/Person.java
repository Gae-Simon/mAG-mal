package me.simon.mAGmal;

public class Person {

    // attribute
    int pid;
    String vorname;
    String nachname;
    String klasse;
    String telefonnummer;
    String status;

    //constructor
    Person (){
        pid  = 0;
        vorname = "---";
        nachname = "---";
        klasse = "0";
        telefonnummer = "0";
        status = "---";
    }

    // --> output of each object
    public void person_output () {
        System.out.print(pid +" ");
        System.out.print(vorname + " ");
        System.out.print(nachname +" ");
        System.out.print(klasse + " ");
        System.out.print(telefonnummer + " ");
        System.out.println(status + " ");
    }
}
