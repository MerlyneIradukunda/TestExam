package rw.ac.rca.termOneExam.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

//    tests for save(success&&fail)
    @Test
    public void create_testSuccess() {
        City theContact = new City(10L,"dd",10,20);
        ResponseEntity<City> response = restTemplate.postForEntity("/api/cities/add", theContact, City.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("dd", Objects.requireNonNull(response.getBody()).getName());
    }
    @Test
    public void create_testDuplicateName() {
        City theContact = new City(101,"Kigali",24,1);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/cities/add", theContact, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        //return type to verify
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    // test for get by id (success & fail)
    @Test
    public void findById_testSuccess() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/id/102", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void findById_testNotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/id/1", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // test for getall(success)
    @Test
    public void getAll_testSuccess() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/all", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
