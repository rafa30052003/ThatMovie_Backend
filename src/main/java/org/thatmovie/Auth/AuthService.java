package org.thatmovie.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thatmovie.Jwt.JwtService;
import org.thatmovie.model.Member;
import org.thatmovie.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Member member= memberRepository.findByUsername(request.getEmail()).orElseThrow();
        String token=jwtService.getToken(member);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        Member member = Member.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode( request.getPassword()))
                .build();

        memberRepository.save(member);
        String token = jwtService.getToken(member);

        return AuthResponse.builder()
                .token(token)
                .username(member.getUsername())
                .email(member.getEmail())
                .name(member.getName())
                .surname(member.getSurname())
                .build();

    }
}
