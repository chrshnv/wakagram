package ltd.chrshnv.wakagram.domain.model;

import java.time.Instant;

public record Heartbeat(
	Instant time,
	String project,
	String language
) {
}
