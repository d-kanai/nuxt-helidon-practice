package io.helidon.examples.quickstart.mp.modules.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddRequest;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddResponse;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserApiTest {
    @InjectMocks
    private UserResource target;

    @Mock
    private UserRepository userRepository;

    @Test
    void test_ユーザ登録APIを呼び出せること() throws JsonProcessingException {
        // Arrange
        UserAddResponse expectedResponse = new UserAddResponse();
        expectedResponse.setMessage("user is created.");
        User expectedUser = new User();
        expectedUser.setName("jiadong.chen");
        expectedUser.setAge(39);

        // Act
        UserAddRequest request = new UserAddRequest();
        request.setName("jiadong.chen");
        request.setAge(39);
        Response r = target.addUser(request);
        int actualStatus = r.getStatus();
        UserAddResponse actualResponse = (UserAddResponse) r.getEntity();

        // Assert
        assertThat(actualStatus).isEqualTo(201);
        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);

        // userRepository.addUser()が呼ばれた時の引数をチェック
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).addUser(captor.capture());
        User actualUser = captor.getValue();
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);
    }
}
