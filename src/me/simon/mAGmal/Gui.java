package me.simon.mAGmal;

import javax.swing.*;

public class Gui extends JFrame {


    public JButton schuelerEinlesenButton;
    public JTextArea textArea1;
    private JPanel rootPanel;
    public JButton AGEinlesenButton;
    public JButton lehrerZusammenMitAGButton;
    public JButton zuordnungSchuelerZuAGButton;
    public JButton ausgabeAGMitgliederButton;
    public JButton lehrerEinlesenButton;

    public Gui (){
        add(rootPanel);
        setTitle("mAG-mal");
        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
