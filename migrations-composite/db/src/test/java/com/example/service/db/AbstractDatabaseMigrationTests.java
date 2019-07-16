package com.example.service.db;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
public abstract class AbstractDatabaseMigrationTests {
    @ClassRule
    public static OutputCapture output = new OutputCapture();

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
