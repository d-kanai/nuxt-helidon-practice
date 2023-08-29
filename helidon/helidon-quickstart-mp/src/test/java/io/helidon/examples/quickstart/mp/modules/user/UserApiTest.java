package io.helidon.examples.quickstart.mp.modules.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;

@HelidonTest
public class UserApiTest {
    @Inject
    private WebTarget target;

    ObjectMapper mapper;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.mapper = new ObjectMapper();
    }

    @Test
    void test_ユーザ登録APIを呼び出せること() throws JsonProcessingException {
        // Arrange
        //language=JSON
        String expectedResponse = "{\n" +
                "  \"message\":" +
                "  \"user is created.\"\n" +
                "}";
        User expectedUser = new User();
        expectedUser.setName("jiadong.chen");
        expectedUser.setAge(39);


        // Act
        Response r = target
                .path("api/v1/user")
                .request()
                .post(Entity.entity("{\n" +
                        "  \"name\":" +
                        "  \"jiadong.chen\",\n" +
                        "  \"age\": 39\n" +
                        "}", MediaType.APPLICATION_JSON));
        int actualStatus = r.getStatus();
        String actualResponse = r.readEntity(String.class);

        // Assert
        assertThat(actualStatus).isEqualTo(201);
        assertThat(mapper.readTree(actualResponse)).isEqualTo(mapper.readTree(expectedResponse));
        verify(userRepository).addUser(expectedUser);
    }
}
