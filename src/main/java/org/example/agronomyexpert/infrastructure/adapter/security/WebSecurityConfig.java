package org.example.agronomyexpert.infrastructure.adapter.security;

import org.example.agronomyexpert.domain.model.enums.AccessLevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final EmployeeAuthenticationFilter employeeAuthenticationFilter;

    @Autowired
    public WebSecurityConfig(EmployeeAuthenticationFilter employeeAuthenticationFilter) {
        this.employeeAuthenticationFilter = employeeAuthenticationFilter;
    }

    protected static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/employees/login"
    };

    protected static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/clients",
            "/clients/{clientId}",
            "/categories",
            "/categories/{categoryId}",
            "/products",
            "/products/{productId}",
            "/carts",
            "/carts/resume-cart/{cartId}",
            "/cart-product/add",
            "/cart-product/remove"
    };

    protected static final String [] ENDPOINTS_WITH_ADMIN_ACCESS_LEVEL = {
            "/roles",
            "/roles/{roleId}",
            "/employees",
            "/employees/{employeeId}",
            "/carts/{cartId}"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                        .requestMatchers(ENDPOINTS_WITH_ADMIN_ACCESS_LEVEL).hasAuthority(AccessLevelEnum.ADMINISTRATIVO.name())
                        .anyRequest().denyAll())
                .addFilterBefore(employeeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
