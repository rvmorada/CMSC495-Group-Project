import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class List {
    
    public static ArrayList<ImportantDate> dateList;
    

public static ArrayList<ImportantDate> loadList() {
        dateList = new ArrayList<>();
        try {
        
        FileInputStream fis = new FileInputStream("Dates.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        dateList = (ArrayList<ImportantDate>) ois.readObject();
        ois.close();
        fis.close();
        }
        catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        }
        
	return dateList;
}


public static void write(){
	try {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Dates.txt"));
		out.writeObject(dateList);
		out.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	
}
