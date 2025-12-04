package ltd.chrshnv.wakagram.application;

import ltd.chrshnv.wakagram.domain.in.ChosenInlineQuery;
import ltd.chrshnv.wakagram.domain.in.CreateLiveMessageUseCase;
import ltd.chrshnv.wakagram.valueobject.BotInlineQuery;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class LiveMessageChosenInlineQuery implements ChosenInlineQuery {

	private final CreateLiveMessageUseCase createLiveMessageUseCase;

	public LiveMessageChosenInlineQuery(CreateLiveMessageUseCase createLiveMessageUseCase) {
		this.createLiveMessageUseCase = createLiveMessageUseCase;
	}

	@Override
	public void handle(@Nullable String inlineMessageId, @NonNull Long userId) {
		if (inlineMessageId == null)
			return;

		createLiveMessageUseCase.execute(inlineMessageId, userId);
	}

	@Override
	public BotInlineQuery getInlineQuery() {
		return BotInlineQuery.LIVE_MESSAGE;
	}
}
