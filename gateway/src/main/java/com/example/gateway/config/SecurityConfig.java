package com.example.gateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.server.WebSessionServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 *
 * @author KienThanh
 * **/
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity,
                                                         OAuth2ClientProperties oAuth2ClientProperties){
        httpSecurity.authorizeExchange(auth -> auth
                        .anyExchange().authenticated())
                .formLogin(Customizer.withDefaults());
        if (!oAuth2ClientProperties.getRegistration().isEmpty()) {
            httpSecurity.oauth2Login(login -> login.authorizedClientRepository(serverOAuth2AuthorizedClientRepository()))
                    .oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));
        }
        return httpSecurity.build();
    }

    @Bean
    public ServerOAuth2AuthorizedClientRepository serverOAuth2AuthorizedClientRepository() {
        return new WebSessionServerOAuth2AuthorizedClientRepository();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    MapReactiveUserDetailsService inMemoryUserDetailsManager() {
        var user = User.builder()
                .username("user")
                .password("{noop}password")
                .roles("USER")
                .build();
        var admin = User.builder()
                .username("admin")
                .password("{noop}password")
                .roles("USER", "ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user, admin);
    }
}
