package edu.ib.technologiebyadamski.controller;

import edu.ib.technologiebyadamski.controller.dto.LoginDto;
import edu.ib.technologiebyadamski.controller.dto.RegisterDto;
import edu.ib.technologiebyadamski.controller.dto.RegisterResponseDto;
import edu.ib.technologiebyadamski.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto requestBody){
        RegisterResponseDto dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public void register(@RequestBody LoginDto requestBody){
        authService.login(requestBody);
    }

}
