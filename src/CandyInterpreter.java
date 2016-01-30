import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class CandyInterpreter {
	public static String OUTPUTFILE = "output.py";
	
	private int tabIndex = 0;


	public void writeToFile(String input) {
		File file = new File(OUTPUTFILE);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(constructTabs() + input + "\n");
			if (writer != null) writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String constructTabs() {
		return String.join("", Collections.nCopies(tabIndex, "/t"));
	}
	
	public void writeNewVariable(String varName, String value) {
		writeToFile(varName + " = " + value);
	}
	
	public void printVariable(String varName) {
		writeToFile("print(" + varName + ")");
	}
	
	public void writeEquals(String varName1, String varName2) {
		writeToFile("if " + varName1 + " == " + varName2 + ":");
		tabIndex++;
	}

	public String runCode(){
		ProcessBuilder pythonCode = new ProcessBuilder("python", OUTPUTFILE);
		Process python = null;
		String input = "";
		try {
			python = pythonCode.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (python != null){
			BufferedReader in = new BufferedReader(new InputStreamReader(python.getInputStream()));
			try {
				input = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return input;
	}
	
	public void endIf() {
		tabIndex--;
	}
	
	public void writeWhile(int loops) {
		writeToFile("for _ in range(" + loops + ")");
		tabIndex++;
	}
	
	public void endWhile() {
		tabIndex--;
	}
	
	public void writeFunction(String functionName, ArrayList<String> variables) {
		
		writeToFile("def " + functionName + "(" + variables.toString().substring(1, variables.size() - 1) + ") :");
		tabIndex++;
		
	}
	
	public void endFunction() {
		tabIndex--;
	}
	
	public void writeComment(String comment) {
		writeToFile("#" + comment);
	}
	
	
	
}
