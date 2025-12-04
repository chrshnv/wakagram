package ltd.chrshnv.wakagram.adapter.out;

import ltd.chrshnv.wakagram.adapter.mapper.HeartbeatMapper;
import ltd.chrshnv.wakagram.adapter.out.dto.HeartbeatDto;
import ltd.chrshnv.wakagram.adapter.out.dto.HeartbeatListDto;
import ltd.chrshnv.wakagram.domain.model.Heartbeat;
import ltd.chrshnv.wakagram.domain.out.WakatimeFetcher;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.time.LocalDate;

@Component
public class HTTPWakatimeFetcher implements WakatimeFetcher {

	private final RestClient restClient;

	private final HeartbeatMapper heartbeatMapper;

	public HTTPWakatimeFetcher(RestClient restClient,
							   HeartbeatMapper heartbeatMapper) {
		this.restClient = restClient;
		this.heartbeatMapper = heartbeatMapper;
	}

	@Override
	public @NonNull Heartbeat fetchLastForUser(@NonNull String apiKey) {
		LocalDate currentDate = LocalDate.now();

		HeartbeatListDto body = restClient
			.get()
			.uri(uriBuilder -> uriBuilder
				.path("/users/current/heartbeats")
				.queryParam("date", currentDate.toString())
				.queryParam("api_key", apiKey)
				.build()
			)
			.retrieve()
			.body(HeartbeatListDto.class);

		if (body == null)
			throw new RuntimeException("Failed to fetch heartbeat");

		if (body.data().isEmpty())
			return new Heartbeat(Instant.now(), null, null);

		HeartbeatDto last = body.data().getLast();

		return heartbeatMapper.toEntity(last);
	}
}
