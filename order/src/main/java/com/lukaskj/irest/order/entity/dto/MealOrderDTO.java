package com.lukaskj.irest.order.entity.dto;

import java.math.BigDecimal;

public class MealOrderDTO {
   public String name;

   public String description;

   public BigDecimal price;

   public MealOrderDTO() {
      super();
   }

   public MealOrderDTO(String name, String description, BigDecimal price) {
      super();
      this.name = name;
      this.description = description;
      this.price = price;
   }
}
