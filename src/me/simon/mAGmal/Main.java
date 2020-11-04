package me.simon.mAGmal;

import java.io.File;

public class Main {

    //--> main Method
    public static void main(String[] args) {

        //--> create a new file with pathname
        File file = new File("C://Users//simon//OneDrive//Desktop//test.txt");

        //--> give class FileReader the file f and start output of the file and close the file
        try (FileReader fileReader = new FileReader(file)) {
            fileReader.output();
        }
    }
}
