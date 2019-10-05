package CourseraCapstoneNew;



/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
public class MovieRunnerAverage {
public void printAverageRatings(){
    SecondRatings SR = new SecondRatings();
    System.out.println("Total number of movies that are rated are "+SR.getMovieSize() + " and rated by " + SR.getRaterSize() + " raters");
    ArrayList<Rating> list = SR.getAverageRatings(20);
    int count =0;
    for(Rating rt :list){
        if(rt.getValue()>0){
        //System.out.println(rt);
        count++;
        }
    }
    System.out.println("movies that are rated by more than 50 raters are "+count);
    SR.printAverageRatings();
}
public void getAverageRatingOneMovie(){
    SecondRatings SR = new SecondRatings();
    ArrayList<Rating> list = SR.getAverageRatings(3);
    String movieName = "Vacation";
    for(Rating r :list){
        int itemConvert = Integer.parseInt(r.getItem());
        String id = SR.getID(movieName);
        int idConvert = Integer.parseInt(id);
        if(itemConvert == idConvert){
            System.out.println(movieName + " " + r.getValue());
        }
    }
}
}
