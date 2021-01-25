package com.lukaskj.irest.register.util;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;

public class ConstraintViolationRespose {
   private final List<ConstraintViolationImpl> violations = new ArrayList<>();

   private ConstraintViolationRespose(ConstraintViolationException exception) {
      exception.getConstraintViolations()
            .forEach(v -> violations.add(ConstraintViolationImpl.of(v)));
   }

   public static ConstraintViolationRespose of(ConstraintViolationException exception) {
      return new ConstraintViolationRespose(exception);
   }

   public List<ConstraintViolationImpl> getViolations() {
      return violations;
   }
}
