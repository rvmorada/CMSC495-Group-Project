import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class List {
	public static void main(String[] args) throws FileNotFoundException, IOException{

	ArrayList<ImportantDate> dateList = loadList();
	System.out.println(dateList);
}

public static ArrayList<ImportantDate> loadList(){
	ArrayList<ImportantDate> dateList = new ArrayList<ImportantDate>();
	try {
		FileInputStream fis = new FileInputStream("Dates.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		dateList = (ArrayList<ImportantDate>) ois.readObject();
		ois.close();
		fis.close();
	} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	return dateList;
}
public void write(ArrayList<ImportantDate> dateList){
	try {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Dates.txt"));
		out.writeObject(dateList);
		out.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	
}
