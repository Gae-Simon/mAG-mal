package me.simon.mAGmal;

import javax.swing.*;

public class GUI extends JFrame {


    public JButton schülerEinlesenButton;
    private JTextArea textArea1;
    private JPanel rootPanel;

    public GUI (){
        add(rootPanel);
        setTitle("mAG-mal");
        setSize(400,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
