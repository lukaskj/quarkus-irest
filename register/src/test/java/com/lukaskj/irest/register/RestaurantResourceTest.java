package com.lukaskj.irest.register;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import javax.ws.rs.core.Response.Status;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.lukaskj.irest.register.entity.Restaurant;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(RegisterTestLifecycleManager.class)
public class RestaurantResourceTest {

   @Test
   @DataSet("restaurants-scenario-1.yml")
   public void testGetAllRestaurants() {
      // NoClassDefFoundError: org/junit/rules/TestRule
      String result =
            given().when().get("/restaurants").then().statusCode(200).extract().asString();
      Approvals.verifyJson(result);
   }

   private RequestSpecification given() {
      return RestAssured.given().contentType(ContentType.JSON);
   }

   @Test
   @DataSet("restaurants-scenario-1.yml")
   public void testUpdateRestaurant() {
      Restaurant dto = new Restaurant();
      dto.name = "New name";
      Long parameterValue = 123L;
      given().with().pathParam("id", parameterValue).body(dto).when().put("/restaurants/{id}")
            .then().statusCode(Status.NO_CONTENT.getStatusCode()).extract().asString();
      Restaurant findById = Restaurant.findById(parameterValue);
      Assert.assertEquals(dto.name, findById.name);
   }

}
