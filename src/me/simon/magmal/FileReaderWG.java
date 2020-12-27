package me.simon.magmal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderWG implements AutoCloseable {

    // --> create Scanner
    private Scanner filereaderWG;

    // WGFileReader --> give scanner a name and file and check if the file is available
    public FileReaderWG(File wGFile) {
        try {
            filereaderWG = new Scanner(wGFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // --> output of the data / create a new object / save the information of each line in the object / output-Method from WG
    public void readingWGs() {
        while (filereaderWG.hasNext()) {
            WG wg = new WG();
            wg.description = filereaderWG.next();
            if (filereaderWG.hasNext()){
                wg.teacherfirstname = filereaderWG.next();
            }
            if (filereaderWG.hasNext()){
                wg.teacherlastname = filereaderWG.next();
            }
            WG.saveWG(wg);
        }
        WG.outputWG();

    }

    @Override
    public void close() {
        filereaderWG.close();
    }


}
