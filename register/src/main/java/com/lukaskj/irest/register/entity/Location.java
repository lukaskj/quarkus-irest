package com.lukaskj.irest.register.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location extends BaseEntity {
   public Double latitude;
   public Double longitude;
}
