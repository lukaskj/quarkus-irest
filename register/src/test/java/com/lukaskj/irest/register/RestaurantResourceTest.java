package com.lukaskj.irest.register;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.lukaskj.irest.register.dto.restaurant.UpdateRestaurantDTO;
import com.lukaskj.irest.register.entity.Restaurant;
import com.lukaskj.irest.register.util.TokenUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

@DBRider
@DBUnit(caseSensitiveTableNames = true, caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(RegisterTestLifecycleManager.class)
public class RestaurantResourceTest {

   private String token;

   @BeforeEach
   public void generateToken() throws Exception {
      token = TokenUtils.generateTokenString("/JWTOwnerClaims.json", null);
   }

   @Test
   @DataSet("restaurants-scenario-1.yml")
   public void testGetAllRestaurants() {
      String result =
            given().when().get("/restaurants").then().statusCode(200).extract().asString();
      // Approvals.verifyJson(result);
   }

   private RequestSpecification given() {
      return RestAssured.given().contentType(ContentType.JSON)
            .header(new Header("Authorization", "Bearer " + token));
   }

   @Test
   @DataSet("restaurants-scenario-1.yml")
   public void testUpdateRestaurant() {
      UpdateRestaurantDTO dto = new UpdateRestaurantDTO();
      dto.companyName = "New name";
      dto.companyId = "12345678901234";
      dto.ownerId = "Test 2";
      Long parameterValue = 123L;
      String response = given().with().pathParam("id", parameterValue).body(dto).when().put("/restaurants/{id}")
            .then().extract().asString();
      Restaurant findById = Restaurant.findById(parameterValue);
      Assert.assertEquals(dto.companyName, findById.name);
   }

}
