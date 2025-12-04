package ltd.chrshnv.wakagram.domain.in;

import ltd.chrshnv.wakagram.valueobject.BotCommand;

public interface Command {
	void handle(String message, Long userId);

	BotCommand getCommand();
}
