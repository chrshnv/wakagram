package ltd.chrshnv.wakagram.adapter.in.handlers.update;

import ltd.chrshnv.wakagram.adapter.in.UpdateHandler;
import ltd.chrshnv.wakagram.domain.in.ChosenInlineQuery;
import ltd.chrshnv.wakagram.valueobject.BotInlineQuery;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Service
public class ChosenQueryUpdateHandler implements UpdateHandler {
	private final Map<BotInlineQuery, ChosenInlineQuery> chosenInlineQueries;

	public ChosenQueryUpdateHandler(Map<BotInlineQuery, ChosenInlineQuery> chosenInlineQueries) {
		this.chosenInlineQueries = chosenInlineQueries;
	}

	@Override
	public void handle(Update update) {
		String inlineMessageId = update.getChosenInlineQuery().getInlineMessageId();
		BotInlineQuery inlineQuery = BotInlineQuery.valueOf(update.getChosenInlineQuery().getResultId());
		Long userId = update.getChosenInlineQuery().getFrom().getId();

		ChosenInlineQuery handler = chosenInlineQueries.get(inlineQuery);
		if (handler != null)
			handler.handle(inlineMessageId, userId);
	}

	@Override
	public boolean canHandle(Update update) {
		return update.hasChosenInlineQuery();
	}
}
