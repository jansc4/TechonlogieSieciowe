package edu.ib.technologiebyadamski.service;

import edu.ib.technologiebyadamski.controller.dto.CreateUserDto;
import edu.ib.technologiebyadamski.controller.dto.CreateUserResponseDto;
import edu.ib.technologiebyadamski.controller.dto.GetUserDto;
import edu.ib.technologiebyadamski.infrastructure.entity.UserEntity;
import edu.ib.technologiebyadamski.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<GetUserDto> getAll(){
        var users = userRepository.findAll();
        return users.stream().map((user) -> new GetUserDto(user.getUserId(), user.getUserName(), user.getRole(), user.getEmail(), user.getName())).collect(Collectors.toList());
    }
    public GetUserDto getOne(long id){
        var userEntity = userRepository.findById(id).orElseThrow(() ->new RuntimeException("User not found"));
        return new GetUserDto(userEntity.getUserId(), userEntity.getUserName(), userEntity.getRole(), userEntity.getEmail(), userEntity.getName());
    }
    public CreateUserResponseDto create(CreateUserDto user){
        var userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        var newUser = userRepository.save(userEntity);
        return new CreateUserResponseDto(newUser.getUserId(), newUser.getUserName(), newUser.getPassword(), newUser.getRole(), newUser.getEmail(), newUser.getName());
    }
    public void delete(long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException();
        }
        userRepository.deleteById(id);
    }
}
