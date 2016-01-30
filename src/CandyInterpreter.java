import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CandyInterpreter {
	public static String OUTPUTFILE = "output.py";
	
	private int tabIndex = 0;
	private HashMap<String, String> variables;
	private HashMap<String, Integer> functions;	//Int represents number of variables

	public CandyInterpreter() {
		
		variables = new HashMap<String, String>();
		functions = new HashMap<String, Integer>();
		
		//Wipe the existing output file
		File file = new File(OUTPUTFILE);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeToFile(String input) {
		File file = new File(OUTPUTFILE);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(constructTabs() + input + "\n");
			if (writer != null) writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String constructTabs() {
		return String.join("", Collections.nCopies(tabIndex, "\t"));
	}
	
	public boolean writeNewVariable(String varName, String value) {
		if(variables.containsKey(varName)) {
			return false;
		}
		else {
			variables.put(varName, value);
			writeToFile(varName + " = \"" + value + "\"");
			
			return true;
		
		}
	}
	
	public void printVariable(String varName) {
		writeToFile("print(" + varName + ")");
	}
	
	public void writeEquals(String varName1, String varName2) {
		writeToFile("if " + varName1 + " == " + varName2 + ":");
		tabIndex++;
	}

	public String runCode(){
		this.writeToFile("print \"success\"");
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
				String temp = "";
				while(temp != null) {
					temp = in.readLine();
					if(temp != null) {
						if (temp.equals("success")){
							return input;
						}
						input = input + temp + "\n";
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void endIf() {
		tabIndex--;
	}
	
	public void writeWhile(int loops) {
		writeToFile("for _ in range(" + loops + ") :");
		tabIndex++;
	}
	
	public void endWhile() {
		tabIndex--;
	}
	
	public boolean writeFunction(String functionName, ArrayList<String> variables) {
		
		if(functionName.contains(functionName)) {	//Do not allow over-writing functions
			return false;
		}
		
		functions.put(functionName, variables.size());
		
		String vars = "";
		if(variables != null) {
			vars = variables.toString().substring(1, variables.size() - 1);
		}
		
		writeToFile("def " + functionName + "(" + vars + ") :");
		tabIndex++;
		
		return false;
		
	}
	
	public void endFunction() {
		tabIndex--;
	}
	
	public void callFunction(String functionName, ArrayList<String> variables) {
		
		String vars = "";
		if(variables != null) {
			vars = variables.toString().substring(1, variables.size() - 1);
		}
		
		writeToFile(functionName + "(" + vars + ")");
	}
	
	public void writeComment(String comment) {
		writeToFile("#" + comment);
	}
	
	public HashMap<String, String> getVariables() {
		return variables;
	}
	
	public HashMap<String, Integer> getFunctions() {
		return functions;
	}
}
