package ltd.chrshnv.wakagram.adapter.in.handlers.inlinequery;

import ltd.chrshnv.wakagram.adapter.in.InlineQueryHandler;
import ltd.chrshnv.wakagram.valueobject.BotCallback;
import ltd.chrshnv.wakagram.valueobject.BotInlineQuery;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Service
public class LiveMessageInlineQueryHandler implements InlineQueryHandler {
	@Override
	public InlineQueryResult handleInlineQuery() {
		InlineKeyboardButton refreshButton = InlineKeyboardButton
			.builder()
			.text("Refresh")
			.callbackData(BotCallback.REFRESH_LIVE_HEARTBEAT_MESSAGE.name())
			.build();

		InlineKeyboardMarkup markup = InlineKeyboardMarkup
			.builder()
			.keyboardRow(new InlineKeyboardRow(refreshButton))
			.build();

		return InlineQueryResultArticle
			.builder()
			.id(BotInlineQuery.LIVE_MESSAGE.name())
			.title("Live heartbeat message")
			.description("Send a live heartbeat message")
			.inputMessageContent(new InputTextMessageContent("Send a live heartbeat message"))
			.replyMarkup(markup)
			.build();
	}
}
