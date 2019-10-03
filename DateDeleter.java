import java.util.ArrayList;


public class DateDeleter {
ArrayList<ImportantDate> deleteDate(List list,ImportantDate date) {


	//deleting the selected date
	for(int i=0;i<list.dateList.size();i++) {
		System.out.println(list.dateList.get(i));
		
		if(String.valueOf(date).equals(String.valueOf(list.dateList.get(i)))) {
			list.dateList.remove(i);
			break;
			
		}
		
		
	}
	
	//returning the new date list
	return(list.dateList);
		
		
	  }
}
