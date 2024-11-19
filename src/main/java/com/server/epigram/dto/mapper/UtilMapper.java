package com.server.epigram.dto.mapper;

import com.server.epigram.db.entity.Epigram;
import com.server.epigram.db.entity.User;
import com.server.epigram.service.EpigramService;
import com.server.epigram.service.UserService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UtilMapper {

    @Named("mapUser")
    default User mapUser(Long userId, @Context UserService userService) {
        return userService.findUserById(userId);
    }

    @Named("mapEpigram")
    default Epigram mapEpigram(Long epigramId, @Context EpigramService epigramService) {
        return epigramService.findEpigramById(epigramId);
    }

}
