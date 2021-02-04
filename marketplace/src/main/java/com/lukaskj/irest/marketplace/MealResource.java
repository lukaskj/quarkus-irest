package com.lukaskj.irest.marketplace;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.lukaskj.irest.marketplace.entity.Meal;
import com.lukaskj.irest.marketplace.entity.dto.MealDTO;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

@Path("/meals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MealResource {

   @Inject
   PgPool pgPoll;

   @GET
   @APIResponse(responseCode = "200", content = @Content(
         schema = @Schema(type = SchemaType.ARRAY, implementation = MealDTO.class)))
   public Multi<MealDTO> searchAll() {
      return Meal.findAll(pgPoll);
   }
}
