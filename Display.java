import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
 
public class Display extends JFrame implements ActionListener
{
	String[] categoryStrings = {"Birthday", "Anniversary", "Meeting", "Appointment", "Other"};
	final JFrame mainApplicationFrame = new JFrame("Reminder Application");
	final JButton addButton = new JButton("Add Date");
	final JButton filterButton = new JButton("Filter");
	final JButton resetFilterButton = new JButton("Reset Filter");
	final JComboBox categoryPicklist = new JComboBox(categoryStrings);
	final JPanel mainPanel = new JPanel();
	final JPanel addButtonPanel = new JPanel();
	final JPanel filterPanel = new JPanel();
	final JTextArea list = new JTextArea(20, 20);
	
	DateChecker dateChecker = new DateChecker();
	
	public Display()
	{
		ArrayList<ImportantDate> dateList = List.loadList();
		
		list.setEditable(false);
		list.setText(dateList.toString());
		
		addButton.addActionListener(this);
		filterButton.addActionListener(this);
		resetFilterButton.addActionListener(this);
		
		mainPanel.setLayout(new BorderLayout());
		addButtonPanel.setLayout(new FlowLayout());
		filterPanel.setLayout(new FlowLayout());
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addButtonPanel.add(addButton);
		filterPanel.add(filterButton);
		filterPanel.add(categoryPicklist);
		filterPanel.add(resetFilterButton);
		mainPanel.add(addButtonPanel, BorderLayout.NORTH);
		mainPanel.add(list, BorderLayout.CENTER);
		mainPanel.add(filterPanel, BorderLayout.SOUTH);
		
		PasswordChecker loginScreen = new PasswordChecker(mainApplicationFrame);
		loginScreen.setVisible(true);
		
		mainApplicationFrame.getContentPane().add(mainPanel);
		mainApplicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainApplicationFrame.setSize(600, 600);
		mainApplicationFrame.setLayout(new FlowLayout());
		mainApplicationFrame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		Display display = new Display();
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
		
		if(e.getSource() == resetFilterButton)
		{
			System.out.println("Reset Filter button pressed");
		}
	}
}