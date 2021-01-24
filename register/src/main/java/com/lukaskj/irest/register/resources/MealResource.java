package com.lukaskj.irest.register.resources;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.lukaskj.irest.register.entity.Meal;
import com.lukaskj.irest.register.entity.Restaurant;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("restaurants/{restaurantId}/meals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "meal")
public class MealResource {
   @GET
   @Tag(name = "meal")
   public List<Meal> all(@PathParam("restaurantId") Long restaurantId) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(restaurantId);
      if (restOpt.isEmpty()) {
         throw new NotFoundException();
      }
      return Meal.list("restaurant", restOpt.get());
   }

   @POST
   @Transactional
   @Tag(name = "meal")
   public Response add(@PathParam("restaurantId") Long restaurantId, Meal dto) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(restaurantId);
      if (restOpt.isEmpty()) {
         throw new NotFoundException();
      }

      Meal meal = new Meal();
      meal.name = dto.name;
      meal.description = dto.description;
      meal.price = dto.price;
      meal.restaurant = restOpt.get();
      meal.persist();

      return Response.status(Status.CREATED).build();
   }

   @PUT
   @Path("/{id}")
   @Transactional
   @Tag(name = "meal")
   public Response update(@PathParam("restaurantId") Long restaurantId,
         @PathParam("id") Long mealId, Meal dto) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(restaurantId);
      if (restOpt.isEmpty()) {
         throw new NotFoundException();
      }
      Optional<Meal> mealOpt = Meal.findByIdOptional(mealId);
      if (mealOpt.isEmpty() || !mealOpt.get().restaurant.id.equals(restOpt.get().id)) {
         throw new NotFoundException();
      }
      Meal meal = mealOpt.get();
      meal.name = dto.name != null ? dto.name : meal.name;
      meal.description = dto.description != null ? dto.description : meal.description;
      meal.price = dto.price != null ? dto.price : meal.price;
      meal.persist();

      return Response.status(Status.OK).build();
   }

   @DELETE
   @Path("/{id}")
   @Transactional
   @Tag(name = "meal")
   public Response edlete(@PathParam("restaurantId") Long restaurantId,
         @PathParam("id") Long mealId) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(restaurantId);
      if (restOpt.isEmpty()) {
         throw new NotFoundException();
      }
      Optional<Meal> mealOpt = Meal.findByIdOptional(mealId);
      if (mealOpt.isEmpty() || !mealOpt.get().restaurant.id.equals(restOpt.get().id)) {
         throw new NotFoundException();
      }
      mealOpt.get().delete();
      return Response.status(Status.OK).build();
   }
}
