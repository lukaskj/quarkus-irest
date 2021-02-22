package com.lukaskj.irest.order.message;

import com.lukaskj.irest.order.entity.dto.NewOrderDTO;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class NewOrderDeserializer extends ObjectMapperDeserializer<NewOrderDTO> {

   public NewOrderDeserializer() {
      super(NewOrderDTO.class);
   }

}
