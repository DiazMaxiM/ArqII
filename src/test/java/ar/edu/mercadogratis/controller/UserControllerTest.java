package ar.edu.mercadogratis.controller;

import ar.edu.mercadogratis.application.service.UserService;
import ar.edu.mercadogratis.domain.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.http.HttpStatus;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private String baseUrl() {
        return "http://localhost:" + port + "/user";
    }

    @Test
    void testRegisterUser() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        User user = User.builder()
                .name("a name")
                .lastName("a lastname")
                .email("an_email@mail.com")
                .cuit("22332233")
                .build();

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(user), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                baseUrl() + "/register", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();

        Long newId = Long.valueOf(response.getBody());
        User createdUser = userService.getUser(newId);
        assertThat(createdUser).isNotNull();
    }


}