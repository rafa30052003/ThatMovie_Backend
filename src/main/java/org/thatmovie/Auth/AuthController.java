package org.thatmovie.Auth;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thatmovie.Auth.Response.LoginResponse;
import org.thatmovie.model.Member;
import org.thatmovie.repository.MemberRepository;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private MemberRepository memberRepository;

    //Funcion Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginResponse loginResponse) {
        String username = loginResponse.getUsername();
        String password = loginResponse.getPassword();

        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            if (password.equals(member.getPassword())) {
                return ResponseEntity.ok("Login exitoso para el usuario: " + username);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Member member) {
        Optional<Member> memberOptional = memberRepository.findByUsername(member.getUsername());
        if (memberOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }
        memberRepository.save(member);
        return ResponseEntity.ok("Usuario creado exitosamente");
    }


}
