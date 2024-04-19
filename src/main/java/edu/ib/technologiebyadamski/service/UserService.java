package edu.ib.technologiebyadamski.service;

import edu.ib.technologiebyadamski.controller.dto.GetUserDto;
import edu.ib.technologiebyadamski.infrastructure.entity.UserEntity;
import edu.ib.technologiebyadamski.infrastructure.repository.AuthRepository;
import edu.ib.technologiebyadamski.infrastructure.repository.UserRepository;
import edu.ib.technologiebyadamski.service.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<GetUserDto> getAll(){
        var users = userRepository.findAll();
        return users.stream().map((user) -> new GetUserDto(user.getUserId(), user.getAuth().getUserName(), user.getAuth().getRole(), user.getEmail(), user.getName())).collect(Collectors.toList());
    }
    public GetUserDto getOne(long id){
        var userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::create);
        return new GetUserDto(userEntity.getUserId(), userEntity.getAuth().getUserName(), userEntity.getAuth().getRole(), userEntity.getEmail(), userEntity.getName());
    }
    public UserEntity getOneUserEntity(long id){
        return userRepository.findById(id).orElseThrow(() ->UserNotFoundException.create(id));
    }

    public void delete(long id){
        if(!userRepository.existsById(id)){
            throw UserNotFoundException.create(id);
        }
        userRepository.deleteById(id);
    }
}
