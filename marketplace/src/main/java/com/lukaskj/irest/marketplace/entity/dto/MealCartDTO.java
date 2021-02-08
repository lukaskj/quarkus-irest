package com.lukaskj.irest.marketplace.entity.dto;

import java.math.BigDecimal;
import io.vertx.mutiny.sqlclient.Row;

public class MealCartDTO {

   public String user;
   public Long mealId;

   public static MealCartDTO from(Row row) {
      MealCartDTO dto = new MealCartDTO();
      dto.user = row.getString("user");
      dto.mealId = row.getLong("mealId");
      return dto;
   }
}
