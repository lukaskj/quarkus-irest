package com.lukaskj.irest.marketplace.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import com.lukaskj.irest.marketplace.entity.dto.MealCartDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

public class MealCart extends BaseEntity {
   public String client;
   public Long mealId;

   private static MealCart toMealCart(Row row) {
      MealCart mc = new MealCart();
      mc.client = row.getString("client");
      mc.mealId = row.getLong("meal_id");
      return mc;
   }


   public static Uni<List<MealCart>> findCart(PgPool pgPool, String client) {
      Uni<RowSet<Row>> queryResult = pgPool
            .preparedQuery("select * from meal_client where client = $1").execute(Tuple.of(client));
      return queryResult.map(rowSet -> {
         List<MealCart> list = new ArrayList<>(rowSet.size());
         for (Row r : rowSet) {
            list.add(toMealCart(r));
         }
         return list;
      });
      // return queryResult.onItem().transformToMulti(rowSet -> Multi.createFrom().items(() -> {
      // return StreamSupport.stream(rowSet.spliterator(), false);
      // })).onItem().transform(MealCartDTO::from);


      // return uniToMulti(queryResult);
   }

   public static Uni<Long> save(PgPool pgClient, String client, Long meal) {
      return pgClient
            .preparedQuery(
                  "INSERT INTO meal_client (client, meal_id) values ($1, $2) returning meal_id")
            .execute(Tuple.of(client, meal))
            .map(rowSet -> rowSet.iterator().next().getLong("meal_id"));
   }

   public static Uni<Boolean> delete(PgPool pgClient, String client) {
      return pgClient.preparedQuery("DELETE FROM meal_client where client = $1")
            .execute(Tuple.of(client)).map(rowSet -> true);
   }

   protected static Multi<MealCartDTO> uniToMulti(Uni<RowSet<Row>> queryResult) {
      return queryResult.onItem().transformToMulti(set -> Multi.createFrom().items(() -> {
         return StreamSupport.stream(set.spliterator(), false);
      })).onItem().transform(MealCartDTO::from);
   }
}
