package com.lukaskj.irest.register.panache;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import com.lukaskj.irest.register.entity.Restaurant;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;

public class PanacheQueries {
   public void selectExemples() {
      //
      PanacheQuery<Restaurant> findAll = Restaurant.findAll();
      PanacheQuery<Restaurant> findAll2 = Restaurant
            .findAll(Sort.by("name", Direction.Ascending).and("ownerId", Direction.Descending));

      PanacheQuery<Restaurant> page = findAll.page(Page.ofSize(10));
      page.count();
      findAll.range(10, 50);

      //
      Map<String, Object> params = new HashMap<>();
      params.put("name", "value");
      Restaurant.find("select r from restaurant where name = :name", params);

      String name = "value";
      Restaurant.find("select r from restaurant where name = $1", name);

      Restaurant.find("select r from restaurant where name = :name and id = :id",
            Parameters.with(name, "value").and("id", -1));

      // Streams
      // Needs being on transaction (@transaction)
      Restaurant.stream("select r from restaurant where name = :name", params);

      Restaurant.stream("select r from restaurant where name = $1", name);

      Restaurant.stream("select r from restaurant where name = :name and id = :id",
            Parameters.with(name, "value").and("id", -1));


      Restaurant findById = Restaurant.findById(1L);

      // Persist
      Restaurant.persist(findById, findById);

      // Delete
      Restaurant.delete("delete Restaurant where id = :id", params);
      Restaurant.delete("delete Restaurant where name = $1", name);

      // Update
      Restaurant.update("update Restaurant set name = :name where id = :id", params);

      // Count
      Restaurant.count();
      Restaurant.count("select 1 from Restaurant", params);

      // Object methods
      Restaurant restaurant = new Restaurant();
      restaurant.persist();
      restaurant.persistAndFlush();
      restaurant.delete();
   }


   @Entity
   class Entity1 extends PanacheEntity { // PanacheEntity defines ID
      public String name;
   }

   @Entity
   class Entity2 extends PanacheEntityBase { // PanacheEntityBase does not define ID
      public String name;
   }

   @Entity
   class Entity3 { // Repository
      public String name;
   }

   @ApplicationScoped
   class Entity3Repository implements PanacheRepository<Entity3> {

   }
}
