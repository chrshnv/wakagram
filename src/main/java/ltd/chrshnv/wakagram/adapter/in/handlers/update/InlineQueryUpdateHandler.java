package ltd.chrshnv.wakagram.adapter.in.handlers.update;

import ltd.chrshnv.wakagram.adapter.in.InlineQueryHandler;
import ltd.chrshnv.wakagram.adapter.in.UpdateHandler;
import ltd.chrshnv.wakagram.event.PushInlineQueryEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;

import java.util.List;

@Service
public class InlineQueryUpdateHandler implements UpdateHandler {
	private final List<InlineQueryHandler> inlineQueryHandlers;
	private final ApplicationEventPublisher eventPublisher;

	public InlineQueryUpdateHandler(List<InlineQueryHandler> inlineQueryHandlers, ApplicationEventPublisher eventPublisher) {
		this.inlineQueryHandlers = inlineQueryHandlers;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void handle(Update update) {
		InlineQuery inlineQuery = update.getInlineQuery();

		List<InlineQueryResult> queryResults = inlineQueryHandlers
			.stream().map(InlineQueryHandler::handleInlineQuery)
			.toList();

		AnswerInlineQuery inlineQueryAnswer = AnswerInlineQuery.builder()
			.inlineQueryId(inlineQuery.getId())
			.results(queryResults)
			.cacheTime(0)
			.build();

		eventPublisher.publishEvent(new PushInlineQueryEvent(this, inlineQueryAnswer));
	}

	@Override
	public boolean canHandle(Update update) {
		return update.hasInlineQuery();
	}
}
