package edu.ib.technologiebyadamski.service;

import edu.ib.technologiebyadamski.controller.dto.LoginDto;
import edu.ib.technologiebyadamski.controller.dto.LoginResponseDto;
import edu.ib.technologiebyadamski.controller.dto.RegisterDto;
import edu.ib.technologiebyadamski.controller.dto.RegisterResponseDto;
import edu.ib.technologiebyadamski.infrastructure.entity.AuthEntity;
import edu.ib.technologiebyadamski.infrastructure.entity.UserEntity;
import edu.ib.technologiebyadamski.infrastructure.repository.AuthRepository;
import edu.ib.technologiebyadamski.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import edu.ib.technologiebyadamski.service.error.UserAlreadyExistsException;
import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDto register(RegisterDto dto){
        Optional<AuthEntity> exsistingAuth = authRepository.findByUserName(dto.getUserName());

        if (exsistingAuth.isPresent()){
            throw UserAlreadyExistsException.create(dto.getUserName());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        authEntity.setUserName(dto.getUserName());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(userEntity);

        authRepository.save(authEntity);

        return new RegisterResponseDto(authEntity.getUserName(), authEntity.getRole());
    }
    public LoginResponseDto login(LoginDto dto ){
        AuthEntity authEntity = authRepository.findByUserName(dto.getUserName()).orElseThrow(RuntimeException::new);

        if (!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())){
            throw new RuntimeException();
        }
        String token = jwtService.generateToken(authEntity);
        return new LoginResponseDto(token);
    }
}
