package com.luisalves.userservice.facade;

import com.luisalves.userservice.model.PostResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
//@FeignClient(name = "forum-service", url = "http://localhost:9003/forums") // without load balance
@FeignClient(name = "forum-service")
public interface UserServiceProxy {

    @GetMapping("/forums/{userId}")
    PostResponse getPostByUser(@PathVariable("userId") Integer userId);
}
