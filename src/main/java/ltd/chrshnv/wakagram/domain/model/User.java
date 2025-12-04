package ltd.chrshnv.wakagram.domain.model;

import org.jspecify.annotations.NonNull;

public record User(
	Long id,
	String apiKey
) {
	public User updateToken(@NonNull String apiKey) {
		return new User(id, apiKey);
	}
}
