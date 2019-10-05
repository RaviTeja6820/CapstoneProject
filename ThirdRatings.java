package CourseraCapstoneNew;


/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratings.csv");
    }
    public ThirdRatings(String ratingsfile){
        FirstRatings FR = new FirstRatings();
        myRaters = FR.loadRaters(ratingsfile);
    }
    public int getRaterSize(){
        return myRaters.size();
    }
    private double getAverageByID(String id,int minimalRaters){
        double count = 0,avg = 0;
        for(Rater r :myRaters){
            ArrayList<String> list = r.getItemsRated();
            for(String mv :list){
                int mvConvert = Integer.parseInt(mv);
                int idConvert = Integer.parseInt(id);
                if(mvConvert == idConvert){
                    avg += r.getRating(mv);
                    count+=1;
                    //System.out.println(avg + " " +count);
                }
            }
        }
        if(count >= minimalRaters)
        return (avg/count);
        return 0.0;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String mv :movies){
            double value = getAverageByID(mv,minimalRaters);
            Rating rt = new Rating(mv,value);
            ratings.add(rt);
        }
        return ratings;
    }
    public void printAverageRatings(){
        int minimalRaters = 35;
        ArrayList<Rating> rating = getAverageRatings(minimalRaters);
        Collections.sort(rating);
        for(Rating r :rating){
            if(r.getValue()!=0){
            System.out.println(r.getValue()+" "+MovieDatabase.  getTitle(r.getItem()));}
        }
    }
    public ArrayList<Rating> getAverageRatingByFilter(int minimumRaters,Filter f){
    ArrayList<Rating> raters = new ArrayList<Rating>();
    ArrayList<String> list = MovieDatabase.filterBy(f);
    for(String s : list){
            double value = getAverageByID(s,minimumRaters);
            Rating rt = new Rating(s,value);
            raters.add(rt);
        }
        return raters;
    }
}
