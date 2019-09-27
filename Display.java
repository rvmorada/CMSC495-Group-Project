import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
 
public class Display extends JFrame implements ActionListener
{
	String[] categoryStrings = {"Birthday", "Anniversary", "Meeting", "Appointment", "Other"};
	final JFrame mainApplicationFrame = new JFrame("Reminder Application");
	final JFrame addDateFrame = new JFrame("Add Date");
	final JFrame displayMessageFrame = new JFrame("!");
	final JButton addButton = new JButton("Add Date");
	final JButton addDateFrameButton = new JButton("Add Date");
	final JButton filterButton = new JButton("Filter");
	final JButton resetFilterButton = new JButton("Reset Filter");
	final JComboBox categoryFilterPicklist = new JComboBox(categoryStrings);
	final JComboBox categoryPicklist = new JComboBox(categoryStrings);
	final JPanel mainPanel = new JPanel();
	final JPanel addButtonPanel = new JPanel();
	final JPanel filterPanel = new JPanel();
	final JPanel addDatePanel = new JPanel();
	final JPanel addDateFieldPanel = new JPanel();
	final JPanel addDescriptionFieldPanel = new JPanel();
	final JPanel addCategoryFieldPanel = new JPanel();
	final JTextArea list = new JTextArea(20, 20);
	final JTextArea messageText = new JTextArea(1, 20);
	final JTextArea dateText = new JTextArea(1,10);
	final JTextArea descriptionText = new JTextArea(3,10);
	final JLabel dateFieldLabel = new JLabel("Date");
	final JLabel descriptionFieldLabel = new JLabel("Description");
	final JLabel categoryFieldLabel = new JLabel("Category");
	final JScrollPane descriptionTextScrollPane = new JScrollPane(descriptionText);
	
	DateChecker dateChecker = new DateChecker();
	DateAdder dateAdder = new DateAdder();
	
	ArrayList<ImportantDate> dateList;
	public Display()
	{
		dateList = List.loadList();
		
		list.setEditable(false);
		list.setText(dateList.toString());
		messageText.setEditable(false);		
		
		addButton.addActionListener(this);
		filterButton.addActionListener(this);
		resetFilterButton.addActionListener(this);
		addDateFrameButton.addActionListener(this);
		
		//build panel for main application frame
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
		
		//build panel for add date frame
		//addDatePanel.setLayout(new FlowLayout());
		addDatePanel.setLayout(new BorderLayout());
		addDateFieldPanel.setLayout(new FlowLayout());
		addDescriptionFieldPanel.setLayout(new FlowLayout());
		addCategoryFieldPanel.setLayout(new FlowLayout());
		addDateFieldPanel.add(dateFieldLabel);
		addDateFieldPanel.add(dateText);
		addDescriptionFieldPanel.add(descriptionFieldLabel);
		addDescriptionFieldPanel.add(descriptionTextScrollPane);
		addCategoryFieldPanel.add(categoryFieldLabel);
		addCategoryFieldPanel.add(categoryPicklist);
//		addDatePanel.add(addDateFrameButton);
//		addDatePanel.add(addDateFieldPanel);
//		addDatePanel.add(addDescriptionFieldPanel);
//		addDatePanel.add(addCategoryFieldPanel);
		addDatePanel.add(addDateFrameButton,BorderLayout.SOUTH);
		addDatePanel.add(addDateFieldPanel, BorderLayout.NORTH);
		addDatePanel.add(addDescriptionFieldPanel, BorderLayout.CENTER);
		addDatePanel.add(addCategoryFieldPanel, BorderLayout.WEST);
		
		PasswordChecker loginScreen = new PasswordChecker(mainApplicationFrame);
		loginScreen.setVisible(true);
				
		//build add date frame
		addDateFrame.getContentPane().add(addDatePanel);
		addDateFrame.setSize(400, 400);
		addDateFrame.setLayout(new FlowLayout());
		
		//build display message frame
		displayMessageFrame.getContentPane().add(messageText);
		displayMessageFrame.setSize(400, 200);
		displayMessageFrame.setLayout(new FlowLayout());
		
		//build main application frame
		mainApplicationFrame.getContentPane().add(mainPanel);
		mainApplicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainApplicationFrame.setSize(600, 600);
		mainApplicationFrame.setLayout(new FlowLayout());
		mainApplicationFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == addButton)
		{
			System.out.println("Add button pressed");
			addDateFrame.setVisible(true);			
		}
		
		if(e.getSource() == addDateFrameButton)
		{
			System.out.println("Add Date Panel button pressed");
			this.displayMessage(dateAdder.addDate(dateText.getText(), descriptionText.getText(), categoryPicklist.getSelectedItem().toString(), dateList));
			dateList = List.loadList();
			list.setText(dateList.toString());
			//System.out.println(categoryPicklist.getSelectedItem().toString());
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
	
	//use this.displayMessage("") to call this
	public void displayMessage(String message)
	{
		messageText.setText(message);
		displayMessageFrame.setVisible(true);		
	}
	
	public static void main(String[] args) 
	{
		Display display = new Display();
	}

	
}