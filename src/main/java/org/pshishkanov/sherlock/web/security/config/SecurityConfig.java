package org.pshishkanov.sherlock.web.security.config;

import org.pshishkanov.sherlock.web.security.model.Role;
import org.pshishkanov.sherlock.web.security.model.Account;
import org.pshishkanov.sherlock.web.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by pshishkanov on 21/04/15.
 */

@EnableWebSecurity
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/sherlock/api/user/**").hasAuthority(Role.ROLE_ADMIN.name())
            .antMatchers("/sherlock/api/check/**").hasAuthority(Role.ROLE_USER.name())
            .and()
            .httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    UserDetailsService getUserDetailsService() {
        return new UserDetailsService() {

            @Autowired
            private AccountService accountService;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Account account = accountService.getAccountByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username=%s was not found", username)));

                return new User(account.getUsername(),
                                account.getPasswordHash(),
                                true,
                                true,
                                true,
                                true,
                                AuthorityUtils.createAuthorityList(account.getRole().name()));
            }
        };
    }
}

