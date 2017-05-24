package com.example.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerTest_WithRandomPort {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        repository.deleteAll();
    }

    @Test
    public void postCreateWorks() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("email", "joe@example.com");
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(asList(MediaType.APPLICATION_JSON));

        User request = new User();
        request.setEmail("joe@example.com");

        ResponseEntity<User> responseEntity =
                restTemplate.exchange("/users", HttpMethod.POST,
                        new HttpEntity<>(request, headers), User.class);

        assertThat(responseEntity.getBody().getEmail(), equalTo(request.getEmail()));

         assertThat(repository.count(), equalTo(1L));
    }

    @Test
    public void getWorks() throws Exception {
        User user = new User();
        user.setEmail("random@email.com");
        repository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(asList(MediaType.APPLICATION_JSON));

        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange("/users", HttpMethod.GET,
                        new HttpEntity<>(headers), new ParameterizedTypeReference<List<User>>() {
                        });


        assertThat(responseEntity.getBody().get(0).getEmail(),
                equalTo("random@email.com"));
    }
}