package com.luisalves.userservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PostResponse {
    private int id;
    private String messsage;

    public PostResponse() {
    }

    public PostResponse(int id, String messsage) {
        this.id = id;
        this.messsage = messsage;
    }
}
