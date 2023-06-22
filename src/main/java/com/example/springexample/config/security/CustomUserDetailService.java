package com.example.springexample.config.security;

import com.example.springexample.entity.User;
import com.example.springexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Temporal;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String[] loginString = StringUtils.split(s, "@");

        if(loginString == null || loginString.length != 2) {
            throw new UsernameNotFoundException("inserisci il nome utente");
        }

        String username = loginString[0];
        String password = loginString[1];

        User user = userService.getUserByLogin(username, password);

        if (user == null) {
            throw new UsernameNotFoundException("username o password errati");
        }
        org.springframework.security.core.userdetails.User.UserBuilder builder;

        builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.roles(user.isAdmin() ? "ADMIN" : "USER");
        builder.password(user.getPassword());

        return builder.build();
    }
}
