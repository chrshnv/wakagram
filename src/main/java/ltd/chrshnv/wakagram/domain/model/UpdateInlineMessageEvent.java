package ltd.chrshnv.wakagram.domain.model;

public class UpdateInlineMessageEvent extends BaseEvent {
	private final String message;
	private final String inlineMessageId;

	public UpdateInlineMessageEvent(String message, String inlineMessageId) {
		this.message = message;
		this.inlineMessageId = inlineMessageId;
	}

	public String getMessage() {
		return message;
	}

	public String getInlineMessageId() {
		return inlineMessageId;
	}
}
