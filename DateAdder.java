import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateAdder 
{
	DateChecker dateChecker = new DateChecker();
	//List list = new List();
	
	public String addDate(String date, String description, String category)
	{
                Date d;
                ImportantDate newDate;
		if((d = dateChecker.isValidDate(date)) != null)
		{
                        newDate = new ImportantDate(d, description, category);
                        List.dateList.add(newDate);
                        List.write();
                        return "Date successfully added!";
                }
		return "Invalid Date. Please enter a valid date.";
	}
        
        
}
