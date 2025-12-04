package ltd.chrshnv.wakagram.domain.in;

import ltd.chrshnv.wakagram.valueobject.BotInlineQuery;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public interface ChosenInlineQuery {
	void handle(@Nullable String inlineMessageId, @NonNull Long userId);

	BotInlineQuery getInlineQuery();
}
