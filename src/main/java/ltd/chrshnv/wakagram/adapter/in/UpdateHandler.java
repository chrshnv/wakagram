package ltd.chrshnv.wakagram.adapter.in;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {
	void handle(Update update);

	boolean canHandle(Update update);
}
