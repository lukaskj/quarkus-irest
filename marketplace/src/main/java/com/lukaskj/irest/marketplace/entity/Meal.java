package com.lukaskj.irest.marketplace.entity;

import java.math.BigDecimal;
import java.util.stream.StreamSupport;
import com.lukaskj.irest.marketplace.entity.dto.MealDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Tuple;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

public class Meal extends BaseEntity {
   public String name;

   public String description;

   public Restaurant restaurant;

   public BigDecimal price;

   public static Multi<MealDTO> findAll(PgPool pgPool) {
      Uni<RowSet<Row>> queryResult = pgPool.query("select * from meal").execute();
      return uniToMulti(queryResult);
      // return queryResult.onItem().transformToMulti(rowSet -> Multi.createFrom().items(() -> {
      // return StreamSupport.stream(rowSet.spliterator(), false);
      // })).onItem().transform(MealDTO::from);


      // return uniToMulti(queryResult);
   }

   public static Multi<MealDTO> findAll(PgPool pgPool, long restaurantId) {
      var queryResult =
            pgPool.preparedQuery("select * from meal where meal.restaurant_id = $1 order by name")
                  .execute(Tuple.of(restaurantId));
      return uniToMulti(queryResult);
   }

   private static Multi<MealDTO> uniToMulti(Uni<RowSet<Row>> queryResult) {
      return queryResult.onItem().transformToMulti(set -> Multi.createFrom().items(() -> {
         return StreamSupport.stream(set.spliterator(), false);
      })).onItem().transform(MealDTO::from);
   }
}
