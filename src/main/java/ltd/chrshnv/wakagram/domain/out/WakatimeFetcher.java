package ltd.chrshnv.wakagram.domain.out;

import ltd.chrshnv.wakagram.domain.model.Heartbeat;
import org.jspecify.annotations.NonNull;

public interface WakatimeFetcher {
	@NonNull Heartbeat fetchLastForUser(@NonNull String apiKey);
}
