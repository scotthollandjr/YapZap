import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

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
  public int getId(){
    return id;
  }
  public static List<Review> all() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM reviews";
      return con.createQuery(sql).executeAndFetch(Review.class);
    }
  }
  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO reviews(name, date, rating, review) VALUES (:name, :date, :rating, :review)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("date", this.date)
      .addParameter("rating", this.rating)
      .addParameter("review", this.review)
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
}
