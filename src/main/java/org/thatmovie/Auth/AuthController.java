package org.thatmovie.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thatmovie.Auth.Response.LoginResponse;
import org.thatmovie.Auth.Response.SignUpResponse;
import org.thatmovie.model.Member;
import org.thatmovie.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginResponse loginResponse) {
        String username = loginResponse.getUsername();
        String password = loginResponse.getPassword();

        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, member.getPassword())) {
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("username", member.getUsername());
                responseBody.put("email", member.getEmail());
                return ResponseEntity.ok(responseBody);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpResponse signUpResponse) {
        if (signUpResponse.getPassword() == null || signUpResponse.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("La contraseña no puede ser nula o vacía");
        }

        Optional<Member> memberOptional = memberRepository.findByUsername(signUpResponse.getUsername());
        if (memberOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(signUpResponse.getPassword());

        Member newMember = new Member();
        newMember.setUsername(signUpResponse.getUsername());
        newMember.setEmail(signUpResponse.getEmail());
        newMember.setName(signUpResponse.getName());
        newMember.setSurname(signUpResponse.getSurname());
        newMember.setPassword(hashedPassword);

        memberRepository.save(newMember);

        return ResponseEntity.ok()
                .body(Map.of("message", "Usuario creado exitosamente", "userId", newMember.getId()));

    }



}
