package io.helidon.examples.quickstart.mp.user;

import io.helidon.examples.quickstart.mp.modules.user.internal.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.internal.dto.UserFindByIdResponse;
import io.helidon.examples.quickstart.mp.modules.user.expose.UserStatus;
import io.helidon.examples.quickstart.mp.modules.user.internal.infra.UserRepository;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@HelidonTest
class UserResourceTest {

    @Inject
    private WebTarget target;

    @BeforeEach
    void setup() {
        UserRepository.users = new ArrayList<>();
    }

    @Test
    void findById() {
        //given
        UserRepository.users.add(new User(1, "daiki", UserStatus.ACTIVE));
        //when
        UserFindByIdResponse response = target.path("user/1").request().get(UserFindByIdResponse.class);
        //then
        assertThat(response.getId(), is(1));
        assertThat(response.getName(), is("daiki"));
        assertThat(response.getStatus(), is(UserStatus.ACTIVE));
    }

}
