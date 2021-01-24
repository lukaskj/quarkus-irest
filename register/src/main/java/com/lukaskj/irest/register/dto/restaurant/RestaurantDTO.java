package com.lukaskj.irest.register.dto.restaurant;

import javax.validation.constraints.*;

public class RestaurantDTO {
   
   public Long id;

   @NotEmpty
   @NotNull
   public String ownerId;

   @NotEmpty
   @Pattern(regexp = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")
   public String companyId;

   @NotEmpty
   @NotNull
   @Size(min = 3, max = 50)
   public String companyName;

   public LocationDTO location;

   public String createdAt;
   
   public String updatedAt;
}
