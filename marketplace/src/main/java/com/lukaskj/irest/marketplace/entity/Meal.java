package com.lukaskj.irest.marketplace.entity;

import java.math.BigDecimal;

public class Meal extends BaseEntity {
   public String name;

   public String description;

   public Restaurant restaurant;

   public BigDecimal price;
}
