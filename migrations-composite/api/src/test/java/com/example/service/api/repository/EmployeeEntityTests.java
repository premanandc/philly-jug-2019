package com.example.service.api.repository;

import com.example.service.api.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {"spring.liquibase.enabled=true",
        "spring.liquibase.change-log=classpath:/migrations.xml"})
public class EmployeeEntityTests {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldSave() {
        Employee entity = new Employee(UUID.randomUUID().toString(),
                "Tester", "Tester", "test@email.com");
        Employee employee = entityManager.persistFlushFind(entity);
        assertThat(employee.getId()).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Tester");
        assertThat(employee.getLastName()).isEqualTo("Tester");
        assertThat(employee.getEmailAddress()).isEqualTo("test@email.com");
    }
}