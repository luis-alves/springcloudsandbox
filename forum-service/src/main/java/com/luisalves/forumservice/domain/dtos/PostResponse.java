package com.luisalves.forumservice.domain.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {
    private int id;
    private String messsage;
}
