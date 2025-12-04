package ltd.chrshnv.wakagram.domain.model;

public class PushMessageEvent extends BaseEvent {
	private final String message;
	private final Long chatId;
	private final Boolean isLiveMessage;

	public PushMessageEvent(String message, Long chatId, Boolean isLiveMessage) {
		this.message = message;
		this.chatId = chatId;
		this.isLiveMessage = isLiveMessage;
	}

	public String getMessage() {
		return message;
	}

	public Long getChatId() {
		return chatId;
	}

	public Boolean getLiveMessage() {
		return isLiveMessage;
	}
}
