import java.util.Set;

//This is the controller of the project contains all the computation and calculations
public class Controller {

	CompleteDirectory completeDirectory;

	public Controller(CompleteDirectory completeDirectory) {
		this.completeDirectory = completeDirectory;
	}

	public String runCommand(String commandName, String parameter) {

		String result;
		if (commandName.equals(CommandEnum.PWD.getName())) {

			String path = completeDirectory.getCurrentDirectoryPath();
			result = "PATH: " + path;
		} else if (commandName.equals(CommandEnum.LS.getName())) {
			Set<Directory> children = completeDirectory.getCurrentNode()
					.getChildren();
			String listOfChildren = "";
			if (children == null) {
				listOfChildren = "";
			} else {
				for (Directory child : children) {
					listOfChildren = listOfChildren + child.getName() + "  ";
				}
			}
			result = "Dirs: " + listOfChildren;
		} else if (commandName.equals(CommandEnum.CD.getName())) {
			boolean canTraverse;
			if (parameter.startsWith("/")) {
				canTraverse = completeDirectory.traverse(parameter);
			} else {
				canTraverse = completeDirectory.traverseRelative(parameter);
			}
			result = ResultBuilder.getResultString(commandName, canTraverse);

		} else if (commandName.equals(CommandEnum.MKDIR.getName())) {

			boolean childAdded;

			if (parameter.startsWith("/")) {
				childAdded = completeDirectory.addChild(parameter);
			} else {
				childAdded = completeDirectory
						.addChildRelativeDirectory(parameter);
			}
			result = ResultBuilder.getResultString(commandName, childAdded);

		} else if (commandName.equals(CommandEnum.RM.getName())) {

			boolean removed;
			if (parameter.startsWith("/")) {
				removed = completeDirectory.deleteDirectory(parameter);
			} else {
				removed = completeDirectory.deleteDirectoryRelative(parameter);
			}
			result = ResultBuilder.getResultString(commandName, removed);
		} else if (commandName.contains(CommandEnum.CLEAR.getName())) {
			completeDirectory.clear();
			result = ResultBuilder.getResultString(commandName, true);
		} else {
			result = ResultBuilder.getResultString(null, true);
		}
		return result;
	}

	// getters and setters

	public CompleteDirectory getcompleteDirectory() {
		return completeDirectory;
	}

	public void setcompleteDirectory(CompleteDirectory completeDirectory) {
		this.completeDirectory = completeDirectory;
	}
}
