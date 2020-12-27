package me.simon.magmal;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static me.simon.magmal.Main.println;

public class WG {

    // attribute
    public String description;
    public String teacherfirstname;
    public String teacherlastname;

    //constructor
    public WG() {

    }

    // get ID with Hash Code
    public int getID() {
        return (this.description).hashCode();
    }

    // get Teacher Hash Code
    public int getTeacherHash (){
        return (this.teacherfirstname + "$" + this.teacherlastname).hashCode();
    }
    // create List for WG
    public static ArrayList<WG> wgList = new ArrayList<>();

    //save the WG in the List
    public static void saveWG(@NotNull WG wg) {
        wgList.add(wg);
    }

    //getter for List
    public static ArrayList<WG> getWgList() {
        return wgList;
    }

    // --> output of the List
    public static void outputWG() {
        println("\n");
        println("WGs: ");
        for (WG wg : wgList) {
            String output = wg.toString();
            println(output);
        }
        println("\n");
    }

    @Override
    public String toString() {
        return "WG{" + "aid='" + this.getID() + '\'' +
                ", description='" + description + '\'' +
                ", teacherfirstname='" + teacherfirstname + '\'' +
                ", teacherlastname='" + teacherlastname + '\'' +
                '}';
    }
}
