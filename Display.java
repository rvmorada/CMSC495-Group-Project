import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
 
public class Display extends JFrame implements ActionListener
{
	final JFrame frame = new JFrame("Reminder Application");
	final JButton addButton = new JButton("Add Date");
	final JButton filterButton = new JButton("Filter");
	final JPanel mainPanel = new JPanel();
	final JPanel buttonPanel = new JPanel();
	final JTextArea list = new JTextArea(20, 20);
	
	public Display()
	{
		ArrayList<ImportantDate> dateList = List.loadList();
		
		list.setEditable(false);
		list.setText(dateList.toString());
		
		addButton.addActionListener(this);
		filterButton.addActionListener(this);
		
		mainPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(new FlowLayout());
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		buttonPanel.add(addButton);
		buttonPanel.add(filterButton);
		mainPanel.add(buttonPanel, BorderLayout.PAGE_START);
		mainPanel.add(list, BorderLayout.PAGE_END);
		
		PasswordChecker loginScreen = new PasswordChecker(frame);
		loginScreen.setVisible(true);
		
		frame.getContentPane().add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		Display display = new Display();
		//display.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == addButton)
		{
			System.out.println("Add button pressed");
		}
		
		if(e.getSource() == filterButton)
		{
			System.out.println("Filter button pressed");
		}
	}
}