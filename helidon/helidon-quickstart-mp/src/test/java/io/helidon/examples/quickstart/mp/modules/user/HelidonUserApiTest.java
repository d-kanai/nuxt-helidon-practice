package io.helidon.examples.quickstart.mp.modules.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddRequest;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddResponse;
import io.helidon.examples.quickstart.mp.modules.user.persistence.UserRepository;
import io.helidon.microprofile.tests.junit5.AddBean;
import io.helidon.microprofile.tests.junit5.AddConfig;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

@HelidonTest
@AddConfig(key = "redis.host", value = "localhost")
@AddConfig(key = "core-api.host", value = "http://localhost")
@AddConfig(key = "core-api.port", value = "8081")
@AddBean(HelidonUserApiTest.FakeUserRepositoryImpl.class)// FakeUserRepositoryImplクラスをCDIに入れる
public class HelidonUserApiTest {
    @Inject
    private WebTarget target;

    ObjectMapper mapper;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.mapper = new ObjectMapper();
    }

    @Test
    void test_ユーザ登録APIを呼び出せること() throws JsonProcessingException {
        // Arrange
        UserAddResponse expectedResponse = new UserAddResponse("XXXXYYMMDD: user is created.");

        // Act
        UserAddRequest request = new UserAddRequest();
        request.setName("jiadong.chen");
        request.setAge(39);
        Response r = target
                .path("api/v1/users")
                .request()
                .post(Entity.entity(request, MediaType.APPLICATION_JSON));
        int actualStatus = r.getStatus();
        UserAddResponse actualResponse = r.readEntity(UserAddResponse.class);

        // Assert
        assertThat(actualStatus).isEqualTo(200);
        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
    }


    @Alternative //これは代替のクラスであり、通常のUserRepositoryImplクラスの代わりにテストで使用されることを示します。
    @Priority(1) // 同じCDIのInterfaceにInjectクラスが複数ある場合に、このクラスが優先されるようにします。
    static class FakeUserRepositoryImpl implements UserRepository {

        @Override
        public void addUser(User actualUser) {
            User expectedUser = new User();
            expectedUser.setName("jiadong.chen");
            expectedUser.setAge(39);

            System.out.println("FakeUserRepositoryImpl.addUser is called.");
            assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);
        }
    }
}
