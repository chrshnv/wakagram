package ltd.chrshnv.wakagram.event;

import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;

public class PushInlineQueryEvent extends ApplicationEvent {
	private final AnswerInlineQuery inlineQuery;

	public PushInlineQueryEvent(Object source, AnswerInlineQuery inlineQuery) {
		super(source);
		this.inlineQuery = inlineQuery;
	}

	public AnswerInlineQuery getInlineQuery() {
		return inlineQuery;
	}
}
