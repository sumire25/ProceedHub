package com.mistysoft.proceedhub.modules.participation.infrastructure;

import com.mistysoft.proceedhub.modules.participation.domain.*;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId().getValue());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setRoles(user.getRoles());
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                new UserId(entity.getId()),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRoles()
        );
    }
}
