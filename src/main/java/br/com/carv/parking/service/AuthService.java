package br.com.carv.parking.service;

import br.com.carv.parking.jwt.response.TokenResponse;
import br.com.carv.parking.payload.request.LoginPostRequest;

public interface AuthService {

    TokenResponse login(final LoginPostRequest loginPostRequest);
}
