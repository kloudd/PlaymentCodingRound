public class LinuxPathTraversalFactory {
	public CompleteDirectory createCompleteDirectoryObject() {
		return new CompleteDirectory();
	}

	public Controller createControllerObject(CompleteDirectory fullDirectory) {
		return new Controller(fullDirectory);
	}
}