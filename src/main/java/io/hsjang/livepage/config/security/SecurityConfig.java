package io.hsjang.livepage.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableRSocketSecurity
//@EnableGlobalMethodSecurity
public class SecurityConfig {

    @Autowired
    HSAuthenticationFilter hsAuthenticationFilter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf().disable()
            .addFilterBefore(hsAuthenticationFilter, SecurityWebFiltersOrder.ANONYMOUS_AUTHENTICATION)
            .authorizeExchange(exchanges ->
                exchanges
                    .anyExchange().permitAll()
            );
            // .httpBasic(withDefaults())
            // .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    PayloadSocketAcceptorInterceptor rsocketInterceptor(RSocketSecurity rsocket) {
        rsocket
            .authorizePayload(authorize ->
                authorize
                    .anyExchange().permitAll()
            );
            //.basicAuthentication(Customizer.withDefaults());
        return rsocket.build();
    }
}