package ltd.chrshnv.wakagram.adapter.mapper;

import ltd.chrshnv.wakagram.adapter.out.MessageEntity;
import ltd.chrshnv.wakagram.domain.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {
	Message toDomain(MessageEntity messageEntity);

	@Mapping(target = "new", source = "isNew")
	MessageEntity toMessageEntity(Message message, boolean isNew);
}
