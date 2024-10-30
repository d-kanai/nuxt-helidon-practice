package com.example.coreapi.users;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/users")
public class UserResource {

    @PersistenceContext(unitName = "postgresql")
    // @PersistenceContext(unitName = "oracle")
    EntityManager em;

    private final ExternalApiClient externalApiClient;

    @Inject
    public UserResource(ExternalApiClient externalApiClient) {
        this.externalApiClient = externalApiClient;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createUser(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setAge(userRequest.getAge());

        em.persist(userEntity);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());

        postUserIdToExternalApi(userEntity);

        return Response.status(200).entity(userResponse).build();
    }

    private void postUserIdToExternalApi(UserEntity userEntity) {
        String apiPath = "/api/v1/stat";
        System.out.println("Api Path: " + apiPath);
        WebTarget webTarget = this.externalApiClient.getWebTarget();

        //language=JSON
        String statRequest = "{\n" +
                "  \"i\":" +
                userEntity.getId() + "\n" +
                "}";
        Response response = webTarget
                .path(apiPath)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(statRequest, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            System.out.println("Successfully posted user to external API.");
        } else {
            System.out.println("Failed to post user to external API. HTTP status: " + response.getStatus());
        }
        response.close();
    }
}
