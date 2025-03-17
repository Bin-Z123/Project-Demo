package com.example.testspringsecurity.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class RegisterRequest {
    String username;
    String password;
    String email;
    String fullname;
    Boolean role;
}
