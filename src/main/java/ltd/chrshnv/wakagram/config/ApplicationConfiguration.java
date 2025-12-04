package ltd.chrshnv.wakagram.config;

import ltd.chrshnv.wakagram.domain.in.ChosenInlineQuery;
import ltd.chrshnv.wakagram.domain.in.Command;
import ltd.chrshnv.wakagram.valueobject.BotCommand;
import ltd.chrshnv.wakagram.valueobject.BotInlineQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class ApplicationConfiguration {
	@Bean
	public Map<BotCommand, Command> commandMap(List<Command> beans) {
	    return beans
	        .stream()
	        .collect(Collectors.toMap(Command::getCommand, Function.identity()));
	}

	@Bean
	public Map<BotInlineQuery, ChosenInlineQuery> chosenInlineQueriesMap(List<ChosenInlineQuery> beans) {
	    return beans
	        .stream()
	        .collect(Collectors.toMap(ChosenInlineQuery::getInlineQuery, Function.identity()));
	}

	@Bean
	RestClient restClient() {
		return RestClient.builder().baseUrl("https://api.wakatime.com/api/v1").build();
	}
}
