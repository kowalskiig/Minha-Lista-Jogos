package project.games.personal.controller.setup;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public interface PostgresSetup {

    PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16-alpine");

        @DynamicPropertySource
        static void localstackDynamicPropertySource(DynamicPropertyRegistry registry) {
            registry.add("spring.datasource.driverClassName", POSTGRES::getDriverClassName);
            registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
            registry.add("spring.datasource.username", POSTGRES::getUsername);
            registry.add("spring.datasource.password", POSTGRES::getPassword);

            registry.add("spring.jpa.generate-ddl", () -> "false");
            registry.add("spring.jpa.show-sql", () -> "true");
            registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
            registry.add("spring.jpa.properties.hibernate.hbm2ddl.create_namespaces", () -> "false");
        }

    @BeforeAll
    static void setPostgres(){
            POSTGRES.start();
    }

}
