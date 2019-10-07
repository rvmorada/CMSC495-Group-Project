import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class Display extends JFrame implements ActionListener
{
	private File pwd = new File("pwd.txt");
	String[] categoryStrings = {"Birthday", "Anniversary", "Meeting", "Appointment", "Other"};
        String[] filterCategoryStrings = {"All", "Birthday", "Anniversary", "Meeting", "Appointment", "Other"};
	final JFrame mainApplicationFrame = new JFrame("Reminder Application");
	final JFrame addDateFrame = new JFrame("Add Date");
        final JFrame editDateFrame = new JFrame("Edit Date");
	final JFrame displayMessageFrame = new JFrame("!");
	
	final JButton addButton = new JButton("Add Date");
	final JButton addDateFrameButton = new JButton("Add Date");
        final JButton editDateFrameButton = new JButton("Edit Date");
	final JButton filterButton = new JButton("Filter");
	final JButton resetFilterButton = new JButton("Reset Filter");
        final JLabel filterLabel = new JLabel("Filter List by Category:");
	final JComboBox categoryFilterPicklist = new JComboBox(filterCategoryStrings);
	final JComboBox addCategoryPicklist = new JComboBox(categoryStrings);
        final JComboBox editCategoryPicklist = new JComboBox(categoryStrings);
	final JPanel mainPanel = new JPanel();
	final JPanel addButtonPanel = new JPanel();
	final JPanel filterPanel = new JPanel();
	final JPanel addDatePanel = new JPanel();
        final JPanel addDateFieldPanel = new JPanel();
	final JPanel addDescriptionFieldPanel = new JPanel();
	final JPanel addCategoryFieldPanel = new JPanel();
        final JPanel editDatePanel = new JPanel();
        final JPanel editDateFieldPanel = new JPanel();
        final JPanel editDescriptionFieldPanel = new JPanel();
        final JPanel editCategoryFieldPanel = new JPanel();
        final JPanel listOuterPanel = new JPanel();
        final JScrollPane listScrollPane;
	final JPanel listPanel = new JPanel();
        final JPanel listHeaderPanel = new JPanel();
        final String[] listHeadings = {"Date", "Description", "Category", "Action"};
	final JTextArea messageText = new JTextArea(1, 20);
	final JTextArea addDateText = new JTextArea(1,10);
	final JTextArea addDescriptionText = new JTextArea(3,10);
        final JTextArea editDateText = new JTextArea(1,10);
        final JTextArea editDescriptionText = new JTextArea(3,10);
	final JLabel addDateFieldLabel = new JLabel("Date");
	final JLabel addDescriptionFieldLabel = new JLabel("Description");
	final JLabel addCategoryFieldLabel = new JLabel("Category");
        final JLabel editDateFieldLabel = new JLabel("Date");
	final JLabel editDescriptionFieldLabel = new JLabel("Description");
	final JLabel editCategoryFieldLabel = new JLabel("Category");
	final JScrollPane addDescriptionTextScrollPane = new JScrollPane(addDescriptionText);
        final JScrollPane editDescriptionTextScrollPane = new JScrollPane(editDescriptionText);
	
	DateChecker dateChecker = new DateChecker();
	DateAdder dateAdder = new DateAdder();
        DateEditor dateEditor = new DateEditor();
        ImportantDate toBeEdited;
        DateDeleter dateDeleter=new DateDeleter(); 	
    	List list =new List();
	
	//ArrayList<ImportantDate> dateList;
	public Display() throws IOException
	{

                List.loadList();
		//dateList = List.loadList();
      
		messageText.setEditable(false);		
		categoryFilterPicklist.setSelectedIndex(0);
		
		filterButton.addActionListener(this);
                categoryFilterPicklist.addActionListener(this);
		resetFilterButton.addActionListener(this);
		addDateFrameButton.addActionListener(this);
                editDateFrameButton.addActionListener(this);
               //setting backgrounds to a white color
            mainApplicationFrame.setBackground(Color.white);
        	categoryFilterPicklist.setBackground(Color.white);
        	mainPanel.setLayout(new BorderLayout());
        	mainPanel.setBackground(Color.white);
        	listOuterPanel.setBackground(Color.white);
			addButtonPanel.setBackground(Color.white);
			filterPanel.setBackground(Color.white);
			listPanel.setBackground(Color.white);
			mainPanel.setBackground(Color.white);
			listHeaderPanel.setBackground(Color.white);
			filterLabel.setBackground(Color.white);
			categoryFilterPicklist.setBackground(Color.white);
		
		//build panel for main application frame
                
                
        		addButton.addActionListener(this); 
        		
			
		addButtonPanel.setLayout(new FlowLayout());
		filterPanel.setLayout(new FlowLayout());

                listOuterPanel.setLayout(new BorderLayout());
                listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
                listScrollPane = new JScrollPane(listPanel);
                listScrollPane.setMinimumSize(new Dimension(500,300));
                listScrollPane.setPreferredSize(new Dimension(550,450));
                listScrollPane.setMaximumSize(new Dimension(700,500));
                listScrollPane.setBackground(Color.white);
                listHeaderPanel.setLayout(new GridLayout(1,4));
                listHeaderPanel.setMinimumSize(new Dimension(300,40));
                listHeaderPanel.setPreferredSize(new Dimension(500,40));
                listHeaderPanel.setMaximumSize(new Dimension(700,40));
                listHeaderPanel.setBackground(Color.blue);
                for (String s : listHeadings) {
                    JLabel heading = new JLabel(s);
                   
                    heading.setForeground(Color.white);
                    heading.setBackground(Color.blue);
                    listHeaderPanel.add(heading);
                }
                listOuterPanel.add(listHeaderPanel, BorderLayout.PAGE_START);
                listOuterPanel.add(listScrollPane, BorderLayout.CENTER);
                
                addButtonPanel.add(addButton, BorderLayout.PAGE_START);
                filterPanel.add(filterLabel);
		filterPanel.add(categoryFilterPicklist);
                
		mainPanel.add(addButtonPanel, BorderLayout.NORTH);
		mainPanel.add(listOuterPanel, BorderLayout.CENTER);
		mainPanel.add(filterPanel, BorderLayout.SOUTH);
		
		//build panel for add date frame

		
		addDatePanel.setLayout(new BoxLayout(addDatePanel, BoxLayout.PAGE_AXIS));
		addDateFieldPanel.add(addDateFieldLabel);
		addDateFieldPanel.add(addDateText);
		addDescriptionFieldPanel.add(addDescriptionFieldLabel);
		addDescriptionFieldPanel.add(addDescriptionTextScrollPane);
		addCategoryFieldPanel.add(addCategoryFieldLabel);
		addCategoryFieldPanel.add(addCategoryPicklist);
		addDatePanel.add(addDateFieldPanel);
		addDatePanel.add(addDescriptionFieldPanel);
		addDatePanel.add(addCategoryFieldPanel);
		addDatePanel.add(addDateFrameButton);
                
                //build panel for edit date frame
                editDatePanel.setLayout(new BoxLayout(editDatePanel, BoxLayout.PAGE_AXIS));
		editDateFieldPanel.add(editDateFieldLabel);
		editDateFieldPanel.add(editDateText);
		editDescriptionFieldPanel.add(editDescriptionFieldLabel);
		editDescriptionFieldPanel.add(editDescriptionTextScrollPane);
		editCategoryFieldPanel.add(editCategoryFieldLabel);
		editCategoryFieldPanel.add(editCategoryPicklist);
                editDatePanel.add(editDateFieldPanel);
                editDatePanel.add(editDescriptionFieldPanel);
                editDatePanel.add(editCategoryFieldPanel);
                editDatePanel.add(editDateFrameButton);
                
                
        
        
		PasswordChecker loginScreen = new PasswordChecker(mainApplicationFrame);
		if(!pwd.exists()){
			loginScreen.setPassword(mainApplicationFrame);
		}
		else{
		
			loginScreen.setVisible(true);
		
		
		
		if(!loginScreen.isSucceeded()){
				System.exit(0);
		}
		//build add date frame
		addDateFrame.getContentPane().add(addDatePanel);
		addDateFrame.setSize(400, 400);
		addDateFrame.setLayout(new FlowLayout());
                
                //build edit date frame
		editDateFrame.getContentPane().add(editDatePanel);
		editDateFrame.setSize(400, 400);
		editDateFrame.setLayout(new FlowLayout());
		
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
                refreshList();
		}}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == addButton)
		{
			System.out.println("Add button pressed");
			addDateFrame.setVisible(true);			
		} 
                else if(e.getSource() == addDateFrameButton)
		{
			System.out.println("Add Date Panel button pressed");
			this.displayMessage(dateAdder.addDate(addDateText.getText(), addDescriptionText.getText(), addCategoryPicklist.getSelectedItem().toString()));
			addDateFrame.dispose();
                        categoryFilterPicklist.setSelectedIndex(0);
                        clearInput(addDateText, addDescriptionText);
                        refreshList();
		} 
                else if(e.getSource() == editDateFrameButton) {
                        System.out.println("Edit Frame button clicked for " + toBeEdited.toString());
                        this.displayMessage(dateEditor.editDate(editDateText.getText(), editDescriptionText.getText(), editCategoryPicklist.getSelectedItem().toString(), toBeEdited));
                        editDateFrame.setVisible(false);
                        refreshList();
                }
                // if filter selection is changed, refresh list
                else if(e.getSource() == categoryFilterPicklist)
		{
                        System.out.println("Filter list changed to " + categoryFilterPicklist.getSelectedItem());
                        refreshList();
			
		}

	}
	
	private void clearInput(JTextArea date, JTextArea desc){
		date.setText("");
		desc.setText("");
	}
	
	//use this.displayMessage("") to call this
	private void displayMessage(String message)
	{
		JOptionPane.showMessageDialog(displayMessageFrame, message, "Message", JOptionPane.INFORMATION_MESSAGE);	
	}
        
        /**
         * Filters and displays date information in list form.
         */
        private void refreshList() {
            // get the selected filter from the picklist
            String filter = categoryFilterPicklist.getSelectedItem().toString();
            // sort List in chronological order 
            List.dateList.sort(null);
            // start from blank slate
            listPanel.removeAll();
            
            for (ImportantDate d : List.dateList) {
                // if date matches filter criteria, or if filter is "all"
                if (d.getCategory().equals(filter) || filter.equals("All")) {
                    //create panel to show date information
                    JPanel datePanel = new JPanel(new GridLayout(1,4));
                    Border raised = BorderFactory.createEtchedBorder();
                    datePanel.setMinimumSize(new Dimension(300,40));
                    datePanel.setPreferredSize(new Dimension(500,40));
                    datePanel.setMaximumSize(new Dimension(700,40));
                    datePanel.setBorder(raised);
                    
                    // create components to go into datePanel
                    
                    JLabel date = new JLabel(d.getDateAsString());
                    
                    JLabel cat = new JLabel(d.getCategory());
                    JLabel description = new JLabel(d.getDescription());
                    JPanel buttons = new JPanel(new GridLayout(1,2));
                    JButton editButton = new JButton("edit");
                    JButton deleteButton = new JButton("delete");
                    buttons.add(editButton);
                    buttons.add(deleteButton);
                    buttons.setBackground(Color.white);
                    datePanel.setBackground(Color.white);
                    // add action listeners to edit and delete buttons 
                    editButton.addActionListener(e -> {editDate(d);});
                    deleteButton.addActionListener(e -> {deleteDate(d);});

                    // add components to datePanel
                    
                    datePanel.add(date);
                    datePanel.add(description);
                    datePanel.add(cat);
                    datePanel.add(buttons);

                    // add datePanel to listPanel, so it will show in the main window.
                    listPanel.add(datePanel);
                    
                }
            }
            // refresh components to show accurate info
            mainApplicationFrame.revalidate();
            listPanel.repaint();
        }
        
        /**
         * Shows edit window with current date info, allows user to edit info and
         * submit. 
         * @param d : ImportantDate to be edited
         */
        private void editDate(ImportantDate d) {
            toBeEdited = d;
            System.out.println("Edit Button pressed for " + d);
            
            // set info in edit window 
            editDateText.setText(d.getDateAsString());
            editDescriptionText.setText(d.getDescription());
            editCategoryPicklist.setSelectedItem(d.getCategory());
            
            // show edit window
            editDateFrame.setVisible(true);
        }
        
        /**
         * Deletes date from list after user confirmation
         * @param d 
         */
        private void deleteDate(ImportantDate d) {
            System.out.println("Delete button pressed for " + d);
            
            int dialogButton = JOptionPane.OK_CANCEL_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Deleting date: \n" + d + "\nPress OK to confirm", "Are you sure?", dialogButton);
            if(dialogResult == 0) {
                // delete date (if possible) and show message
          	displayMessage(dateDeleter.deleteDate(d));
        	refreshList();	
            }
            
               

        }
	
	public static void main(String[] args) throws IOException 
	{
		Display display = new Display();
	}

	
}
