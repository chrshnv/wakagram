package ltd.chrshnv.wakagram.valueobject;

public enum BotCommand {
	API_KEY("api_key");

	private final String value;

	BotCommand(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
