package com.lukaskj.irest.order.message;

import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import com.lukaskj.irest.order.entity.Meal;
import com.lukaskj.irest.order.entity.Order;
import com.lukaskj.irest.order.entity.Restaurant;
import com.lukaskj.irest.order.entity.dto.NewOrderDTO;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class NewOrderIncoming {

   @Incoming("orders")
   public void readOrders(NewOrderDTO dto) {
      System.out.println(dto);
      Order order = new Order();
      order.client = dto.client;
      order.meals = new ArrayList<>();
      dto.meals.forEach(mealDTO -> order.meals.add(Meal.from(mealDTO)));

      Restaurant r = new Restaurant();
      r.name = dto.restaurant.name;
      order.restaurant = r;
      order.persist();
   }
}
