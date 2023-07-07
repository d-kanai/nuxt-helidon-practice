package io.helidon.examples.quickstart.mp.user;

import io.helidon.examples.quickstart.mp.user.dto.UserFindByIdResponse;
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
    void findAll() {
        UserFindByIdResponse user = target.path("user/1").request().get(UserFindByIdResponse.class);
        assertThat(user.getId(), is(1));
        assertThat(user.getName(), is("daiki"));
        assertThat(user.getStatus(), is("ACTIVE"));
    }

}