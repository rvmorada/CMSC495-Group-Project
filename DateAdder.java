import java.util.ArrayList;

public class DateAdder 
{
	DateChecker dateChecker = new DateChecker();
	List list = new List();
	
	public String addDate(String date, String description, String category, ArrayList<ImportantDate> dateList)
	{
		if(dateChecker.isValidDate(date))
		{
			ImportantDate newDate = new ImportantDate(date, description, category);
			dateList.add(newDate);
			list.write(dateList);
			return "Date successfully added!";
		}
		return "Invalid Date. Please enter a valid date.";
	}
}
