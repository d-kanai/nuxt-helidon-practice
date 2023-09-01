package com.example.coreapi.users;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/users")
public class UserResource {

    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setAge(userRequest.getAge());

        em.persist(userEntity);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());

        return userResponse;
    }
}
