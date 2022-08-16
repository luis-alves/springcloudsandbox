package com.luisalves.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRequest(@NotBlank @Size(min = 1, max = 20) String name,
                          LocalDate birthdate) {
}
