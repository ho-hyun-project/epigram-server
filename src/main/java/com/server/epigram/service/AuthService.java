package com.server.epigram.service;

import com.server.epigram.auth.JwtTokenProvider;
import com.server.epigram.auth.UserDetailsImpl;
import com.server.epigram.db.entity.User;
import com.server.epigram.db.repository.UserRepository;
import com.server.epigram.dto.JwtToken;
import com.server.epigram.dto.UserDto;
import com.server.epigram.dto.request.auth.LoginRequestDto;
import com.server.epigram.dto.request.auth.RegisterRequestDto;
import com.server.epigram.dto.response.auth.AuthResponseDto;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

        return new UserDetailsImpl(user);
    }

    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {
        boolean exists = userRepository.existsByEmail(registerRequestDto.getEmail());

        if (exists) {
            throw new RuntimeException("이미 사용 중인 아이디입니다.");
        }

        if (!Objects.equals(registerRequestDto.getPassword(), registerRequestDto.getPasswordConfirmation())) {
            throw new RuntimeException("비밀번호를 다시 확인해주세요.");
        }

        registerRequestDto.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        User user = new User();
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(registerRequestDto.getPassword());
        user.setNickName(registerRequestDto.getNickname());

        userRepository.save(user);

        JwtToken jwtToken = jwtTokenProvider.generateToken(user.getEmail());

        return AuthResponseDto.builder()
                .accessToken(jwtToken.getAccessToken())
                .refreshToken(jwtToken.getRefreshToken())
                .user(UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .nickname(user.getNickName())
                        .updatedAt(user.getUpdatedAt())
                        .createdAt(user.getCreatedAt())
                        .image(user.getImage())
                        .build())
                .build();
    }

    public AuthResponseDto authenticate(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 email 입니다."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        JwtToken jwtToken = jwtTokenProvider.generateToken(user.getEmail());

        return AuthResponseDto.builder()
                .accessToken(jwtToken.getAccessToken())
                .refreshToken(jwtToken.getRefreshToken())
                .user(UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .nickname(user.getNickName())
                        .updatedAt(user.getUpdatedAt())
                        .createdAt(user.getCreatedAt())
                        .image(user.getImage())
                        .build())
                .build();
    }

}
