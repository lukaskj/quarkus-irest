package com.lukaskj.irest.marketplace.message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import com.lukaskj.irest.marketplace.entity.Restaurant;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.smallrye.common.annotation.Blocking;
import io.vertx.mutiny.pgclient.PgPool;

@ApplicationScoped
public class RegisteredRestaurant {

   @Inject
   PgPool pgPool;

   @Incoming("restaurants")
   @Blocking
   public void receiveRestaurant(String json) {
      Jsonb create = JsonbBuilder.create();
      Restaurant restaurant = create.fromJson(json, Restaurant.class);
      System.out.println("----------------------------------------");
      System.out.println(json);
      System.out.println("----------------------------------------");
      System.out.println(restaurant);
      System.out.println("----------------------------------------");

      restaurant.persist(pgPool);
   }
}
