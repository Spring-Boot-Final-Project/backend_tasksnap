package com.finalProject.taskSnap.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
//    This class is used to send the token and expiration time to the client
    private String token;
    private long expiresIn;
}
