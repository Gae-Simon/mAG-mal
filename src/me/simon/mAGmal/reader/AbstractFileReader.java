package me.simon.mAGmal.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractFileReader<T> implements AutoCloseable {

    // --> create scanner
    private Scanner scanner;
    private List<T> list;

    // Constructor --> give scanner a name and file and check if the file is available
    public AbstractFileReader(File file) {
        this.list = new LinkedList<>();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected abstract void read(final Scanner scanner);
    public abstract void save(final Connection connection, final T obj);

    public void read() {
        this.read(this.scanner);
    }

    public void add (final T t) {
        this.list.add(t);
    }

    public List<T> getList() {
        return list;
    }

    // --> close the file
    @Override
    public void close() {
        scanner.close();
    }
}