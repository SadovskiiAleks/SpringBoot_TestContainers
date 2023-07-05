package com.example.conditional2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(9199);
    @Container
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(9299);


    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    public void contextLoads() {
        Integer appPort = devapp.getMappedPort(9199);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(
                "http://localhost:" + appPort
                        + "/profile", String.class);
        assertEquals("Current profile is dev",forEntity.getBody());
    }

    @Test
    public void contextLoads2() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(
                "http://localhost:"
                        + prodapp.getMappedPort(9299)
                        + "/profile", String.class);
        assertEquals("Current profile is production",forEntity.getBody());
    }
}
