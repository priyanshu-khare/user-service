package com.app.userservice.login.controller;


import com.app.userservice.auth.dto.AuthRequestDto;
import com.app.userservice.auth.dto.AuthRequestDto.*;
import com.app.userservice.auth.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/api/login")
    public JwtResponseDto login(@RequestBody AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
        if(authentication.isAuthenticated()) {
            JwtResponseDto jwtResponseDto = new JwtResponseDto();
            jwtResponseDto.setAccessToken(jwtService.generateToken(authRequestDto.getUsername()));
            return jwtResponseDto;
            //return JwtResponseDto.builder().accessToken(jwtService.generateToken(authRequestDto.getUsername())).build();
        }
        else {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }
}
