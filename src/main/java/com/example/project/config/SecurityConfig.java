package com.example.project.config;

import com.example.project.domain.Role;
import com.example.project.domain.User;
import com.example.project.security.JwtTokenFilter;
import com.example.project.security.SfgPasswordEncoderFactories;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/auth/login",
                        "/api/v1/auth","/api/v1/auth/new-account"
                        ,"/api/v1/auth/confirmAccountRegister",
                        "/api/v1/auth/confirmEmailChange",
                        "/h2-console/**","/ws/**","/api/v1/notify/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage()))
                .and().headers().frameOptions().sameOrigin()
                .and().cors().and().csrf().disable();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return SfgPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
