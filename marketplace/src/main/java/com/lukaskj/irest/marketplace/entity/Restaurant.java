package com.lukaskj.irest.marketplace.entity;

import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

public class Restaurant extends BaseEntity {
   public String ownerId;

   public String name;

   public String companyId;

   public Location location;

   public void persist(PgPool pgPool) {
      pgPool.preparedQuery("insert into location(id, latitude, longitude) values ($1, $2, $3)")
            .execute(Tuple.of(location.id, location.latitude, location.longitude)).await()
            .indefinitely();

      pgPool.preparedQuery("insert into restaurant (id, name, location_id) values ($1, $2, $3)")
            .execute(Tuple.of(id, name, location.id)).await()
            .indefinitely();
   }

   @Override
   public String toString() {
      return super.toString();
   }
}
