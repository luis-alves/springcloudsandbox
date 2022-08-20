package com.luisalves.userservice.controlers;

import com.luisalves.userservice.exceptions.UserNotFoundException;
import com.luisalves.userservice.model.PostResponse;
import com.luisalves.userservice.model.UserRequest;
import com.luisalves.userservice.model.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RefreshScope
@RequestMapping("/users")
public class UsersControler {

    @Value("${my.greeting: default value}")
    private String value;

    @Autowired
    private RestTemplate restTemplate;

    @Operation(summary = "Delete existing User")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable("id")
                                           @Parameter(description = "id of user to be removed")
                                           Long id) {
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get existing User")
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id")
                                    @Parameter(description = "id of user to be fetched")
                                    Long id) {
        return new UserResponse(1L, "Luis", LocalDate.now(), null);
    }

    @Operation(summary = "Get existing Users")
    @GetMapping
    public Collection<UserResponse> getAllUser() {

        List<PostResponse> postResponse
                = Stream.of(1, 2)
                        .map(this::getForObject)
                        .toList();

        return List.of(new UserResponse(1L, value, LocalDate.now(), postResponse.get(0)),
                       new UserResponse(2L, "Silvia", LocalDate.now(), postResponse.get(1)));
    }

    private PostResponse getForObject(Integer userId) {
        return restTemplate.getForObject("http://forum-service/forums/" + userId,
                PostResponse.class);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid dto supplied",
                    content = @Content)})
    @Operation(summary = "Create new User")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody
                                                   @Valid
                                                   @Parameter(description = "User to be added")
                                                   UserRequest newUser) {
        throw new UserNotFoundException("Not found my friend!");
        //return ResponseEntity.created(URI.create("/users/" + 1)).body(new UserResponse(1L, "luis", LocalDate.now()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the user",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @Operation(summary = "Update existing User")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody
                                           @Valid
                                           @Parameter(description = "User's info to be updated")
                                           UserRequest updatedUser) {
        return ResponseEntity.noContent().build();
    }

}
