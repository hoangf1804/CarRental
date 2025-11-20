package com.kodlamaio.filterservice.configuration.security;

import com.kodlamaio.common.constants.Paths;
import com.kodlamaio.common.constants.Roles;
import com.kodlamaio.common.security.converter.KeycloakJwtRoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private static final String[] PUBLIC_SWAGGER_ENDPOINTS = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    private static final String[] ALLOWED_ROLES = {
            Roles.Admin,
            Roles.Developer,
            Roles.Moderator,
            Roles.User
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        configureCsrf(http);
        configureAuthorization(http);
        configureJwt(http);
        return http.build();
    }

    private void configureCsrf(HttpSecurity http) throws Exception {
        http.csrf().disable(); // Explicitly disabled for API usage
    }

    private void configureAuthorization(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(PUBLIC_SWAGGER_ENDPOINTS).permitAll()
                .antMatchers(Paths.PrometheusMetricsPath).permitAll()
                .antMatchers(Paths.Filter.Prefix).hasAnyRole(ALLOWED_ROLES)
                .anyRequest().authenticated();
    }

    private void configureJwt(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtConverter());
    }

    private JwtAuthenticationConverter jwtConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakJwtRoleConverter());
        return converter;
    }
}
