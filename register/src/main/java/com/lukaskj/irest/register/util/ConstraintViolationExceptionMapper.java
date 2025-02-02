package com.lukaskj.irest.register.util;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper
      implements ExceptionMapper<ConstraintViolationException> {

   @Override
   public Response toResponse(ConstraintViolationException ex) {
      return Response.status(Status.BAD_REQUEST).entity(ConstraintViolationRespose.of(ex)).build();
   }

}
