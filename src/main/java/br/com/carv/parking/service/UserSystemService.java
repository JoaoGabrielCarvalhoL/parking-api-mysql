package br.com.carv.parking.service;

import br.com.carv.parking.entity.UserSystem;
import br.com.carv.parking.payload.request.UserPostRequest;
import br.com.carv.parking.payload.request.UserPutRequest;
import br.com.carv.parking.payload.response.UserGetResponse;
import br.com.carv.parking.payload.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.util.UUID;

public interface UserSystemService {

    EntityModel<UserResponse> save(final UserPostRequest userPostRequest, final
                                   HttpServletRequest httpServletRequest);

    UserGetResponse findById(final UUID id);

    Page<UserGetResponse> findAll(final Pageable pageable);

    void update(final UserPutRequest userPutRequest);

    void delete(final UUID id);

    UserSystem findEntityById(final UUID id);

    void changePassword(final UUID id, final String oldPassword,
                        final String newPassword, final String confirmedPassword);

    void verifyUsername(String username);
    void verifyEmail(String email);
}
