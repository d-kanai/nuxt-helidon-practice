package io.helidon.examples.quickstart.mp.modules.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.persistence.UserRepository;
import io.helidon.microprofile.tests.junit5.AddBean;
import io.helidon.microprofile.tests.junit5.AddConfig;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.inject.Named;
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
@AddBean(HelidonUserApiTest.FakeUserRepositoryImpl.class)
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
    }


    @ApplicationScoped // アプリケーションスコープでこのBeanが生存することを示します。
    @Alternative //これは代替のクラスであり、通常のUserRepositoryImplクラスの代わりにテストで使用されることを示します。
    @Priority(1) // 同じCDIのInterfaceにInjectクラスが複数ある場合に、このクラスが優先されるようにします。
    static class FakeUserRepositoryImpl implements UserRepository {

        @Override
        public void addUser(User user) {
            System.out.println("FakeUserRepositoryImpl.addUser is called.");
        }
    }
}
