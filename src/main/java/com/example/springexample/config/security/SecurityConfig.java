package com.example.springexample.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;
    @Autowired
    @Qualifier("persistentTokenRepository")
    private PersistentTokenRepository persistentTokenRepository;

    DataSource dataSource;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.builder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        //
        manager.createUser(
                users.username("admin")
                        .password(new BCryptPasswordEncoder().encode("admin"))
                        .roles("ADMIN")
                        .build()
        );
        manager.createUser(
                users.username("user")
                        .password(new BCryptPasswordEncoder().encode("user"))
                        .roles("USER")
                        .build()
        );

        return manager;
    }*/

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService((userDetailsService));
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public HttpFirewall allowUrlEncoderSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);

        return firewall;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.httpFirewall(allowUrlEncoderSlashHttpFirewall());
    }

    private static final String[] ADMIN_MATCHER = {
          "/user/delete/**",
          "/car/manage/**",
          "/car/delete/**",
          "/user/detail/**",
          "/user/filter",
          "/car/all"
    };

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(ADMIN_MATCHER).access("hasRole('ADMIN')")
                .antMatchers("/booking/**").access("hasAnyRole('USER', 'ADMIN')")
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .formLogin()
                        .loginPage("/login")
                        .successForwardUrl("/home")
                        .failureUrl("/login?fail")
                            .usernameParameter("username")
                            .passwordParameter("password")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/login?authDecline")
                .and()
                    .logout()
                    .logoutUrl("/login?logout");
    }

    public AuthenticationFilter authenticationFilter() throws Exception

    {
        AuthenticationFilter filter = new AuthenticationFilter();

        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationFailureHandler(failureHandler());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setRememberMeServices(customRememberMeService());

        return filter;
    }

    public SimpleUrlAuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login?fail");
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("/home");

        return auth;
    }
    @Bean
    public PersistentTokenBasedRememberMeServices customRememberMeService() {
        String key = "ricordamiKey";

        PersistentTokenBasedRememberMeServices rememberMeServices = new CustomRememberMeService(key, userDetailsService, persistentTokenRepository);

        rememberMeServices.setCookieName("remember");
        rememberMeServices.setTokenValiditySeconds(60 * 60 * 4);
        rememberMeServices.setParameter("remember");
        rememberMeServices.setUseSecureCookie(false);

        return rememberMeServices;
    }
/*
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);

        return tokenRepository;
    }*/

}
