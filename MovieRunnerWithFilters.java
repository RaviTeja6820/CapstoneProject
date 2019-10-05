package CourseraCapstoneNew;


/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
public void printAverageRatings(){
    ThirdRatings TR = new ThirdRatings("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratings.csv");
    MovieDatabase.initialize("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data/ratedmoviesfull.csv");
    System.out.println("Total number of movies that are rated are "+ MovieDatabase.filterBy(new TrueFilter()).size() + " and rated by " + TR.getRaterSize() + " raters");
    ArrayList<Rating> list = TR.getAverageRatings(35);
    int count =0;
    Collections.sort(list);
    for(Rating rt :list){
        if(rt.getValue()>0){
        System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        count++;
        }
    }
    System.out.println("movies that are rated by more than 1 raters are "+count);
    //TR.printAverageRatings();
}
public void printAverageRatingsByYear(){
    ThirdRatings TR = new ThirdRatings("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratings.csv");
    MovieDatabase.initialize("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data/ratedmoviesfull.csv");
    System.out.println("Total number of movies that are rated are "+ MovieDatabase.filterBy(new TrueFilter()).size() + " and rated by " + TR.getRaterSize() + " raters");
    //ArrayList<Rating> list = TR.getAverageRatingByFilter(1,new YearAfterFilter(2000));
    ArrayList<Rating> list = TR.getAverageRatingByFilter(20,new YearAfterFilter(2000));
    int count =0;
    Collections.sort(list);
    for(Rating r :list){
        //if(r.getValue()!=0.0){
        System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem())+ " " + MovieDatabase.getTitle(r.getItem()));
        count++;
    }
    System.out.println("movies that are rated by atleast 20 rater and released after the year 2000 are "+count);
}
public void printAverageRatingsByGenre(){
    ThirdRatings TR = new ThirdRatings("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratings.csv");
    MovieDatabase.initialize("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data/ratedmoviesfull.csv");
    System.out.println("Total number of movies that are rated are "+ MovieDatabase.filterBy(new TrueFilter()).size() + " and rated by " + TR.getRaterSize() + " raters");
    ArrayList<Rating> list = TR.getAverageRatingByFilter(20,new GenreFilter("Comedy"));
    int count =0;
    Collections.sort(list);
    for(Rating r :list){
        //if(r.getValue()!=0.0){
        System.out.println(r.getValue() + " " + MovieDatabase.getGenres(r.getItem())+ " " + MovieDatabase.getTitle(r.getItem()));
        count++;
    }
    System.out.println("movies that are rated by atleast 20 raters and has the genre Crime are "+count);
}
public void printAverageRatingsByMinutes(){
    ThirdRatings TR = new ThirdRatings("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratings.csv");
    MovieDatabase.initialize("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data/ratedmoviesfull.csv");
    System.out.println("Total number of movies that are rated are "+ MovieDatabase.filterBy(new TrueFilter()).size() + " and rated by " + TR.getRaterSize() + " raters");
    ArrayList<Rating> list = TR.getAverageRatingByFilter(5,new MinutesFilter(105,135));
    int count =0;
    Collections.sort(list);
    //System.out.println("movies that are rated by atleast 1 rater and has the genre Crime are "+count);
    for(Rating r :list){
        //if(r.getValue()!=0.0){
        System.out.println(r.getValue() + " " + " Time :" + MovieDatabase.getMinutes(r.getItem())+ " " + MovieDatabase.getTitle(r.getItem()));
        count++;
    }
    System.out.println("found "+count+ " Movies");
}
public void printAverageRatingsByDirector(){
    ThirdRatings TR = new ThirdRatings("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratings.csv");
    MovieDatabase.initialize("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data/ratedmoviesfull.csv");
    System.out.println("Total number of movies that are rated are "+ MovieDatabase.filterBy(new TrueFilter()).size() + " and rated by " + TR.getRaterSize() + " raters");
    ArrayList<Rating> list = TR.getAverageRatingByFilter(4,new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
    int count =0;
    Collections.sort(list);
    //System.out.println("movies that are rated by atleast 1 rater and has the genre Crime are "+count);
    for(Rating r :list){
        if(r.getValue()!=0.0){
        System.out.println(r.getValue() + " " + " Director :" + MovieDatabase.getDirector(r.getItem())+ " " + MovieDatabase.getTitle(r.getItem()));
        count++;}
    }
    System.out.println("found "+count+ " Movies");
}
public void printAverageRatingsByYearAfterAndGenre(){
    ThirdRatings TR = new ThirdRatings("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratings.csv");
    MovieDatabase.initialize("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data/ratedmoviesfull.csv");
    System.out.println("Total number of movies that are rated are "+ MovieDatabase.filterBy(new TrueFilter()).size() + " and rated by " + TR.getRaterSize() + " raters");
    AllFilters af = new AllFilters();
    af.addFilter(new YearAfterFilter(1990));
    af.addFilter(new GenreFilter("Drama"));
    ArrayList<Rating> list = TR.getAverageRatingByFilter(8,af);
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
public void printAverageRatingsByDirectorsAndMinutes(){
    ThirdRatings TR = new ThirdRatings("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratings.csv");
    MovieDatabase.initialize("C:\\Users\\Ravi Teja L\\CourseraCapstone\\data/ratedmoviesfull.csv");
    System.out.println("Total number of movies that are rated are "+ MovieDatabase.filterBy(new TrueFilter()).size() + " and rated by " + TR.getRaterSize() + " raters");
    AllFilters af = new AllFilters();
    af.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
    af.addFilter(new MinutesFilter(90,180));
    ArrayList<Rating> list = TR.getAverageRatingByFilter(3,af);
    int count =0;
    Collections.sort(list);
    //System.out.println("movies that are rated by atleast 1 rater and has the genre Crime are "+count);
    for(Rating r :list){
        if(r.getValue()!=0.0){
        System.out.println(r.getValue() + " " + " Time :" + MovieDatabase.getMinutes(r.getItem()) + " Director :" + MovieDatabase.getDirector(r.getItem()) + " " + "Movie :" + MovieDatabase.getTitle(r.getItem()));
        count++;}
    }
    System.out.println("found "+count+ " Movies");
}
}