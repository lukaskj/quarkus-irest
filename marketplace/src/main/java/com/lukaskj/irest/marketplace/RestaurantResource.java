package com.lukaskj.irest.marketplace;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.lukaskj.irest.marketplace.entity.Meal;
import com.lukaskj.irest.marketplace.entity.dto.MealDTO;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

@Path("restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource {

   @Inject
   PgPool client;

   @GET
   @Path("{restaurantId}/meals")
   public Multi<MealDTO> getMeals(@PathParam("restaurantId") Long restaurantId) {
      return Meal.findAll(client, restaurantId);
   }
}
