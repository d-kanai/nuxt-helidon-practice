package io.helidon.examples.quickstart.mp.modules.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.persistence.UserRepository;
import io.helidon.microprofile.tests.junit5.AddConfig;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@HelidonTest
@AddConfig(key = "redis.host", value = "localhost")
@AddConfig(key = "core-api.host", value = "http://localhost")
@AddConfig(key = "core-api.port", value = "8081")
public class HelidonUserApiTest {
    @Inject
    private WebTarget target;

    ObjectMapper mapper;

    @InjectMocks
    private UserResource userResource;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.mapper = new ObjectMapper();
    }

//    @Disabled
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

        doNothing().when(userRepository).addUser(any(User.class));


        // Act
        Response r = target
                .path("api/v1/users")
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
        // userRepository.addUser()が呼ばれた時の引数をチェック
//        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
//        verify(userRepository).addUser(captor.capture());
//        User actualUser = captor.getValue();
//        assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);
    }
}
