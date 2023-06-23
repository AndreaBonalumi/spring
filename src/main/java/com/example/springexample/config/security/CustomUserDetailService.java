package com.example.springexample.config.security;

import com.example.springexample.entity.User;
import com.example.springexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("username o password errati");
        }
        org.springframework.security.core.userdetails.User.UserBuilder builder;

        builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.disabled(false);
        if (user.isAdmin()) {
            builder.authorities("ROLE_ADMIN");
        } else {
            builder.authorities("ROLE_USER");
        }
        builder.password(user.getPassword());

        return builder.build();
    }
}
