package br.com.carv.parking.controller.impl;

import br.com.carv.parking.controller.AuthController;
import br.com.carv.parking.jwt.response.TokenResponse;
import br.com.carv.parking.payload.request.LoginPostRequest;
import br.com.carv.parking.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }
    @Override
    public ResponseEntity<TokenResponse> login(LoginPostRequest loginPostRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.login(loginPostRequest));
    }
}
