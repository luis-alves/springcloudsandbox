package com.luisalves.userservice.service;

import com.luisalves.userservice.facade.UserServiceProxy;
import com.luisalves.userservice.model.PostResponse;
import com.luisalves.userservice.model.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {
    @Value("${my.greeting: default value}")
    private String value;

    @Autowired
    private UserServiceProxy userServiceProxy;

    @CircuitBreaker(name = "userService",
                    fallbackMethod = "getFallbackUsers"
    )
    public Collection<UserResponse> getUserInfo() {
        List<PostResponse> postResponse = Stream.of(1, 2)
                                                .map(this::getPostMessage)
                                                .toList();

        return List.of(new UserResponse(1L, value, LocalDate.now(), postResponse.
                        get(0)),
                new UserResponse(2L, "Silvia", LocalDate.now(), postResponse.
                        get(1)));
    }

    public Collection<UserResponse> getFallbackUsers(Exception e) {
        return List.of(new UserResponse(10L,
                        value,
                        LocalDate.now(),
                        PostResponse.builder().id(10).messsage("Fallback message").build()),
                new UserResponse(2L,
                        "Silvia",
                        LocalDate.now(),
                        PostResponse.builder().id(10).messsage("Fallback message").build()));
    }

    private PostResponse getPostMessage(Integer userId) {
        return userServiceProxy.getPostByUser(userId);
    }

}