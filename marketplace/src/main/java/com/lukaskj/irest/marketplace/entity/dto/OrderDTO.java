package com.lukaskj.irest.marketplace.entity.dto;

import java.util.List;

public class OrderDTO {
   public List<MealOrderDTO> meals;
   public RestaurantDTO restaurant;
   public String client;
}
