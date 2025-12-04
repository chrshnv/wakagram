package ltd.chrshnv.wakagram.adapter.in;

import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;

public interface InlineQueryHandler {
	InlineQueryResult handleInlineQuery();
}
