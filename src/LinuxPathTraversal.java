import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//the class containing the main function. 
public class LinuxPathTraversal {

	public static void main(String[] args) {
		LinuxPathTraversalFactory factoryObject = new LinuxPathTraversalFactory();
		CompleteDirectory fullDirectory = factoryObject
				.createCompleteDirectoryObject();
		Controller executor = factoryObject
				.createControllerObject(fullDirectory);
		processStandardInput(executor);

	}

	private static void processStandardInput(Controller executor) {
		System.out
				.println("Please give the inputs one by one and wait for the output. If you want to quit, press q");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;

		try {
			while (!(input = br.readLine()).equals("q")) {

				InputHelper.Command command = InputHelper
						.validateCommand(input);
				if (!command.isValidate())
					System.out.println("ERR: CANNOT RECOGNIZE INPUT");
				else {
					String result = executor.runCommand(command.getCommand(),
							command.getParameter());
					System.out.println(result);
				}
			}
		} catch (IOException e) {
			System.out
					.println("Something went wrong while reading the standard console");
			e.printStackTrace();
		}

	}
}
