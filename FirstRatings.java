package CourseraCapstoneNew;


/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.nio.*;
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
public class FirstRatings {
public ArrayList<Movie> loadMovies(String filename){
    ArrayList<Movie> list = new ArrayList<Movie>();
    FileResource fr = new FileResource(filename);
    CSVParser parser = fr.getCSVParser();
    for(CSVRecord record:parser){
        Movie mv = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes")));
        list.add(mv);
    }
    return list;
}
public void testLoadMovies(){
    String source = "C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratedmoviesfull.csv";
    ArrayList<Movie> local = loadMovies(source);
    HashMap<String,Integer> map = new HashMap<String,Integer>(); 
    int countComedy = 0,countMinutes = 0;
    for(Movie m:local){
        if(m.getGenres().contains("Comedy")){
            countComedy++;   
        }
        if(m.getMinutes() > 150){
            countMinutes++;   
        }
        String dir = m.getDirector();
        if(map.containsKey(dir)){
            int value = map.get(dir);
            map.put(dir,value+1);
        }
        else{
            map.put(dir,1);
        }
    }
    System.out.println("Movies that include comedy genre are :"+countComedy);
    System.out.println("Movies that are greater than 150 minutes :"+countMinutes);
    int max=0;
    for(String key :map.keySet()){
        if(max < map.get(key)){
            max = map.get(key);
        }
    }
    System.out.println("Maximum number of movie directed by a director are : "+max);
    System.out.println("And Number of directors directed such many movies are:");
    int count = 0;
    for(String key :map.keySet()){
        if(max == map.get(key)){
            System.out.println(key + map.get(key));
            count++;
        }
    }
    System.out.println("Director = "+count);
    System.out.println(local.size());
}
public int contains(ArrayList<Rater> ratings,String id){
    for(Rater r : ratings){
        if(r.getID().equals(id)){
            return ratings.indexOf(r);
        }
    }
        return -1;
}
public ArrayList<Rater> loadRaters(String filename){
    ArrayList<Rater> list = new ArrayList<Rater>();
    FileResource fr = new FileResource(filename);
    CSVParser parser = fr.getCSVParser();
    for(CSVRecord record:parser){
        int check = contains(list,record.get("rater_id"));
        if(check!=-1){
            Rater rt = list.get(check);
            rt.addRating(record.get("movie_id"),Integer.parseInt(record.get("rating")));
        }
        else{
            Rater rt = new PlainRater(record.get("rater_id"));
            rt.addRating(record.get("movie_id"),Integer.parseInt(record.get("rating")));
            list.add(rt);
        }
    }
    return list;
}
public void testLoadRaters(){
    String source = "C:\\Users\\Ravi Teja L\\CourseraCapstone\\data\\ratings.csv";
    ArrayList<Rater> local = loadRaters(source);
    HashMap<String,Integer> map = new HashMap<String,Integer>();
    int max=0;
    for(Rater r :local){
        if(r.getID().equals("193"))
        System.out.println("Rater ID:"+r.getID()+"\tNumber of Movies Rated :"+r.numRatings());
        if(max < r.numRatings()){
            max = r.numRatings();
        }
        ArrayList<String> ratings = r.getItemsRated();
        for(String id : ratings){
        //System.out.println(id + "\tRating :"+r.getRating(id));
        }
        map.put(r.getID(),r.numRatings());
    }
    System.out.println("Maximum Number of Movies Rated by a Rater are :"+max);
    int count = 0;
    for(String rt :map.keySet()){
        if(max == map.get(rt)){
            System.out.println("Rater ID :"+rt);
            count++;
        }
    }
    System.out.println("Raters who have max movies are :"+count);
    int movieRater = 0;
    for(Rater r :local){
        if(r.hasRating("1798709")){
            movieRater++;
        }
    }
    System.out.println("Raters Rated 1798709 are "+movieRater);
    ArrayList<String> list = new ArrayList<String>();
    for(Rater r :local){
        ArrayList<String> movies = r.getItemsRated();
        for(String mv :movies){
            if(!list.contains(mv)){
                list.add(mv);
            }
        }
    }
    System.out.println("Total number of movies rated by raters are :"+list.size() +"\n" +list);
    System.out.println("Total number of Raters :"+local.size());
}
}
