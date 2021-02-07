package com.lukaskj.irest.order.entity;

import java.util.List;
import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "orders", database = "order")
public class Order extends PanacheMongoEntity {

   public String client;

   public List<Meal> meals;

   public String deliveryman;
   
   public Location deliverymanLocation;

   public Restaurant restaurant;

}