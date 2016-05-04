import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;


public class ReviewTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/yapzap_test", null, null);
  }
  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteReviewsQuery = "DELETE FROM reviews *;";
      con.createQuery(deleteReviewsQuery).executeUpdate();
    }
  }


  @Test
  public void review_instantiatesCorrectly_true(){
    Review review = new Review("name", "date and time", 1, "review");
    assertEquals(true, review instanceof Review);
  }

  @Test
  public void review_getName_String(){
    Review review = new Review("name", "date and time", 1, "review");
    assertEquals("name", review.getName());
  }

  @Test
  public void review_getDate_String(){
    Review review = new Review("name", "date and time", 1, "review");
    assertEquals("date and time", review.getDate());
  }
  @Test
  public void review_getRating_int(){
    Review review = new Review("name", "date and time", 1, "review");
    assertEquals(1, review.getRating());
  }
  @Test
  public void review_getReview_String(){
    Review review = new Review("name", "date and time", 1, "review");
    assertEquals("review", review.getReview());
  }
  
}
