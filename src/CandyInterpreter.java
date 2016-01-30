import java.io.*;

public class CandyInterpreter {
	public static String OUTPUTFILE = "output.py";


	public void writeToFile(String input) {
		File file = new File(OUTPUTFILE);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(input + "\n");
			if (writer != null) writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeNewVariable(String varName) {
		writeToFile(varName + " = ");
	}

	public void runCode(){
		ProcessBuilder pythonCode = new ProcessBuilder("python", OUTPUTFILE);
		Process python = null;
		try {
			python = pythonCode.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (python != null){
			BufferedReader in = new BufferedReader(new InputStreamReader(python.getInputStream()));
			String input = "";
			try {
				input = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Value returned is: " + input);
		}
	}
}
