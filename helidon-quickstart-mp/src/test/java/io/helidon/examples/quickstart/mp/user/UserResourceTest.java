package io.helidon.examples.quickstart.mp.user;

import io.helidon.examples.quickstart.mp.user.dto.UserFindByIdResponse;
import io.helidon.examples.quickstart.mp.user.expose.UserStatus;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@HelidonTest
class UserResourceTest {

    @Inject
    private WebTarget target;

    @Test
    void findById() {
        //when
        UserFindByIdResponse response = target.path("response/1").request().get(UserFindByIdResponse.class);
        //then
        assertThat(response.getId(), is(1));
        assertThat(response.getName(), is("daiki"));
        assertThat(response.getStatus(), is(UserStatus.ACTIVE));
    }

}
