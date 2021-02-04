package com.lukaskj.irest.marketplace;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MealResourceTest {

   @Test
   public void testMealsEndpoint() {
      String body = given().when().get("/meals").then().statusCode(200).extract().asString();
      System.out.println(body);
   }

}
