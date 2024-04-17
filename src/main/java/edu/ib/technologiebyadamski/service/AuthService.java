package edu.ib.technologiebyadamski.service;

import edu.ib.technologiebyadamski.controller.dto.LoginDto;
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
    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
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
    public void login(LoginDto dto ){
        AuthEntity authEntity = authRepository.findByUserName(dto.getUserName()).orElseThrow(RuntimeException::new);

        if (!authEntity.getPassword().equals(dto.getPassword())){
            throw new RuntimeException();
        }
    }
}
