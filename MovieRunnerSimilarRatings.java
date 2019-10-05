package CourseraCapstoneNew;


/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.lang.Math;
public class MovieRunnerSimilarRatings {
public void printAverageRatings(){
    FourthRatings FR = new FourthRatings();
    System.out.println("Total number of movies that are rated are "+ MovieDatabase.filterBy(new TrueFilter()).size() + " and rated by " + FR.getRaterSize() + " raters");
    int minimumRaters = 30;
    ArrayList<Rating> list = FR.getAverageRatings(minimumRaters);
    int count =0;
    Collections.sort(list,Collections.reverseOrder());
    for(Rating rt :list){
        if(rt.getValue()>0){
        System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        count++;
        if(count == 30)
        break;
        }
    }
    System.out.println("movies that are rated by more than " + minimumRaters + " raters are "+ list.size());
    //TR.printAverageRatings();
}
public void printAverageRatingsByYearAfterAndGenre(){
    FourthRatings FR = new FourthRatings();
    System.out.println("Total number of movies that are rated are "+ MovieDatabase.filterBy(new TrueFilter()).size() + " and rated by " + FR.getRaterSize() + " raters");
    AllFilters af = new AllFilters();
    af.addFilter(new YearAfterFilter(1990));
    af.addFilter(new GenreFilter("Drama"));
    ArrayList<Rating> list = FR.getAverageRatingByFilter(1,af);
    int count =0;
    Collections.sort(list);
    //System.out.println("movies that are rated by atleast 1 rater and has the genre Crime are "+count);
    for(Rating r :list){
        if(r.getValue()!=0.0){
        System.out.println(r.getValue() + " " + " Time :" + MovieDatabase.getYear(r.getItem()) + " Genres :" + MovieDatabase.getGenres(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        count++;}
    }
    System.out.println("found "+count+ " Movies");
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
public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRatings,int minimalRaters) {
    ArrayList<Rating> ret = new ArrayList<Rating>();
    ArrayList<String> movieID = MovieDatabase.filterBy(new TrueFilter());
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
public void printSimilarRatings(){
    FourthRatings FR = new FourthRatings();
    ArrayList<Rating> list = getSimilarRatings("71",20 ,5);
    int count = 0;
    Rater r = RaterDatabase.getRater("71");
    ArrayList<String> movieList = r.getItemsRated();
    for(Rating topRatedMovies : list){
        if(topRatedMovies.getValue() > 0.0){
            String movieId = topRatedMovies.getItem();
            //System.out.println(topRatedMovies);topRatedMovies.getValue() + " " +
            if(movieList.contains(movieId))
            System.out.print("***"+" ");
            System.out.print(MovieDatabase.getTitle(movieId) + "  " );
            System.out.printf("%.2f",FR.averageOfMovie(movieId,5));
            System.out.println("");
            count++;
            if(count == 10)
            break;
        }
    }
    System.out.println("Recommended Top " + count + " movies on your selections");
}
public void printSimilarRatingsByGenre(){
    FourthRatings FR = new FourthRatings();
    ArrayList<Rating> list = getSimilarRatingsByFilter("964",20 ,5,new GenreFilter("Mystery"));
    int count = 0;
    for(Rating topRatings : list){
        if(topRatings.getValue() > 0.0){
            String movieId = topRatings.getItem();
            System.out.print(MovieDatabase.getTitle(movieId) + "  " );
            System.out.printf("%.2f",FR.averageOfMovie(movieId,5));
            System.out.println("");
            count++;
            if(count == 10)
            break;
        }
    }
    System.out.println("Recommended Top " + count + " movies on your selections");
}
public void printSimilarRatingsByDirector(){
    FourthRatings FR = new FourthRatings();
    ArrayList<Rating> list = getSimilarRatingsByFilter("120",10 ,2,new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
    int count = 0;
    for(Rating topRatings : list){
        if(topRatings.getValue() > 0.0){
            String movieId = topRatings.getItem();
            System.out.print(MovieDatabase.getTitle(movieId) + "  " );
            System.out.printf("%.2f",FR.averageOfMovie(movieId,5));
            System.out.println("");
            count++;
            if(count == 10)
            break;
        }
    }
    System.out.println("Recommended Top " + count + " movies on your selcetions");
}
public void printSimilarRatingsByGenreAndMinutes(){
    FourthRatings FR = new FourthRatings();
    AllFilters af = new AllFilters();
    af.addFilter(new GenreFilter("Drama"));
    af.addFilter(new MinutesFilter(80,160));
    ArrayList<Rating> list = getSimilarRatingsByFilter("168",10 ,3,af);
    int count = 0;
    for(Rating topRatings : list){
        if(topRatings.getValue() > 0.0){
            String movieId = topRatings.getItem();
            System.out.print(MovieDatabase.getTitle(movieId) + "  " );
            System.out.printf("%.2f",FR.averageOfMovie(movieId,5));
            System.out.println("");
            count++;
            if(count == 10)
            break;
        }
    }
    System.out.println("Recommended Top " + count + " movies on your selcetions");
}
public void printSimilarRatingsByYearAfterAndMinutes(){
    FourthRatings FR = new FourthRatings();
    AllFilters af = new AllFilters();
    af.addFilter(new YearAfterFilter(1975));
    af.addFilter(new MinutesFilter(70,200));
    ArrayList<Rating> list = getSimilarRatingsByFilter("314",10 ,5,af);
    int count = 0;
    for(Rating topRatings : list){
        if(topRatings.getValue() > 0.0){
            String movieId = topRatings.getItem();
            System.out.print(MovieDatabase.getTitle(movieId) + "  " );
            System.out.printf("%.2f",FR.averageOfMovie(movieId,5));
            System.out.println("");
            count++;
            if(count == 10)
            break;
        }
    }
    System.out.println("Recommended Top " + count + " movies on your selcetions");
}
}
