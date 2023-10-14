package br.com.carv.parking.controller.impl;

import br.com.carv.parking.controller.UserSystemController;
import br.com.carv.parking.payload.request.PasswordResetPostRequest;
import br.com.carv.parking.payload.request.UserPostRequest;
import br.com.carv.parking.payload.request.UserPutRequest;
import br.com.carv.parking.payload.response.UserGetResponse;
import br.com.carv.parking.payload.response.UserResponse;
import br.com.carv.parking.service.UserSystemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserSystemControllerImpl implements UserSystemController {

    private final UserSystemService userSystemService;

    public UserSystemControllerImpl(UserSystemService userSystemService) {
        this.userSystemService = userSystemService;
    }

    @Override
    public ResponseEntity<EntityModel<UserResponse>> save(UserPostRequest userPostRequest, HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userSystemService.save(userPostRequest, httpServletRequest));
    }

    @Override
    public ResponseEntity<UserGetResponse> findById(UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userSystemService.findById(id));
    }

    @Override
    public ResponseEntity<Page<UserGetResponse>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userSystemService.findAll(pageable));
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        this.userSystemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> update(UserPutRequest userPutRequest) {
        this.userSystemService.update(userPutRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> changePassword(UUID id, PasswordResetPostRequest passwordResetPostRequest) {
        this.userSystemService.changePassword(id, passwordResetPostRequest.oldPassword(),
                passwordResetPostRequest.newPassword(), passwordResetPostRequest.confirmedPassword());
        return ResponseEntity.noContent().build();
    }
}
