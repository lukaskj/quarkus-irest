package com.lukaskj.irest.order.entity;

import com.lukaskj.irest.order.entity.dto.MealOrderDTO;
import org.bson.types.Decimal128;

public class Meal {
   public String name;

   public String description;

   public Decimal128 price;

   public static Meal from(MealOrderDTO dto) {
      Meal m = new Meal();
      m.price = new Decimal128(dto.price);
      m.description = dto.description;
      m.name = dto.name;
      return m;
   }
}
