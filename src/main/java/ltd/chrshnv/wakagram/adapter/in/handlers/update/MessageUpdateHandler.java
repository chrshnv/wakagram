package ltd.chrshnv.wakagram.adapter.in.handlers.update;

import ltd.chrshnv.wakagram.adapter.in.UpdateHandler;
import ltd.chrshnv.wakagram.domain.in.Command;
import ltd.chrshnv.wakagram.valueobject.BotCommand;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Service
public class MessageUpdateHandler implements UpdateHandler {

	private final Map<BotCommand, Command> commandMap;

	public MessageUpdateHandler(Map<BotCommand, Command> commandMap) {
		this.commandMap = commandMap;
	}

	@Override
	public void handle(Update update) {
		String text = update.getMessage().getText();
		Long userId = update.getMessage().getFrom().getId();

		BotCommand command = getCommand(text);

		if (command == null)
			return;

		commandMap.get(command).handle(text, userId);
	}

	@Override
	public boolean canHandle(Update update) {
		return update.hasMessage() && update.getMessage().hasText();
	}

	private BotCommand getCommand(String text) {
		for (BotCommand value : BotCommand.values()) {
			if (text.startsWith("/" + value.getValue()))
				return value;
		}

		return null;
	}
}
