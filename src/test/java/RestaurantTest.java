import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class RestaurantTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/yapzap_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteRestaurantsQuery = "DELETE FROM restaurants *;";
      con.createQuery(deleteRestaurantsQuery).executeUpdate();
    }
  }

  @Test
  public void restaurant_instantiatesCorrectly_true() {
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    assertEquals(true, newRestaurant instanceof Restaurant);
  }

  @Test
  public void getName_getsNameCorrectly_String() {
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    assertEquals("Pepe's", newRestaurant.getName());
  }

  @Test
  public void getGenre_getsGenreCorrectly_String() {
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    assertEquals("Mexican", newRestaurant.getGenre());
  }

  @Test
  public void getLocation_getsLocationCorrectly_String() {
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    assertEquals("South East", newRestaurant.getLocation());
  }

  @Test
  public void getNumber_getsNumberCorrectly_String() {
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    assertEquals("(714)788-5600", newRestaurant.getNumber());
  }

  @Test
  public void getAddress_getsAddressCorrectly_String() {
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    assertEquals("6500 Chapman Ave. Fullerton, CA", newRestaurant.getAddress());
  }

  @Test
  public void getDescription_getsDescriptionCorrectly_String() {
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    assertEquals("Description of the restaurant, and its stuff", newRestaurant.getDescription());
  }

  @Test
  public void getHours_getsHoursCorrectly_String() {
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    assertEquals("8:00AM - 2:00AM", newRestaurant.getHours());
  }

  @Test
  public void getCost_getsCostCorrectly_String() {
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    assertEquals("$", newRestaurant.getCost());
  }

  @Test
  public void getRestaurantId_returnsIdUponSave_int() {
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    newRestaurant.save();
    assertEquals(Restaurant.all().get(0).getId(), newRestaurant.getId());
  }

  @Test
  public void findRestaurant_findRestaurantById_String(){
    Restaurant newRestaurant = new Restaurant("Pepe's", "Mexican", "South East", "(714)788-5600", "6500 Chapman Ave. Fullerton, CA", "Description of the restaurant, and its stuff", "8:00AM - 2:00AM", "$");
    newRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(newRestaurant.getId());
    String namedRestaurant = savedRestaurant.getName();
    assertEquals("Pepe's", namedRestaurant);
  }
}
