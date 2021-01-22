package com.lukaskj.irest.register.entity;

import java.math.BigDecimal;
import javax.persistence.ManyToOne;

public class Meal extends BaseEntity {
   public String name;

   public String description;

   @ManyToOne
   public Restaurant restaurant;

   public BigDecimal price;
}
