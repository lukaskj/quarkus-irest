package com.lukaskj.irest.register.entity;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@MappedSuperclass
public class BaseEntity extends PanacheEntityBase {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long id;

   @CreationTimestamp
   public Date createdAt;

   @UpdateTimestamp
   public Date updatedAt;

}
