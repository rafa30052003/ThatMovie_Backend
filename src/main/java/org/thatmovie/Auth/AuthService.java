package org.thatmovie.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        Member userDetails = memberRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.getToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .email(userDetails.getEmail())
                .name(userDetails.getName())
                .surname(userDetails.getSurname())
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
