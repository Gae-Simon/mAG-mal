package me.simon.mAGmal;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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
        System.out.println();
        System.out.println("WGs:");
        for (WG wg : wgList) {
            System.out.println(wg);
        }
        System.out.println();
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
