package br.com.carv.parking.service.impl;

import br.com.carv.parking.controller.impl.UserSystemControllerImpl;
import br.com.carv.parking.entity.UserInfoSystem;
import br.com.carv.parking.entity.UserSystem;
import br.com.carv.parking.exception.PasswordMissMatchException;
import br.com.carv.parking.mapper.UserSystemMapper;
import br.com.carv.parking.repository.UserSystemRepository;
import br.com.carv.parking.service.UserSystemService;
import br.com.carv.parking.exception.ResourceAlreadyUsedException;
import br.com.carv.parking.exception.ResourceNotFoundException;
import br.com.carv.parking.payload.request.UserPostRequest;
import br.com.carv.parking.payload.request.UserPutRequest;
import br.com.carv.parking.payload.response.UserGetResponse;
import br.com.carv.parking.payload.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserSystemServiceImpl implements UserSystemService {

    private final Logger logger = Logger.getLogger(UserSystemServiceImpl.class.getName());
    private final UserSystemMapper userSystemMapper;
    private final UserSystemRepository userSystemRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSystemServiceImpl(UserSystemMapper userSystemMapper, UserSystemRepository userSystemRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userSystemMapper = userSystemMapper;
        this.userSystemRepository = userSystemRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EntityModel<UserResponse> save(UserPostRequest userPostRequest, HttpServletRequest httpServletRequest) {
        verifyEmail(userPostRequest.email());
        verifyUsername(userPostRequest.username());
        UserSystem userSystem = userSystemMapper.toUserSystem(userPostRequest);
        userSystem.setPasswordHash(passwordEncoder.encode(userPostRequest.passwordHash()));
        userSystem.setActive(true);
        userSystem.setUserInfoSystem(getInfoSystem(httpServletRequest));
        UserResponse userResponse =
                userSystemMapper.toUserResponse(this.userSystemRepository.save((userSystem)));
        EntityModel<UserResponse> response = EntityModel.of(userResponse);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserSystemControllerImpl.class)
                        .findById(userResponse.getId())).withSelfRel();
        response.add(link);
        return response;
    }

    @Override
    public UserGetResponse findById(UUID id) {
        logger.info("Getting user by id: " + id);
        return this.userSystemRepository.findById(id).filter(UserSystem::getActive)
                .stream().findFirst()
                .map(userSystemMapper::toUserGetResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User not found into database!Id: " + id));
    }

    @Override
    public Page<UserGetResponse> findAll(Pageable pageable) {
        List<UserGetResponse> response =
                this.userSystemRepository.findAll(pageable).map(userSystemMapper::toUserGetResponse).stream().toList();
        return new PageImpl<UserGetResponse>(response, pageable, response.size());
    }

    @Override
    public void update(UserPutRequest userPutRequest) {
        UserSystem userSystem = userSystemMapper.toUserSystem(userPutRequest);
        userSystem.setActive(true);
        this.userSystemRepository.save(userSystem);
    }

    @Override
    public void delete(UUID id) {
        UserSystem user = findEntityById(id);
        user.setActive(false);
        this.userSystemRepository.save(user);
    }

    @Override
    public UserSystem findEntityById(UUID id) {
        logger.info("Getting entity by id: " + id);
        return this.userSystemRepository.findById(id).filter(UserSystem::getActive).stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found! Id: " + id));
    }

    @Override
    public void changePassword(UUID id, String oldPassword, String newPassword, String confirmedPassword) {
        if (!newPassword.equals(confirmedPassword)) {
            throw new PasswordMissMatchException("Confirm fail. Please enter the same password.");
        }
        UserSystem user = findEntityById(id);
        if (passwordEncoder.matches(user.getPasswordHash(), passwordEncoder.encode(oldPassword))) {
            user.setPasswordHash(passwordEncoder.encode(newPassword));
            user.setPasswordResetHashAt(LocalDateTime.now());
            user.setPasswordResetHash(passwordEncoder.encode(newPassword));
            userSystemRepository.save(user);
        }

        throw new PasswordMissMatchException("Cannot change password. Password not confer.");
    }

    @Override
    public void verifyUsername(String username) {
        Optional<UserSystem> user = this.userSystemRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new ResourceAlreadyUsedException("Username Unavailable.");
        }
    }

    @Override
    public void verifyEmail(String email) {
        Optional<UserSystem> user = this.userSystemRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new ResourceAlreadyUsedException("Email Unavailable.");
        }

    }

    private UserInfoSystem getInfoSystem(HttpServletRequest httpServletRequest) {
        if (httpServletRequest != null) {
            return new UserInfoSystem(httpServletRequest.getHeader("User-Agent"),
                    httpServletRequest.getRemoteAddr(), httpServletRequest.getLocale().toString());
        }
        return new UserInfoSystem();
    }
}
