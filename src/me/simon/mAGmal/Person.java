package me.simon.mAGmal;

public class Person {

    // attribute
    public int pid;
    public String vorname;
    public String nachname;
    public String klasse;
    public String telefonnummer;
    public String status;

    //constructor
    public Person (){
        pid  = 0;
        vorname = "---";
        nachname = "---";
        klasse = "0";
        telefonnummer = "0";
        status = "---";
    }

    // --> output of each object
    public void person_output () {
        System.out.printf("%d %s %s %s %s %s\n", pid, vorname, nachname, klasse, telefonnummer, status);
    }
}
