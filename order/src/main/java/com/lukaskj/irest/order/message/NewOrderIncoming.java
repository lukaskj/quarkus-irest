package com.lukaskj.irest.order.message;

import javax.enterprise.context.ApplicationScoped;
import com.lukaskj.irest.order.entity.dto.NewOrderDTO;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class NewOrderIncoming {

   @Incoming("orders")
   public void readOrders(NewOrderDTO dto) {
      System.out.println(dto);
   }
}
