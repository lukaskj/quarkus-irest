package com.lukaskj.irest.register.dto.restaurant;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.lukaskj.irest.register.entity.Restaurant;
import com.lukaskj.irest.register.util.DTO;
import com.lukaskj.irest.register.util.ValidDTO;
@ValidDTO
public class AddRestaurantDTO implements DTO {
   @NotEmpty
   // @Pattern(regexp = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")
   @Size(min = 14, max = 14)
   public String companyId;

   @Size(min = 3, max = 50)
   public String companyName;

   public LocationDTO location;

   @Override
   public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      if (Restaurant.find("companyId", companyId).count() > 0) {
         constraintValidatorContext
               .buildConstraintViolationWithTemplate("Company ID already exists")
               .addPropertyNode("companyId").addConstraintViolation();
         return false;
      }
      return true;
   }
}
