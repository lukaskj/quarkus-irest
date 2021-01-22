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
import com.lukaskj.irest.register.entity.Restaurant;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "restaurant")
public class RestaurantResource {

   @GET
   public List<Restaurant> all() {
      return Restaurant.listAll();
   }

   @POST
   @Transactional
   public Response add(Restaurant dto) {
      dto.persist();
      return Response.status(Status.CREATED).build();
   }

   @PUT
   @Path("{id}")
   @Transactional
   public void update(@PathParam("id") Long id, Restaurant dto) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(id);
      if (restOpt.isEmpty()) {
         throw new NotFoundException();
      }
      Restaurant rest = restOpt.get();
      rest.name = dto.name;
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
