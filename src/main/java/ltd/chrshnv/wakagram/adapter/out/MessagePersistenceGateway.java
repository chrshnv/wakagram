package ltd.chrshnv.wakagram.adapter.out;

import ltd.chrshnv.wakagram.adapter.mapper.MessageMapper;
import ltd.chrshnv.wakagram.domain.model.Message;
import ltd.chrshnv.wakagram.domain.out.MessageRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MessagePersistenceGateway implements MessageRepository {

	private final MessageMapper messageMapper;

	private final MessageEntityRepository messageEntityRepository;

	public MessagePersistenceGateway(MessageMapper messageMapper,
									 MessageEntityRepository messageEntityRepository) {
		this.messageMapper = messageMapper;
		this.messageEntityRepository = messageEntityRepository;
	}

	@Override
	public void save(Message message, Boolean isNew) {
		MessageEntity entity = messageMapper.toMessageEntity(message, isNew);

		messageEntityRepository.save(entity);
	}

	@Override
	public Optional<Message> findById(String id) {
		return messageEntityRepository.findById(id).map(messageMapper::toDomain);
	}
}
