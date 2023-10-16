package br.com.carv.parking.service.impl;

import br.com.carv.parking.jwt.JWTProvider;
import br.com.carv.parking.jwt.response.TokenResponse;
import br.com.carv.parking.payload.request.LoginPostRequest;
import br.com.carv.parking.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthServiceImpl implements AuthService {

    private final JWTProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    private Logger logger = Logger.getLogger(AuthServiceImpl.class.getName());

    public AuthServiceImpl(JWTProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public TokenResponse login(LoginPostRequest loginPostRequest) {
        logger.info("Authentication for user: " + loginPostRequest.username());
        Authentication authenticate =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginPostRequest.username(),
                        loginPostRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = this.jwtProvider.generateToken(authenticate);
        return new TokenResponse(token, "Bearer");
    }
}
