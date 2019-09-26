import java.io.Serializable;

public class ImportantDate implements Serializable{
	String date, description, category;


ImportantDate(String d, String desc, String cat){
	date = d;
	description = desc;
	category = cat;
}

void edit(String d, String desc, String cat){
	date = d;
	description = desc;
	category = cat;
}

public String toString(){
	return date + " " + description + " " + category;
}
public String getDate() {
	return date;
}

public void setDate(String date) {
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

}
