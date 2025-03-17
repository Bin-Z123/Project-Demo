package com.example.testspringsecurity.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null values
public class ApiResponse<T>  {
    int code = 9999;
    String message;
    LocalDateTime createdAt = LocalDateTime.now();
    T result;
}
