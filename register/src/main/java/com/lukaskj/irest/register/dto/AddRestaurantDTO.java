package com.lukaskj.irest.register.dto;

import javax.validation.constraints.Size;

public class AddRestaurantDTO {
   public String ownerId;

   public String companyId;

   public String companyName;

   public LocationDTO location;
}
