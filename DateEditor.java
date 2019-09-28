import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateEditor 
{
	DateChecker dateChecker = new DateChecker();
	//List list = new List()
        
        public String editDate(String date, String description, String category, ImportantDate id) {
            Date d;
            if((d = dateChecker.isValidDate(date)) != null) 
            {
                    id.edit(d,description, category);
                    List.write();
                    return "Date successfully edited!";
                    
            }
            return "Invalid Date. Please enter a valid date.";
        }
        
}
