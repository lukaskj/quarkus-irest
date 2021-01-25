package com.lukaskj.irest.register.util;

import javax.validation.ConstraintValidatorContext;

public interface DTO {
   default boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
      return true;
   }
}
