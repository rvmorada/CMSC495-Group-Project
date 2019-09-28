import java.awt.GridLayout;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImportantDate implements Serializable, Comparable<ImportantDate> {
        private static final long serialVersionUID = 6529685098267757690L;
        Date date;
	String description, category;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        JPanel datePanel = new JPanel(new GridLayout(1,5));
        JLabel dateLabel;
        JLabel descriptionLabel;
        JLabel categoryLabel;
        JButton editButton = new JButton("edit");
        JButton deleteButton = new JButton("delete");


ImportantDate(Date d, String desc, String cat){
	date = d;
	description = desc;
	category = cat;
        
}

public JPanel getPanel() {
    return datePanel;
}

void edit(Date d, String desc, String cat){
	date = d;
	description = desc;
	category = cat;
}

public String toString(){
	return getDateAsString() + " " + description + " " + category;
}
public String getDateAsString() {
	return dateFormat.format(date);
}

public Date getDate() {
        return date;
}

public void setDate(Date date) {
	this.date = date;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}


@Override
public int compareTo(ImportantDate o) {
    return getDate().compareTo(o.getDate());
}



public void delete() {
    System.out.println("delete " + getDateAsString());
}
}