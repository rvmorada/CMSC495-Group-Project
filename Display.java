import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
 
public class Display {
    public static void main(String[] args) {
    	ArrayList<ImportantDate> dateList = List.loadList();
    	
    	final JFrame frame = new JFrame("Reminder Application");
        final JButton add = new JButton("Add Date");
        final JButton filter = new JButton("Filter");
        final JPanel mp = new JPanel();
        final JPanel bp = new JPanel();
        final JTextArea list = new JTextArea(20, 20);
        
        list.setEditable(false);
        list.setText(dateList.toString());
        
        mp.setLayout(new BorderLayout());
        bp.setLayout(new FlowLayout());
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bp.add(add);
        bp.add(filter);
        mp.add(bp, BorderLayout.PAGE_START);
        mp.add(list, BorderLayout.PAGE_END);
        
        PasswordChecker loginScreen = new PasswordChecker(frame);
        loginScreen.setVisible(true);
        
        frame.getContentPane().add(mp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }
}