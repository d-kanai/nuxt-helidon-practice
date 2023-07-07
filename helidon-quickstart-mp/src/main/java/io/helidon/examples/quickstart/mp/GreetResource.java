
package io.helidon.examples.quickstart.mp;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.Collections;

@Path("/greet")
@RequestScoped
public class GreetResource {

    private final GreetingProvider greetingProvider;

    @Inject
    public GreetResource(GreetingProvider greetingConfig) {
        this.greetingProvider = greetingConfig;
    }

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Path("/2")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getDefaultMessage2() {
        return JSON.createObjectBuilder().add("message", "Hello World").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Message getDefaultMessage() {
        return createResponse("World");
    }

    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("name") String name) {
        return createResponse(name);
    }

    @Path("/greeting")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestBody(name = "greeting", required = true, content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.OBJECT, requiredProperties = {"greeting"})))
    @APIResponses({@APIResponse(name = "normal", responseCode = "204", description = "Greeting updated"), @APIResponse(name = "missing 'greeting'", responseCode = "400", description = "JSON did not contain setting for 'greeting'")})
    public Response updateGreeting(Message message) {

        if (message.getGreeting() == null || message.getGreeting().isEmpty()) {
            Message error = new Message();
            error.setMessage("No greeting provided");
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }

        greetingProvider.setMessage(message.getGreeting());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private Message createResponse(String who) {
        String msg = String.format("%s %s!", greetingProvider.getMessage(), who);

        return new Message(msg);
    }
}
