package io.hsjang.livepage.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class HSAuthenticationFilter implements WebFilter {

    String COOKIE_AUTH_KEY = "HS-X-AUTH";

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        MultiValueMap<String, HttpCookie> cookies = serverWebExchange.getRequest().getCookies();
        if(cookies!=null && cookies.size()>0) {
            Authentication authentication = cookies.entrySet().stream()
                                            .filter(c->c.getKey().equalsIgnoreCase(COOKIE_AUTH_KEY))
                                            .findFirst()
                                            .map(c->c.getValue().get(0).getValue())
                                            .map(this::fromCookie)
                                            .orElse(null);
            if(authentication!=null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        return webFilterChain.filter(serverWebExchange);
    }

    @SuppressWarnings("unchecked")
    private Authentication fromCookie(String cookie){
        Map<String,Object> hsXAuth = HSAuthenticationProvider.parse(cookie);
        if(hsXAuth.containsKey("roles")){
            List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
            ((List<String>) hsXAuth.get("roles")).stream().forEach(r->roles.add(new SimpleGrantedAuthority(r.toUpperCase())));
            return new HSAuthenticationToken(roles).addUser(hsXAuth);
        }
        return null;
    }

}