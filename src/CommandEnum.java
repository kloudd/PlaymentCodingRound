import java.util.HashMap;
import java.util.Map;
//This Class is used to store commands.
//Enum for commands

public enum CommandEnum {

	CD("cd"), MKDIR("mkdir"), PWD("pwd"), RM("rm"), LS("ls"), CLEAR(
			"session clear");

	private String com;

	CommandEnum(String com) {
		this.com = com;
	}

	private static final Map<String, CommandEnum> commandMap = new HashMap<String, CommandEnum>();

	static {
		for (CommandEnum command : values()) {
			commandMap.put(command.getName(), command);
		}
	}

	// getters&setters
	public static CommandEnum getEnumByValue(String value) {
		return commandMap.get(value);
	}

	public String getName() {
		return com;
	}
}