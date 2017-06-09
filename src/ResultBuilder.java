//append the result. 
public class ResultBuilder {

	public static String getResultString(String commandName, boolean result) {

		CommandEnum command = CommandEnum.getEnumByValue(commandName);
		StringBuilder sb = new StringBuilder();

		switch (command) {
		case CD:
			sb = result ? sb.append("SUCC : REACHED") : sb
					.append("ERR : INVALID PATH");
			break;
		case MKDIR:
			sb = result ? sb.append("SUCC : CREATED") : sb
					.append("ERR : DIRECTORY ALREADY EXISTS");
			break;
		case RM:
			sb = result ? sb.append("SUCC : DELETED") : sb
					.append("ERR : DIRECTORY DOES NOT EXISTS");
			break;
		case CLEAR:
			sb.append("SUCC: CLEARED: RESET TO ROOT");
			break;
		default:
			sb.append("ERR: CANNOT RECOGNIZE INPUT");
			break;

		}

		return String.valueOf(sb);
	}
}
