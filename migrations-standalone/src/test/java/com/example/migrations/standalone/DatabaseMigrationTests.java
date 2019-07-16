package com.example.migrations.standalone;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ContextConfiguration(initializers = DatabaseMigrationTests.Initializer.class)
public class DatabaseMigrationTests {

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
    public static OutputCapture output = new OutputCapture();

    @ClassRule
    public static MySQLContainer mySqlContainer = new MySQLContainer() {
        @Override
        public String getDriverClassName() {
            return "com.mysql.cj.jdbc.Driver";
        }
    };

    @Test
    public void shouldNotContainErrors() {
        assertThat(output.toString()).doesNotContainPattern("(?i)ERROR");
    }

    @Test
    public void shouldNotContainExceptions() {
        assertThat(output.toString()).doesNotContainPattern("(?i)Exception");
    }

    @Test
    public void shouldApplyAllMigrations() {
        assertThat(output.toString()).contains("Successfully released change log lock");
    }

}
