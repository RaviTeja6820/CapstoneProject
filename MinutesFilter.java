package CourseraCapstoneNew;


/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int myMinutes1,myMinutes2;
	
	public MinutesFilter(int minutes1,int minutes2) {
		myMinutes1 = minutes1;
		myMinutes2 = minutes2;
	}
	
	@Override
	public boolean satisfies(String id) {
		return (MovieDatabase.getMinutes(id) >= myMinutes1 && MovieDatabase.getMinutes(id) <= myMinutes2);
	}

}
