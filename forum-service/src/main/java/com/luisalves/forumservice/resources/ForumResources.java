package com.luisalves.forumservice.resources;

import com.luisalves.forumservice.domain.dtos.PostResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forums")
public class ForumResources {

    @GetMapping("/{userId}")
    public PostResponse getPostByUser(@PathVariable("userId") Integer userId) {
        return PostResponse.builder()
                           .id(userId)
                           .messsage("This is a post for user id: " + userId)
                           .build();
    }
}
