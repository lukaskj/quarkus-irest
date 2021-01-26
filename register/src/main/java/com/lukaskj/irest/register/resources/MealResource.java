package com.lukaskj.irest.register.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
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

import com.lukaskj.irest.register.dto.meal.AddMealDTO;
import com.lukaskj.irest.register.dto.meal.MealDTO;
import com.lukaskj.irest.register.dto.meal.MealMapper;
import com.lukaskj.irest.register.dto.meal.UpdateMealDTO;
import com.lukaskj.irest.register.entity.Meal;
import com.lukaskj.irest.register.entity.Restaurant;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("restaurants/{restaurantId}/meals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "meal")
@RolesAllowed("owners")
@SecurityScheme(securitySchemeName = "irest-oauth", type = SecuritySchemeType.OAUTH2,
      flows = @OAuthFlows(password = @OAuthFlow(
            tokenUrl = "http://localhost:8180/auth/realms/irest/protocol/openid-connect/token")))
@SecurityRequirements(value = {@SecurityRequirement(name = "irest-oauth", scopes = {})})
public class MealResource {
   @Inject
   MealMapper mealMapper;

   @GET
   @Tag(name = "meal")
   public List<MealDTO> all(@PathParam("restaurantId") Long restaurantId) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(restaurantId);
      if (restOpt.isEmpty()) {
         throw new NotFoundException();
      }
      return Meal.list("restaurant", restOpt.get()).stream()
            .map(m -> mealMapper.toMealDTO((Meal) m)).collect(Collectors.toList());
   }

   @POST
   @Transactional
   @Tag(name = "meal")
   public Response add(@PathParam("restaurantId") Long restaurantId, AddMealDTO dto) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(restaurantId);
      if (restOpt.isEmpty()) {
         throw new NotFoundException();
      }

      Meal meal = mealMapper.toMeal(dto);
      meal.restaurant = restOpt.get();
      meal.persist();

      return Response.status(Status.CREATED).build();
   }

   @PUT
   @Path("/{id}")
   @Transactional
   @Tag(name = "meal")
   public Response update(@PathParam("restaurantId") Long restaurantId,
         @PathParam("id") Long mealId, UpdateMealDTO dto) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(restaurantId);
      if (restOpt.isEmpty()) {
         throw new NotFoundException();
      }
      Optional<Meal> mealOpt = Meal.findByIdOptional(mealId);
      if (mealOpt.isEmpty() || mealOpt.get().restaurant == null
            || !mealOpt.get().restaurant.id.equals(restOpt.get().id)) {
         throw new NotFoundException();
      }
      Meal meal = mealOpt.get();
      mealMapper.toMeal(dto, meal);
      meal.restaurant = restOpt.get();
      meal.id = mealId;
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
