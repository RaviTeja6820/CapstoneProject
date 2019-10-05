package CourseraCapstoneNew;


/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratedmoviesfull.csv", "C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratings.csv");
    }
    public SecondRatings(String moviefile,String ratingsfile){
        FirstRatings FR = new FirstRatings();
        myMovies = FR.loadMovies(moviefile);
        myRaters = FR.loadRaters(ratingsfile);
    }
    public int getMovieSize(){
      return myMovies.size();
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
        for(Movie mv :myMovies){
            double value = getAverageByID(mv.getID(),minimalRaters);
            Rating rt = new Rating(mv.getID(),value);
            ratings.add(rt);
        }
        return ratings;
    }
    public String getTitle(String id){
        for(Movie mv :myMovies){
            int mvConvert = Integer.parseInt(mv.getID());
            int idConvert = Integer.parseInt(id);
            if(mvConvert == idConvert){
                return mv.getTitle();
            }
        }
        return "ID was not found";
    }
    public void printAverageRatings(){
        int minimalRaters = 12;
        ArrayList<Rating> rating = getAverageRatings(minimalRaters);
        Collections.sort(rating);
        for(Rating r :rating){
            if(r.getValue()!=0){
            System.out.println(r.getValue()+" "+getTitle(r.getItem()));}
        }
    }
    public String getID(String title){
        for(Movie mv :myMovies){
            if(mv.getTitle().equals(title)){
                return mv.getID();
            }
        }
        return "Title was not found";
    }
}
