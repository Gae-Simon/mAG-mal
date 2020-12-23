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
    private JScrollBar scrollBar1;

    public Gui (){
        add(rootPanel);
        setTitle("mAG-mal");
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
