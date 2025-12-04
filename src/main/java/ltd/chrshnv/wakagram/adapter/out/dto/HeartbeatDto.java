package ltd.chrshnv.wakagram.adapter.out.dto;

import ltd.chrshnv.wakagram.domain.model.Heartbeat;

import java.time.Instant;

/**
 * DTO for {@link Heartbeat}
 */
public record HeartbeatDto(Instant time, String project, String language) {
}
