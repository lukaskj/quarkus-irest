package com.lukaskj.irest.register;

import java.util.HashMap;
import java.util.Map;
import org.testcontainers.containers.PostgreSQLContainer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class RegisterTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

   public static PostgreSQLContainer<?> POSTGRES;

   @Override
   public Map<String, String> start() {
      if (POSTGRES == null) {
         POSTGRES = new PostgreSQLContainer<>("postgres:13-alpine");
      }
      POSTGRES.start();
      System.out.println(POSTGRES.getJdbcUrl());
      System.out.println(POSTGRES.getJdbcUrl());
      System.out.println(POSTGRES.getJdbcUrl());
      System.out.println(POSTGRES.getJdbcUrl());
      HashMap<String, String> props = new HashMap<String, String>();

      props.put("quarkus.datasource.db-kind", "postgres");
      props.put("quarkus.datasource.jdbc.url", POSTGRES.getDatabaseName());
      props.put("quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl());
      props.put("quarkus.datasource.username", POSTGRES.getUsername());
      props.put("quarkus.datasource.password", POSTGRES.getPassword());

      return props;
   }

   @Override
   public void stop() {
      if (POSTGRES != null && POSTGRES.isRunning()) {
         POSTGRES.stop();
      }
   }
}
