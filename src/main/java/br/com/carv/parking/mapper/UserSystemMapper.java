package br.com.carv.parking.mapper;

import br.com.carv.parking.entity.UserSystem;
import br.com.carv.parking.payload.request.UserPostRequest;
import br.com.carv.parking.payload.request.UserPutRequest;
import br.com.carv.parking.payload.response.UserGetResponse;
import br.com.carv.parking.payload.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserSystemMapper {

    UserResponse toUserResponse(UserSystem userSystem);

    UserSystem toUserSystem(UserPostRequest userPostRequest);

    UserSystem toUserSystem(UserPutRequest userPutRequest);

    UserGetResponse toUserGetResponse(UserSystem userSystem);
}
