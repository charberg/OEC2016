import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class ChapterTester {

	private HashMap<String, String> variables;
	private ArrayList<String> functions;	//Int represents number of variables
	
	private HashMap<String, String> variableTests;
	private ArrayList<String> functionNameTests;

	
	public ChapterTester() {
		
		variables = new HashMap<String, String>();
		functions = new ArrayList<String>();
		
		variableTests = new HashMap<String, String>();
		functionNameTests = new ArrayList<String>();
		
	}
	
	public void setVariables(HashMap<String, String> variables) {
		this.variables = variables;
	}
	public void setFunctions(ArrayList<String> functions) {
		this.functions = functions;
	}
	
	public void addVariableTest(String varName, String varValue) {
		variableTests.put(varName, varValue);
	}
	
	public void removeVariableTest(String varName) {
		variableTests.remove(varName);
	}
	
	public void addFunctionNameTest(String functionName) {
		functionNameTests.add(functionName);
	}
	
	public void removeFunctionNameTest(String functionName) {
		functionNameTests.remove(functionName);
	}
	
	public boolean printTest(String programOutput, String expectedOutput) {
		String[] outputLines = programOutput.split("\n");
		if(Arrays.asList(outputLines).contains(expectedOutput)) {
			return true;
		}
		return false;
	}
	
	public boolean printMultipleTimesTest(String programOutput, String expectedOutput, int numberOfTimes) {
		String[] outputLines = programOutput.split("\n");
		int freq = Collections.frequency(Arrays.asList(outputLines), expectedOutput);
		System.out.println(freq);
		if(freq < numberOfTimes) {
			return false;
		}
		return true;
	}
	
	public boolean runTests() {
		for(String testVar : variableTests.keySet()) {
			if(!variables.containsKey(testVar) || !(variables.get(testVar).equals(variableTests.get(testVar)))) {
				return false;
			}
		}
		
		for(String testVar : functionNameTests) {
			if(!functions.contains(testVar)) {
				return false;
			}
		}
		
		
		return true;
		
	}
	
}
