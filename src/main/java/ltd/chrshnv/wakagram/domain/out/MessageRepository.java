package ltd.chrshnv.wakagram.domain.out;

import ltd.chrshnv.wakagram.domain.model.Message;

import java.util.Optional;

public interface MessageRepository {
	void save(Message message, Boolean isNew);

	Optional<Message> findById(String id);
}
