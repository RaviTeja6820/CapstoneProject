package CourseraCapstoneNew;


/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    
    public FourthRatings() {
        // default constructor
        MovieDatabase.initialize("C:\\Users\\Ravi Teja L\\CourseraCapstoneNew\\data\\ratedmoviesfull.csv");
        RaterDatabase.initialize("C:\\Users\\Ravi Teja L\\CourseraCapstoneNew\\data\\ratings.csv");
        //MovieDatabase.initialize("ratedmovies.csv");
        //RaterDatabase.initialize("ratings.csv");
    }
    public int getRaterSize(){
        return RaterDatabase.size();
    }
    private double getAverageByID(String id,int minimalRaters){
        double count = 0,avg = 0;
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for(Rater r : raters){
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
        Collections.sort(ratings,Collections.reverseOrder());
        return ratings;
    }
    public double averageOfMovie(String id,int minimalRaters){
        return getAverageByID(id,minimalRaters);
    }
    public void printAverageRatings(){
        int minimalRaters = 30;
        ArrayList<Rating> rating = getAverageRatings(minimalRaters);
        Collections.sort(rating);
        for(Rating r :rating){
            if(r.getValue()!=0){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem())+ " " + r.getItem());}
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
    private double dotProduct(Rater me,Rater rater){
    double similarity = 0;
    for(String movie:me.getItemsRated()){
        if(rater.getRating(movie)== -1)
        continue;
        double other = rater.getRating(movie)-5;
        double mine = me.getRating(movie)-5;
        similarity += (other*mine);
    }
    return similarity;
    }
    private ArrayList<Rating> getSimilarities(String id){
    ArrayList<Rating> list = new ArrayList<Rating>();
    Rater me = RaterDatabase.getRater(id);
    for(Rater rater : RaterDatabase.getRaters()){
        double similarity = 0;
        if(!rater.getID().equals(me.getID())){
        similarity = dotProduct(me,rater);
        Rating rate = new Rating(rater.getID(),similarity);
        list.add(rate);
        }
    }   
    Collections.sort(list,Collections.reverseOrder());
    return list;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRatings,int minimalRaters,Filter f) {
    ArrayList<Rating> ret = new ArrayList<Rating>();
    ArrayList<String> movieID = MovieDatabase.filterBy(f);
    ArrayList<Rating> list = getSimilarities(id);
    int loopCount = 0;
    for(String movies : movieID){
        double weightedSum = 0,count = 0;
        for(int k=0;k < numSimilarRatings ;k++){
            Rating r = list.get(k);
            String rater_id = r.getItem();
            Rater topRater = RaterDatabase.getRater(rater_id);
            if(topRater.hasRating(movies)){
                count++;
                double similarityValue = r.getValue();
                double realValue = topRater.getRating(movies);
                weightedSum += (realValue*similarityValue);
            }
        }
        if(count >= minimalRaters){
            double weightedAverage = weightedSum/count;
            ret.add(new Rating(movies,weightedAverage));
        }
    }
    Collections.sort(ret,Collections.reverseOrder());
    return ret;
    }
}
