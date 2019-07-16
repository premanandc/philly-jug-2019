package com.example.service.db;

import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;

@ContextConfiguration(initializers = MySqlDatabaseMigrationTests.Initializer.class)
public class MySqlDatabaseMigrationTests extends AbstractDatabaseMigrationTests {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + mySqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mySqlContainer.getUsername(),
                    "spring.datasource.password=" + mySqlContainer.getPassword(),
                    "spring.liquibase.dropFirst=true",
                    "spring.liquibase.testRollbackOnUpdate=true"
            ).applyTo(context.getEnvironment());
        }
    }

    @ClassRule
    public static MySQLContainer mySqlContainer = new MySQLContainer() {
        @Override
        public String getDriverClassName() {
            return "com.mysql.cj.jdbc.Driver";
        }
    };


}
