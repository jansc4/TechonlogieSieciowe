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
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public RegisterResponseDto register(RegisterDto dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        UserEntity createdUser = userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword(dto.getPassword());
        authEntity.setUserName(dto.getUserName());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(createdUser);

        AuthEntity createdAuth = authRepository.save(authEntity);

        return new RegisterResponseDto(createdAuth.getUserName(), createdAuth.getRole());
    }
    public LoginResponseDto login(LoginDto dto ){
        AuthEntity authEntity = authRepository.findByUserName(dto.getUserName()).orElseThrow(RuntimeException::new);

        if (!authEntity.getPassword().equals(dto.getPassword())){
            throw new RuntimeException();
        }
        String token = jwtService.generateToken(authEntity);
        return new LoginResponseDto(token);
    }
}
