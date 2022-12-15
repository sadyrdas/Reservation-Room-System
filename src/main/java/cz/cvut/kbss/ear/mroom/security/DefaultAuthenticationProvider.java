package cz.cvut.kbss.ear.mroom.security;

import cz.cvut.kbss.ear.mroom.security.model.AuthenticationToken;
import cz.cvut.kbss.ear.mroom.security.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAuthenticationProvider.class);

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Objects.requireNonNull(authentication, "Cannot authenticate: authentication is null!");

        if(authentication.isAuthenticated()){
            return SecurityContextHolder.getContext().getAuthentication();
        }

        final UserDetails userDetails = (UserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if(userDetails == null){
            throw new UsernameNotFoundException("User is not found.");
        }
        Object creds = authentication.getCredentials();
        String password = creds == null ? null : creds.toString();

        if(passwordEncoder.matches(password, userDetails.getPassword())){
            userDetails.eraseCredentials();
            return SecurityUtils.setCurrentUser(userDetails);
        }
        else{
            throw new BadCredentialsException("Wrong password.");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass) ||
                AuthenticationToken.class.isAssignableFrom(aClass);
    }
}
