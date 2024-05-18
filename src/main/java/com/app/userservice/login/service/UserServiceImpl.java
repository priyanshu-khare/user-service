package com.app.userservice.login.service;

import com.app.userservice.login.entity.CustomUserDetails;
import com.app.userservice.login.entity.UserInfo;
import com.app.userservice.login.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals("John")) {
            throw new UsernameNotFoundException("User not found");
        }
        UserInfo userInfo = new UserInfo();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("123");
        userInfo.setUsername("John");
        userInfo.setPassword(encodedPassword);
        logger.info("User Logged In Successfully");
//        UserInfo userInfo = userRepository.findByUserName(username);


        return new CustomUserDetails(userInfo);
    }
}
