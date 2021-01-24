package com.lukaskj.irest.register.dto.meal;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.lukaskj.irest.register.entity.Restaurant;

import io.smallrye.common.constraint.NotNull;

public class AddMealDTO {
   @NotEmpty
   @NotNull
   @Size(min = 2, max = 150)
   public String name;
   
   @NotEmpty
   @NotNull
   @Size(min = 2, max = 1000)
   public String description;

   // @NotNull
   // public Restaurant restaurant;

   @DecimalMin("0")
   @NotEmpty
   @NotNull
   public BigDecimal price;
}
