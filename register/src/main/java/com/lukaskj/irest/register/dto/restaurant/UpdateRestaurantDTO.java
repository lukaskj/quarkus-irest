package com.lukaskj.irest.register.dto.restaurant;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateRestaurantDTO {

   public Long id;

   @NotEmpty
   @NotNull
   public String ownerId;

   @NotEmpty
   @NotNull
   @Pattern(regexp = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")
   public String companyId;

   @NotEmpty
   @NotNull
   @Size(min = 3, max = 50)
   public String companyName;

   public LocationDTO location;
}
