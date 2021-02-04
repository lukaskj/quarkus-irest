package com.lukaskj.irest.marketplace.entity.dto;

import java.math.BigDecimal;
import io.vertx.mutiny.sqlclient.Row;

public class MealDTO {

   public Long id;

   public String name;

   public String description;

   // public Restaurant restaurant;

   public BigDecimal price;

   public static MealDTO from(Row row) {
      MealDTO dto = new MealDTO();
      dto.description = row.getString("description");
      dto.name = row.getString("name");
      dto.id = row.getLong("id");
      dto.price = row.getBigDecimal("price");
      return dto;
   }
}
