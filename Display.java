import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Display extends JFrame implements ActionListener {
    private File pwd = new File("pwd.txt");
    String[] categoryStrings = {
        "Birthday",
        "Anniversary",
        "Meeting",
        "Appointment",
        "Other"
    };
    String[] filterCategoryStrings = {
        "All",
        "Birthday",
        "Anniversary",
        "Meeting",
        "Appointment",
        "Other"
    };
    final JFrame mainApplicationFrame = new JFrame("Important Date Application");
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
    final JPanel addFrameButtonPanel = new JPanel();
    final JPanel editDatePanel = new JPanel();
    final JPanel editDateFieldPanel = new JPanel();
    final JPanel editDescriptionFieldPanel = new JPanel();
    final JPanel editCategoryFieldPanel = new JPanel();
    final JPanel editFrameButtonPanel = new JPanel();
    final JPanel listOuterPanel = new JPanel();
    final JScrollPane listScrollPane;
    final JPanel listPanel = new JPanel();
    final JPanel listHeaderPanel = new JPanel();
    final JPanel listHeaderOuterPanel = new JPanel();
    final String[] listHeadings = {
        "Date",
        "Description",
        "Category",
        "Action"
    };
    final JTextArea messageText = new JTextArea(1, 20);
    final JTextArea addDateText = new JTextArea(1, 10);
    final JTextArea addDescriptionText = new JTextArea(3, 10);
    final JTextArea editDateText = new JTextArea(1, 10);
    final JTextArea editDescriptionText = new JTextArea(3, 10);
    final JLabel addDateFieldLabel = new JLabel("Date");
    final JLabel addDescriptionFieldLabel = new JLabel("Description");
    final JLabel addCategoryFieldLabel = new JLabel("Category");
    final JLabel editDateFieldLabel = new JLabel("Date");
    final JLabel editDescriptionFieldLabel = new JLabel("Description");
    final JLabel editCategoryFieldLabel = new JLabel("Category");
    final Font buttonFont = new Font("SansSerif", Font.BOLD, 12);
    final JScrollPane addDescriptionTextScrollPane = new JScrollPane(addDescriptionText);
    final JScrollPane editDescriptionTextScrollPane = new JScrollPane(editDescriptionText);
    final Border empty5 = new EmptyBorder(5, 5, 5, 5);
    final Border empty0 = new EmptyBorder(0, 0, 0, 0);
    final Border empty10 = new EmptyBorder(10, 10, 10, 10);

    DateChecker dateChecker = new DateChecker();
    DateAdder dateAdder = new DateAdder();
    DateEditor dateEditor = new DateEditor();
    ImportantDate toBeEdited;
    DateDeleter dateDeleter = new DateDeleter();
    List list = new List();


    public Display() throws IOException {

        List.loadList();

        messageText.setEditable(false);
        categoryFilterPicklist.setSelectedIndex(0);


        // Action Listeners
        filterButton.addActionListener(this);
        categoryFilterPicklist.addActionListener(this);
        resetFilterButton.addActionListener(this);
        addDateFrameButton.addActionListener(this);
        editDateFrameButton.addActionListener(this);
        addButton.addActionListener(this);

        // setting backgrounds to a white color
        mainApplicationFrame.setBackground(Color.white);
        categoryFilterPicklist.setBackground(Color.white);
        mainPanel.setBackground(Color.white);
        listOuterPanel.setBackground(Color.white);
        addButtonPanel.setBackground(Color.white);
        filterPanel.setBackground(Color.white);
        listPanel.setBackground(Color.white);
        mainPanel.setBackground(Color.green);
        listHeaderPanel.setBackground(Color.white);
        filterLabel.setBackground(Color.white);
        categoryFilterPicklist.setBackground(Color.white);


        // build panels for list 
        listOuterPanel.setLayout(new BorderLayout());
        listOuterPanel.setBorder(empty10);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        listScrollPane = new JScrollPane(listPanel);
        listScrollPane.setBackground(Color.white);
        listHeaderOuterPanel.setLayout(new BoxLayout(listHeaderOuterPanel, BoxLayout.PAGE_AXIS));
        listHeaderPanel.setLayout(new GridLayout(1, 4));
        listHeaderOuterPanel.setMaximumSize(new Dimension(1200, 30));
        listHeaderOuterPanel.add(listHeaderPanel);
        Dimension listSize = new Dimension(1200, 800);
        listOuterPanel.setMaximumSize(listSize);
        listHeaderPanel.setMinimumSize(new Dimension(300, 30));
        listHeaderPanel.setPreferredSize(new Dimension(500, 30));
        listHeaderPanel.setMaximumSize(new Dimension(1200, 30));
        mainPanel.setMaximumSize(listSize);
        listPanel.setMaximumSize(listSize);
        listScrollPane.setMaximumSize(listSize);
        listHeaderPanel.setBackground(Color.blue);
        listHeaderOuterPanel.setBackground(Color.white);
        // make list header
        for (String s: listHeadings) {
            JLabel heading = new JLabel(s);
            heading.setFont(new Font("SansSerif", Font.BOLD, 14));
            heading.setForeground(Color.white);
            heading.setBackground(Color.blue);
            listHeaderPanel.add(heading);
        }
        listOuterPanel.add(listHeaderOuterPanel, BorderLayout.PAGE_START);
        listOuterPanel.add(listScrollPane, BorderLayout.CENTER);


        // Add Date Button
        Dimension addButtonSize = new Dimension(150, 40);
        addButton.setFont(buttonFont);
        addButton.setMinimumSize(addButtonSize);
        addButton.setPreferredSize(addButtonSize);
        addButton.setMaximumSize(addButtonSize);
        addButtonPanel.setLayout(new FlowLayout());
        addButtonPanel.add(addButton);

        // Filter list panel
        filterPanel.setLayout(new FlowLayout());
        filterPanel.add(filterLabel);
        filterPanel.add(categoryFilterPicklist);

        // configure main application panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(empty0);
        listPanel.setBorder(empty0);
        listScrollPane.setBorder(empty0);
        mainPanel.add(addButtonPanel, BorderLayout.NORTH);
        mainPanel.add(listOuterPanel, BorderLayout.CENTER);
        mainPanel.add(filterPanel, BorderLayout.SOUTH);


        // configure add and edit date frames
        Dimension dateFieldSize = new Dimension(400, 30);
        Dimension descriptionFieldSize = new Dimension(400, 100);
        Dimension categoryFieldSize = new Dimension(400, 50);
        Dimension addEditButtonSize = new Dimension(300, 50);

        addDatePanel.setLayout(new BoxLayout(addDatePanel, BoxLayout.PAGE_AXIS));
        addDateFieldPanel.setLayout(new GridLayout(1, 2));
        addDateFieldPanel.setMaximumSize(dateFieldSize);
        addDateFieldPanel.setBorder(empty5);
        addDateFieldPanel.add(addDateFieldLabel);
        addDateFieldPanel.add(addDateText);
        addDescriptionFieldPanel.setLayout(new GridLayout(1, 2));
        addDescriptionFieldPanel.setMaximumSize(descriptionFieldSize);
        addDescriptionFieldPanel.setBorder(empty5);
        addDescriptionFieldPanel.add(addDescriptionFieldLabel);
        addDescriptionFieldPanel.add(addDescriptionTextScrollPane);
        addCategoryFieldPanel.setLayout(new GridLayout(1, 2));
        addCategoryFieldPanel.setMaximumSize(categoryFieldSize);
        addCategoryFieldPanel.setBorder(empty5);
        addCategoryFieldPanel.add(addCategoryFieldLabel);
        addCategoryFieldPanel.add(addCategoryPicklist);
        addFrameButtonPanel.setLayout(new GridLayout(1, 1));
        addFrameButtonPanel.setBorder(empty5);
        addFrameButtonPanel.setMinimumSize(addEditButtonSize);
        addFrameButtonPanel.setPreferredSize(addEditButtonSize);
        addFrameButtonPanel.setMaximumSize(addEditButtonSize);
        addDateFrameButton.setFont(buttonFont);
        addFrameButtonPanel.add(addDateFrameButton);
        addDatePanel.add(addDateFieldPanel);
        addDatePanel.add(addDescriptionFieldPanel);
        addDatePanel.add(addCategoryFieldPanel);
        addDatePanel.add(addFrameButtonPanel);

        editDatePanel.setLayout(new BoxLayout(editDatePanel, BoxLayout.PAGE_AXIS));
        editDateFieldPanel.setLayout(new GridLayout(1, 2));
        editDateFieldPanel.setMaximumSize(dateFieldSize);
        editDateFieldPanel.setBorder(empty5);
        editDateFieldPanel.add(editDateFieldLabel);
        editDateFieldPanel.add(editDateText);
        editDescriptionFieldPanel.setLayout(new GridLayout(1, 2));
        editDescriptionFieldPanel.setMaximumSize(descriptionFieldSize);
        editDescriptionFieldPanel.setBorder(empty5);
        editDescriptionFieldPanel.add(editDescriptionFieldLabel);
        editDescriptionFieldPanel.add(editDescriptionTextScrollPane);
        editCategoryFieldPanel.setLayout(new GridLayout(1, 2));
        editCategoryFieldPanel.setMaximumSize(categoryFieldSize);
        editCategoryFieldPanel.setBorder(empty5);
        editCategoryFieldPanel.add(editCategoryFieldLabel);
        editCategoryFieldPanel.add(editCategoryPicklist);
        editFrameButtonPanel.setLayout(new GridLayout(1, 1));
        editFrameButtonPanel.setBorder(empty5);
        editFrameButtonPanel.setMinimumSize(addEditButtonSize);
        editFrameButtonPanel.setPreferredSize(addEditButtonSize);
        editFrameButtonPanel.setMaximumSize(addEditButtonSize);
        editDateFrameButton.setFont(buttonFont);
        editFrameButtonPanel.add(editDateFrameButton);
        editDatePanel.add(editDateFieldPanel);
        editDatePanel.add(editDescriptionFieldPanel);
        editDatePanel.add(editCategoryFieldPanel);
        editDatePanel.add(editFrameButtonPanel);




        PasswordChecker loginScreen = new PasswordChecker(mainApplicationFrame);
        if (!pwd.exists()) {
            loginScreen.setPassword(mainApplicationFrame);
        } else {
            loginScreen.setVisible(true);

            if (!loginScreen.isSucceeded()) {
                System.exit(0);
            }

            //build add date frame
            addDateFrame.getContentPane().add(addDatePanel);
            addDateFrame.setSize(400, 300);

            //build edit date frame
            editDateFrame.getContentPane().add(editDatePanel);
            editDateFrame.setSize(400, 300);

            //build display message frame
            displayMessageFrame.getContentPane().add(messageText);
            displayMessageFrame.setSize(400, 200);

            //build main application frame
            mainApplicationFrame.getContentPane().add(mainPanel);
            mainApplicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainApplicationFrame.setSize(600, 600);
            mainApplicationFrame.setVisible(true);
            refreshList();
        }
    }

    @Override
    /**
     * Action Listeners for various application buttons.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            System.out.println("Add button pressed");
            addDateFrame.setVisible(true);
        } else if (e.getSource() == addDateFrameButton) {
            if ((addDateText.getText().isEmpty()) || (addDescriptionText.getText().isEmpty()) || (addCategoryPicklist.getSelectedIndex() == -1)) {
                System.out.println("Add Date Panel button pressed");
                this.displayMessage("All Fields Required");
            } else {
                this.displayMessage(dateAdder.addDate(addDateText.getText(), addDescriptionText.getText(), addCategoryPicklist.getSelectedItem().toString()));
                addDateFrame.dispose();
                categoryFilterPicklist.setSelectedIndex(0);
                clearInput(addDateText, addDescriptionText);
                refreshList();
            }
        } else if (e.getSource() == editDateFrameButton) {
            System.out.println("Edit Frame button clicked for " + toBeEdited.toString());
            this.displayMessage(dateEditor.editDate(editDateText.getText(), editDescriptionText.getText(), editCategoryPicklist.getSelectedItem().toString(), toBeEdited));
            editDateFrame.setVisible(false);
            refreshList();
        }
        // if filter selection is changed, refresh list
        else if (e.getSource() == categoryFilterPicklist) {
            System.out.println("Filter list changed to " + categoryFilterPicklist.getSelectedItem());
            refreshList();

        }

    }

    /**
     * clears text from the given JTextAreas
     * @param date
     * @param desc 
     */
    private void clearInput(JTextArea date, JTextArea desc) {
        date.setText("");
        desc.setText("");
    }

    //use this.displayMessage("") to call this
    private void displayMessage(String message) {
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

        for (ImportantDate d: List.dateList) {
            // if date matches filter criteria, or if filter is "all"
            if (d.getCategory().equals(filter) || filter.equals("All")) {
                //create panel to show date information
                JPanel datePanel = new JPanel(new GridLayout(1, 4));
                //Border raised = BorderFactory.createLineBorder(Color.black);
                datePanel.setMinimumSize(new Dimension(300, 40));
                datePanel.setPreferredSize(new Dimension(500, 40));
                datePanel.setMaximumSize(new Dimension(1200, 40));
                datePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

                // create components to go into datePanel
                JLabel date = new JLabel(d.getDateAsString());

                JLabel cat = new JLabel(d.getCategory());
                JLabel description = new JLabel(d.getDescription());
                JPanel buttons = new JPanel(new GridLayout(1, 2));

                JButton editButton = new JButton("edit");
                editButton.setFont(buttonFont);
                JButton deleteButton = new JButton("delete");
                deleteButton.setFont(buttonFont);
                buttons.add(editButton);
                buttons.add(deleteButton);

                buttons.setBackground(Color.white);
                datePanel.setBackground(Color.white);
                // add action listeners to edit and delete buttons 
                editButton.addActionListener(e-> {                   
                	editDate(d);
                });
                deleteButton.addActionListener(e-> {
                    deleteDate(d);
                });

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
        if (dialogResult == 0) {
            // delete date (if possible) and show message
            displayMessage(dateDeleter.deleteDate(d));
            refreshList();
        }

    }

    public static void main(String[] args) throws IOException {
        Display display = new Display();
    }


}