package ltd.chrshnv.wakagram.adapter.in;

import ltd.chrshnv.wakagram.domain.model.UpdateInlineMessageEvent;
import ltd.chrshnv.wakagram.event.PushInlineQueryEvent;
import ltd.chrshnv.wakagram.domain.model.PushMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

@Service
public class TelegramHandler implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

	private static final Logger log = LoggerFactory.getLogger(TelegramHandler.class);

	private final List<UpdateHandler> updateHandlers;

	@Value("${telegram.token}")
	private String telegramToken;

	private final TelegramClient telegramClient = new OkHttpTelegramClient(getBotToken());

	public TelegramHandler(List<UpdateHandler> updateHandlers) {
		this.updateHandlers = updateHandlers;
	}

	@Override
	public void consume(Update update) {
		updateHandlers
			.stream()
			.filter(it -> it.canHandle(update))
			.forEach(it -> it.handle(update));
	}

	@Override
	public String getBotToken() {
		return telegramToken;
	}

	@Override
	public LongPollingUpdateConsumer getUpdatesConsumer() {
		return this;
	}

	@EventListener
	public void handlePushMessageEvent(PushMessageEvent event) {
		String message = event.getMessage();
		Long chatId = event.getChatId();

		SendMessage messageToSend = SendMessage.builder()
			.chatId(chatId)
			.text(message)
			.build();

		try {
			telegramClient.execute(messageToSend);
		} catch (TelegramApiException e) {
			log.error("failed to send message", e);
		}
	}

	@EventListener
	public void handlePushInlineQueryEvent(PushInlineQueryEvent event) {
		try {
			telegramClient.execute(event.getInlineQuery());
		} catch (TelegramApiException e) {
			log.error("failed to send inline query", e);
		}
	}

	@EventListener
	public void handleUpdateInlineMessageEvent(UpdateInlineMessageEvent event) {
		EditMessageText messageToSend = EditMessageText
			.builder()
			.inlineMessageId(event.getInlineMessageId())
			.text(event.getMessage())
			.build();

		try {
			telegramClient.execute(messageToSend);
		} catch (TelegramApiException e) {
			log.error("failed to edit inline message", e);
		}
	}
}
