package CourseraCapstoneNew;




/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.text.DecimalFormat;
public class RecommendationRunner implements Recommender {

    public ArrayList<String> getItemsToRate (){
        ArrayList<String> movieID = new ArrayList<String>();
        movieID.add("1300854");
        movieID.add("1843866");
        movieID.add("1408101");
        movieID.add("1392214");
        movieID.add("1951264");
        movieID.add("0993846");
        movieID.add("1877832");
        movieID.add("2267998");
        movieID.add("2872718");
        movieID.add("0816692");
        movieID.add("1454468");
        return movieID;
    }
    public void printRecommendationsFor (String webRaterID){
        FourthRatings FR = new FourthRatings();
        Filter f = new YearAfterFilter(1990);
        ArrayList<Rating> list = FR.getSimilarRatingsByFilter(webRaterID, 20 ,5 ,f);
        int count = 0;
        Rater r = RaterDatabase.getRater(webRaterID);
        ArrayList<String> movieList = r.getItemsRated();
            if(list.isEmpty()){
                System.out.println("<html>");
                System.out.println("<style>");
                System.out.println("*{");
                    System.out.println("background :#2C3335;");
                    System.out.println("color:white;");
                    System.out.println("}");
                    System.out.println("#divone {");
                        System.out.println("margin-top: 20%;");
                        System.out.println("text-align: center;");
                        System.out.println("}");
                        System.out.println("</style>");
                System.out.println("<body>");
                System.out.println("<div id=\"divone\">");
                System.out.println("<h2><b>" + "Movies not found" + "</b></h2>");
                System.out.println("</div>");
                System.out.println("</body>");
                System.out.println("</html>");
                System.exit(1);
            }
            System.out.println("<html>");
            System.out.println("<style>");
            System.out.println("table { width: 100% ;border: 2px solid black; background:AliceBlue}");
            System.out.println("*{");
                    System.out.println("color:solid white;");
                    System.out.println("}");
                    System.out.println("#row th {");
                        System.out.println("background :#74B9FF;");
                        System.out.println("color:black;");
                        System.out.println("}");
            System.out.println("</style>");
            System.out.println("<body>");
            
            System.out.println("<table>");
            
            System.out.println("<tr id=\"row\">");
            System.out.println("<th>" + "Average Movie Rating" + "</th>");
            System.out.println("<th>" + "Movie Poster" + "</th>");
            System.out.println("<th>" + "Movie Name" + "</th>");
            System.out.println("<th>" + "Directed By" + "</th>");
            System.out.println("<th>" + "Year" + "</th>");
            System.out.println("<th>" + "Movie Duration(minutes)" + "</th>");
            System.out.println("</tr>");
            
    for(Rating topRatedMovies : list){
        if(topRatedMovies.getValue() > 0.0){
            String movieId = topRatedMovies.getItem();
            double no= FR.averageOfMovie(movieId,5) ;
            DecimalFormat dec = new DecimalFormat("#0.00");
            
            System.out.println("<tr>");
            System.out.println("<th><b><font color=red>" + dec.format(no) + "</font></font></b></th>");
            System.out.println("<th>" + " <img src=\""+MovieDatabase.getPoster(movieId)+"\" width:10px height:20px>" + "</th>");
            if(movieList.contains(movieId))
            System.out.print("<th><h2>" + "***" + MovieDatabase.getTitle(movieId) + "</h2></th>");
            else
            System.out.println("<th><h2>" + MovieDatabase.getTitle(movieId) + "</h2></th>");
            System.out.println("<th>" + MovieDatabase.getDirector(movieId) + "</th>");
            System.out.println("<th>" + MovieDatabase.getYear(movieId) + "</th>");
            System.out.println("<th>" + MovieDatabase.getMinutes(movieId) + "</th>");
            System.out.println("</tr>");
            count++;
            if(count == 15)
            break;
        }
    }
            System.out.println("</table>");
            System.out.println("</body>");
            System.out.println("</html>");
    }
}
