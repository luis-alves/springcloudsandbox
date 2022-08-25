package com.luisalves.forumservice.resources;

import com.luisalves.forumservice.domain.dtos.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forums")
public class ForumResources {

    private int number = 1;

    @Autowired
    private Environment environment;

    @GetMapping("/{userId}")
    public PostResponse getPostByUser(@PathVariable("userId") Integer userId) {


        String property = environment.getProperty("server.port");
        return PostResponse.builder()
                           .id(userId)
                           .messsage("This is a post for user id: " + property)
                           .build();
    }
}
