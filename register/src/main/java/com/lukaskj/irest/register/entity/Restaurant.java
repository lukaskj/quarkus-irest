package com.lukaskj.irest.register.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {
   public String ownerId;

   public String companyId;

   public String name;

   @OneToOne(cascade = CascadeType.ALL)
   public Location location;
}
