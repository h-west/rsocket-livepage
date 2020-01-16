package io.hsjang.livepage.config.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class HSAuthenticationToken extends AbstractAuthenticationToken {
    
    private static final long serialVersionUID = 1L;

    Map<String,Object> user;

    public HSAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
		if(authorities!=null && authorities.size()>0) {
			setAuthenticated(true);
		}
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Map<String,Object> getPrincipal() {
        return user;
    }

    public HSAuthenticationToken addUser(Map<String,Object> user){
        this.user = user;
        return this;
    }

}