package br.com.carv.parking.service.impl;

import br.com.carv.parking.entity.UserSystem;
import br.com.carv.parking.exception.ResourceNotFoundException;
import br.com.carv.parking.repository.UserSystemRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserSystemRepository userSystemRepository;
    private final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    public UserDetailsServiceImpl(UserSystemRepository userSystemRepository) {
        this.userSystemRepository = userSystemRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Getting user by username: " + username);
        Optional<UserSystem> user = this.userSystemRepository.findByUsername(username);
        if (user.isPresent()) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.get().getRole().name());
            authorities.add(simpleGrantedAuthority);
            return new User(user.get().getUsername(), user.get().getPasswordHash(), authorities);
        }
        throw new ResourceNotFoundException("User not found into database. Username: " + username);
    }
}
