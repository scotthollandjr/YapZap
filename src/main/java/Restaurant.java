
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Restaurant {
  private int id;
  private String name;
  private String genre;
  private String location;
  private String number;
  private String address;
  private String description;
  private String hours;
  private int rating;
  private String cost;

  public Restaurant(String name, String genre, String location, String number, String address, String description, String hours, String cost){
    this.name = name;
    this.genre = genre;
    this.location = location;
    this.number = number;
    this.address = address;
    this.description = description;
    this.hours = hours;
    this.cost = cost;
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getGenre(){
    return genre;
  }

  public String getLocation(){
    return location;
  }

  public String getNumber(){
    return number;
  }

  public String getAddress(){
    return address;
  }

  public String getDescription(){
    return description;
  }

  public String getHours(){
    return hours;
  }

  public String getCost(){
    return cost;
  }

  @Override
  public boolean equals(Object otherRestaurant) {
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
             this.getId() == newRestaurant.getId();
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO restaurants(name, genre, location, number, address, description, hours, cost) VALUES (:name, :genre, :location, :number, :address, :description, :hours, :cost)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("genre", this.genre)
        .addParameter("location", this.location)
        .addParameter("number", this.number)
        .addParameter("address", this.address)
        .addParameter("description", this.description)
        .addParameter("hours", this.hours)
        .addParameter("cost", this.cost)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Restaurant> all(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants";
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants WHERE id=:id";
      Restaurant restaurant = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Restaurant.class);
      return restaurant;
    }
  }
}
