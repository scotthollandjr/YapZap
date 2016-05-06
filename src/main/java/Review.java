import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Review {
  private int id;
  private String name;
  private String date;
  private String rating;
  private String feedback;
  private int restaurantId;

  public Review(String name, String date, String rating, String feedback){
    this.name = name;
    this.date = date;
    this.rating = rating;
    this.feedback = feedback;
  }

  public String getName(){
    return name;
  }

  public String getDate(){
    return date;
  }

  public String getRating(){
    return rating;
  }

  public String getFeedback(){
    return feedback;
  }

  public int getId(){
    return id;
  }

  public static List<Review> all() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM reviews";
      return con.createQuery(sql).executeAndFetch(Review.class);
    }
  }

  public void saveReviewToRestaurant(int inputId) {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO reviews(name, date, rating, feedback, restaurantid) VALUES (:name, :date, :rating, :feedback, :restaurantId)";
      this.restaurantId = inputId;
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("date", this.date)
      .addParameter("rating", this.rating)
      .addParameter("feedback", this.feedback)
      .addParameter("restaurantId", this.restaurantId)
      .executeUpdate()
      .getKey();
    }
  }
  public static Review find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM reviews WHERE id=:id";
      Review review = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Review.class);
      return review;
    }
  }

  public static List<Review> getReviews(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM reviews WHERE restaurantId=:id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Review.class);
    }
  }
}
