package ltd.chrshnv.wakagram.application;

import ltd.chrshnv.wakagram.domain.in.Command;
import ltd.chrshnv.wakagram.domain.model.PushMessageEvent;
import ltd.chrshnv.wakagram.domain.model.User;
import ltd.chrshnv.wakagram.domain.out.EventPublisher;
import ltd.chrshnv.wakagram.domain.out.UserRepository;
import ltd.chrshnv.wakagram.valueobject.BotCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class APIKeyCommand implements Command {
	private static final Logger log = LoggerFactory.getLogger(APIKeyCommand.class);

	private final EventPublisher eventPublisher;
	private final UserRepository userRepository;

	public APIKeyCommand(EventPublisher eventPublisher, UserRepository userRepository) {
		this.eventPublisher = eventPublisher;
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void handle(String message, Long userId) {
		log.info("api key command received - {} from {}", message, userId);

		String token = message.replace("/api_key", "").trim();
		if (token.isBlank()) {
			PushMessageEvent event = new PushMessageEvent("Please provide a valid API key", userId, false);
			eventPublisher.publish(event);

			return;
		}

		UserResult userResult = userRepository.findById(userId)
			.map(it -> new UserResult(it, false))
			.orElse(new UserResult(new User(userId, null), true));

		User user = userResult.data();

		user = user.updateToken(token);

		userRepository.save(user, userResult.isNew());

		PushMessageEvent event = new PushMessageEvent("API key updated successfully", userId, false);
		eventPublisher.publish(event);
	}

	@Override
	public BotCommand getCommand() {
		return BotCommand.API_KEY;
	}
}


record UserResult(User data, boolean isNew) {
}
