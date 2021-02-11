package com.lukaskj.irest.marketplace;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.lukaskj.irest.marketplace.entity.Meal;
import com.lukaskj.irest.marketplace.entity.MealCart;
import com.lukaskj.irest.marketplace.entity.dto.MealCartDTO;
import com.lukaskj.irest.marketplace.entity.dto.MealDTO;
import com.lukaskj.irest.marketplace.entity.dto.MealOrderDTO;
import com.lukaskj.irest.marketplace.entity.dto.OrderDTO;
import com.lukaskj.irest.marketplace.entity.dto.RestaurantDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
   private static final String CLIENT = "a";

   @Inject
   PgPool pgClient;

   @Inject
   @Channel("orders")
   Emitter<OrderDTO> orderEmitter;

   @GET
   public Uni<List<MealCart>> getCart() {
      return MealCart.findCart(pgClient, CLIENT);
   }

   @POST
   @Path("/meal/{mealId}")
   public Uni<Long> addMeal(@PathParam("mealId") Long mealId) {
      MealCart mc = new MealCart();
      mc.client = CLIENT;
      mc.mealId = mealId;
      return MealCart.save(pgClient, CLIENT, mealId);
   }

   @POST
   @Path("/add-order")
   public Uni<Boolean> addOrder() {
      OrderDTO order = new OrderDTO();
      String client = CLIENT;
      order.client = client;
      List<MealCart> mealCart = MealCart.findCart(pgClient, client).await().indefinitely();
      List<MealOrderDTO> meals = mealCart.stream().map(pc -> from(pc)).collect(Collectors.toList());

      RestaurantDTO restaurant = new RestaurantDTO();
      restaurant.name = "Nome do restaurant";
      order.restaurant = restaurant;
      order.meals = meals;

      orderEmitter.send(order);

      return MealCart.delete(pgClient, client);
   }

   private MealOrderDTO from(MealCart mealCart) {
      MealDTO md = Meal.findById(pgClient, mealCart.mealId).await().indefinitely();
      return new MealOrderDTO(md.name, md.description, md.price);
   }
}
