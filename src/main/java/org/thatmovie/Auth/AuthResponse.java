package org.thatmovie.Auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String token;

    String id;
    String username;
    String email;
    String name;
    String surname;
    String avatar;
    String bio;



}

