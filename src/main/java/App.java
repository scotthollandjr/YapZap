import java.util.*;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       model.put("restaurants", Restaurant.all());
      //  model.put("reviews", Restaurant.getReviews());
       model.put("template", "templates/index.vtl");
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

    get("/restaurant/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/restaurant-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/success", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       String name = request.queryParams("name");
       String genre = request.queryParams("genre");
       String description = request.queryParams("description");
       String number = request.queryParams("number");
       String hours = request.queryParams("hours");
       String cost = request.queryParams("cost");
       String address = request.queryParams("address");
       String city = request.queryParams("city");
       String state = request.queryParams("state");
       String zipcode = request.queryParams("zipcode");
       Restaurant newRestaurant = new Restaurant(name, genre, number, address, city, state, zipcode, description, hours, cost);
       newRestaurant.save();
       model.put("restaurant", newRestaurant);
       model.put("restaurants", Restaurant.all());
       model.put("template", "templates/submit-success.vtl");
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     get("/restaurant/:id", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
       int inputId = Integer.parseInt((request.params(":id")));
       model.put("reviews", Review.getReviews(inputId));
       model.put("restaurant", restaurant);
       model.put("template", "templates/restaurant.vtl");
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     get("/restaurant/:id/review/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
       model.put("restaurant", restaurant);
       model.put("template", "templates/review-form.vtl");
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     post("/success-review", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String name = request.queryParams("name");
        String date = request.queryParams("date");
        String rating = request.queryParams("rating");
        String feedback = request.queryParams("feedback");
        int restaurantId = Integer.parseInt(request.queryParams("restaurantId"));
        Review newReview = new Review(name, date, rating, feedback);
        newReview.saveReviewToRestaurant(restaurantId);
        model.put("review", newReview);
        model.put("template", "templates/submit-success.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
  }
}



// model.put("template", "templates/index.vtl");
// = new Review;
//  newReview.saveReviewToRestaurant(":id")
//  model.put(":id", request.params(:id))
