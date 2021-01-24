package com.lukaskj.irest.register.dto.meal;

import java.math.BigDecimal;

import com.lukaskj.irest.register.dto.restaurant.RestaurantDTO;

public class MealDTO {
   public String name;

   public String description;

   public RestaurantDTO restaurant;

   public BigDecimal price;

   public String createdAt;
   
   public String updatedAt;
}
