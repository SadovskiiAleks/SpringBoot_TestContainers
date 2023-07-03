import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyTest {

    @Autowired
    TestRestTemplate restTemplate;

    private static final  GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(9199);
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(9299);


    @BeforeAll
    public void setUp(){
        //devapp.start();
        //prodapp.start();
    }

    @Test
    public void contextLoads(){
        devapp.start();
        Integer appPort =devapp.getMappedPort(9199);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(
                "http://localhost:" + appPort, String.class);
        System.out.println(forEntity.getBody());
    }

    @Test
    public void contextLoads2(){
        prodapp.start();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(
                "http://localhost:" + prodapp.getMappedPort(9299), String.class);
        System.out.println(forEntity.getBody());
    }


}
