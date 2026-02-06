package org.example.dao;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractPostgresTest {

    protected static final PostgreSQLContainer<?> POSTGRES =
            new PostgreSQLContainer<>("postgres:16.3")
                    .withDatabaseName("test_db")
                    .withUsername("test")
                    .withPassword("test");

    static {
        POSTGRES.start();

        System.setProperty("hibernate.connection.url", POSTGRES.getJdbcUrl());
        System.setProperty("hibernate.connection.username", POSTGRES.getUsername());
        System.setProperty("hibernate.connection.password", POSTGRES.getPassword());
    }
}