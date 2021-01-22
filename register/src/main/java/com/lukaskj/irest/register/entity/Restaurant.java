package com.lukaskj.irest.register.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {
   public String ownerId;

   public String companyId;

   public String name;

   @ManyToOne
   public Location location;
}
