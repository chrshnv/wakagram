package ltd.chrshnv.wakagram.application;

import ltd.chrshnv.wakagram.domain.in.CreateLiveMessageUseCase;
import ltd.chrshnv.wakagram.domain.in.UpdateLiveMessageUseCase;
import ltd.chrshnv.wakagram.domain.model.Heartbeat;
import ltd.chrshnv.wakagram.domain.model.Message;
import ltd.chrshnv.wakagram.domain.model.UpdateInlineMessageEvent;
import ltd.chrshnv.wakagram.domain.model.User;
import ltd.chrshnv.wakagram.domain.out.EventPublisher;
import ltd.chrshnv.wakagram.domain.out.MessageRepository;
import ltd.chrshnv.wakagram.domain.out.TaskScheduler;
import ltd.chrshnv.wakagram.domain.out.UserRepository;
import ltd.chrshnv.wakagram.domain.out.WakatimeFetcher;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;

@Service
public class MessageService implements CreateLiveMessageUseCase, UpdateLiveMessageUseCase {
	private final MessageRepository messageRepository;

	private final TaskScheduler taskScheduler;

	private final UserRepository userRepository;

	private final WakatimeFetcher wakatimeFetcher;

	private final EventPublisher eventPublisher;

	public MessageService(MessageRepository messageRepository, TaskScheduler taskScheduler,
						  UserRepository userRepository,
						  WakatimeFetcher wakatimeFetcher,
						  EventPublisher eventPublisher) {
		this.messageRepository = messageRepository;
		this.taskScheduler = taskScheduler;
		this.userRepository = userRepository;
		this.wakatimeFetcher = wakatimeFetcher;
		this.eventPublisher = eventPublisher;
	}

	@Override
	@Transactional
	public void execute(String id, Long userId) {
		Message message = new Message(id, userId);

		messageRepository.save(message, true);

		taskScheduler.schedule(() -> this.execute(id), Duration.ofMinutes(1));
	}

	@Override
	public void execute(String id) {
		Message message = messageRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Message not found"));

		User user = userRepository.findById(message.userId()).orElseThrow(() -> new RuntimeException("User not found"));

		String apiKey = user.apiKey();
		if (apiKey == null)
			throw new RuntimeException("User has no api key");

		Heartbeat heartbeat = wakatimeFetcher.fetchLastForUser(apiKey);

		long seconds = Instant.now().getEpochSecond() - heartbeat.time().getEpochSecond();
		String timeText = getTimeText(seconds);

		String text = """
		Currently working on %s (using %s). Last heartbeat was %s.
		""".formatted(heartbeat.project(), heartbeat.language(), timeText);

		UpdateInlineMessageEvent updateEvent = new UpdateInlineMessageEvent(text, id);
		eventPublisher.publish(updateEvent);
	}

	@NotNull
	private String getTimeText(long seconds) {
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;

		String timeText;

		if (seconds < 60) timeText = seconds + " second" + (seconds == 1 ? "" : "s") + " ago";
		else if (minutes < 60) timeText = minutes + " minute" + (minutes == 1 ? "" : "s") + " ago";
		else if (hours < 24) timeText = hours + " hour" + (hours == 1 ? "" : "s") + " ago";
		else timeText = days + " day" + (days == 1 ? "" : "s") + " ago";
		return timeText;
	}
}
