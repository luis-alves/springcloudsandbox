package com.luisalves.apigateway.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserResponse(Long id, String name, LocalDate birthdate) { }
