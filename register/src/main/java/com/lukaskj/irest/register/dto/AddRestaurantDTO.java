package com.lukaskj.irest.register.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import io.smallrye.common.constraint.NotNull;

public class AddRestaurantDTO {
   // NotNull adds required asterisk to swagger documentation
   @NotEmpty
   @NotNull
   public String ownerId;
   
   @NotEmpty
   @Pattern(regexp = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")
   public String companyId;

   @Size(min = 3, max = 50)
   public String companyName;

   public LocationDTO location;
}
