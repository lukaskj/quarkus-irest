package com.lukaskj.irest.register.resources;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
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
import com.lukaskj.irest.register.dto.AddRestaurantDTO;
import com.lukaskj.irest.register.dto.RestaurantDTO;
import com.lukaskj.irest.register.dto.RestaurantMapper;
import com.lukaskj.irest.register.dto.UpdateRestaurantDTO;
import com.lukaskj.irest.register.entity.Restaurant;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "restaurant")
public class RestaurantResource {

   @Inject
   RestaurantMapper restaurantMapper;

   @GET
   public List<RestaurantDTO> all() {
      Stream<Restaurant> allRestaurants = Restaurant.streamAll();
      return allRestaurants.map(r -> restaurantMapper.toRestaurantDTO(r)).collect(Collectors.toList());
   }

   @POST
   @Transactional
   public Response add(@Valid AddRestaurantDTO dto) {
      Restaurant rest = restaurantMapper.toRestaurant(dto);
      rest.persist();
      return Response.status(Status.CREATED).build();
   }

   @PUT
   @Path("{id}")
   @Transactional
   public void update(@PathParam("id") Long id, UpdateRestaurantDTO dto) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(id);
      if (restOpt.isEmpty()) {
         throw new NotFoundException();
      }
      Restaurant rest = restOpt.get();
      restaurantMapper.toRestaurant(dto, rest);
      rest.id = id;
      rest.persist();
   }

   @DELETE
   @Path("{id}")
   @Transactional
   public void delete(@PathParam("id") Long id) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(id);
      restOpt.ifPresentOrElse(Restaurant::delete, () -> {
         throw new NotFoundException();
      });
   }
}
