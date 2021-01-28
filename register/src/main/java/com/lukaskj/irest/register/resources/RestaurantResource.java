package com.lukaskj.irest.register.resources;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.RedirectionException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.lukaskj.irest.register.dto.restaurant.AddRestaurantDTO;
import com.lukaskj.irest.register.dto.restaurant.RestaurantDTO;
import com.lukaskj.irest.register.dto.restaurant.RestaurantMapper;
import com.lukaskj.irest.register.dto.restaurant.UpdateRestaurantDTO;
import com.lukaskj.irest.register.entity.Restaurant;
import com.lukaskj.irest.register.util.ConstraintViolationRespose;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "restaurant")
@RolesAllowed("owners")
@SecurityScheme(securitySchemeName = "irest-oauth", type = SecuritySchemeType.OAUTH2,
      flows = @OAuthFlows(password = @OAuthFlow(
            tokenUrl = "http://localhost:8180/auth/realms/irest/protocol/openid-connect/token")))
@SecurityRequirements(value = {@SecurityRequirement(name = "irest-oauth", scopes = {})})
public class RestaurantResource {

   @Inject
   RestaurantMapper restaurantMapper;

   @GET
   public List<RestaurantDTO> all() {
      Stream<Restaurant> allRestaurants = Restaurant.streamAll();
      return allRestaurants.map(r -> restaurantMapper.toRestaurantDTO(r))
            .collect(Collectors.toList());
   }

   @POST
   @Transactional
   @APIResponse(responseCode = "201", description = "Success")
   @APIResponse(responseCode = "400",
         content = @Content(schema = @Schema(allOf = ConstraintViolationRespose.class)))
   public Response add(@Valid AddRestaurantDTO dto) {
      Restaurant rest = restaurantMapper.toRestaurant(dto);
      rest.persist();
      return Response.status(Status.CREATED).build();
   }

   @PUT
   @Path("{id}")
   @Transactional
   public void update(@PathParam("id") Long id, @Valid UpdateRestaurantDTO dto) {
      Optional<Restaurant> restOpt = Restaurant.findByIdOptional(id);
      if (restOpt.isEmpty()) {
         throw new NotFoundException();
      }
      Restaurant rest = restOpt.get();
      // rest.name = dto.companyName;
      // rest.ownerId = dto.ownerId;
      // rest.companyId = dto.companyId;
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
