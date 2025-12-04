package ltd.chrshnv.wakagram.adapter.mapper;

import ltd.chrshnv.wakagram.adapter.out.dto.HeartbeatDto;
import ltd.chrshnv.wakagram.domain.model.Heartbeat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HeartbeatMapper {
	Heartbeat toEntity(HeartbeatDto heartbeatDto);

	HeartbeatDto toHeartbeatDto(Heartbeat heartbeat);
}
