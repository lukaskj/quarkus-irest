package com.lukaskj.irest.register;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

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

}
