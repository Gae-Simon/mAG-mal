package me.simon.mAGmal;

import java.io.File;

public class Main {

    //--> main Method
    public static void main(String[] args) {

        //--> create a new file with pathname
        File f = new File("C:\\Users\\simon\\OneDrive\\Desktop\\test.txt");

        //--> give class FileReader the file f
        FileReader dr = new FileReader(f);

        //--> start output of the file
        dr.output();

        //--> close the file
        dr.close();
    }
}
