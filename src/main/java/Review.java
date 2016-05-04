import java.util.ArrayList;
import java.sql.Timestamp;

public class Review {
  private int id;
  private String name;
  private String date;
  private int rating;
  private String review;

  public Review(String name, String date, int rating, String review){
    this.name = name;
    this.date = date;
    this.rating = rating;
    this.review = review;
  }

  public String getName(){
    return name;
  }
  public String getDate(){
    return date;
  }
  public int getRating(){
    return rating;
  }
  public String getReview(){
    return review;
  }
}
