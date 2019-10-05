//import java.util.ArrayList;


public class DateDeleter {
        public String deleteDate(ImportantDate date) {
		
		if(List.dateList.remove(date)) {
                    List.write();
                    return "You have successfully deleted this date: " + date;
                }
                return "Date unable to be deleted";
			
		//}
		
		
	}
		
}

