import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class DateChecker 
{    
    public boolean isValidDate(String dateStr) {
        DateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        simpleDateFormat.setLenient(false);
        try {
        	simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
