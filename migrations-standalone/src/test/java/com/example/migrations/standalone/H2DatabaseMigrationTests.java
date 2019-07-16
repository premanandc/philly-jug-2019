package com.example.migrations.standalone;

import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

@DataJdbcTest(properties = "spring.main.banner-mode=off")
public class H2DatabaseMigrationTests extends AbstractDatabaseMigrationTests {


}
