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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@HelidonTest
public class UserApiTest2 {
    @Inject
    private WebTarget target;

    ObjectMapper mapper;

    @Inject
    private UserResource userResource;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.mapper = new ObjectMapper();
        userResource.setUserRepository(userRepository);
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

        doNothing().when(userRepository).addUser(any(User.class));


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
        // userRepository.addUser()が呼ばれた時の引数をチェック
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).addUser(captor.capture());
        User actualUser = captor.getValue();
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);
    }
}