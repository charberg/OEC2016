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
	
}
