package ltd.chrshnv.wakagram.adapter.mapper;

import ltd.chrshnv.wakagram.adapter.out.UserEntity;
import ltd.chrshnv.wakagram.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
	User toDomain(UserEntity userEntity);

	@Mapping(target = "new", source = "isNew")
	UserEntity toUserEntity(User user, Boolean isNew);
}
