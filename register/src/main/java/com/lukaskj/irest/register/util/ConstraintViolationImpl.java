package com.lukaskj.irest.register.util;

import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.ConstraintViolation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ConstraintViolationImpl implements Serializable {
   private static final long serialVersionUID = -2717131009988938573L;

   @Schema(description = "Attribute path (ex: initDate, person.address.number)", required = true)
   private final String attribute;

   @Schema(description = "Error message", required = true)
   private final String message;

   private ConstraintViolationImpl(ConstraintViolation<?> violation) {
      this.message = violation.getMessage();
      this.attribute = Stream.of(violation.getPropertyPath().toString().split("\\.")).skip(2)
            .collect(Collectors.joining("."));
   }

   private ConstraintViolationImpl(String message, String attribute) {
      this.message = message;
      this.attribute = attribute;
   }

   public static ConstraintViolationImpl of(ConstraintViolation<?> violation) {
      return new ConstraintViolationImpl(violation);
   }

   public static ConstraintViolationImpl of(String violation) {
      return new ConstraintViolationImpl(null, violation);
   }

   public String getMessage() {
      return this.message;
   }



}
