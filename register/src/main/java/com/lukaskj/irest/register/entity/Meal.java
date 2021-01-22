package com.lukaskj.irest.register.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meal")
public class Meal extends BaseEntity {
   public String name;

   public String description;

   @ManyToOne
   public Restaurant restaurant;

   public BigDecimal price;
}
