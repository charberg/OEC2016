import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class ChapterTester {

	private HashMap<String, String> variables;
	private HashMap<String, Integer> functions;	//Int represents number of variables
	
	private HashMap<String, String> variableTests;
	private HashMap<String, String> functionNameTests;

	
	public ChapterTester() {
		
		variables = new HashMap<String, String>();
		functions = new HashMap<String, Integer>();
		
		variableTests = new HashMap<String, String>();
		functionNameTests = new HashMap<String, String>();
		
	}
	
	public void setVariables(HashMap<String, String> variables) {
		this.variables = variables;
	}
	public void setFunctions(HashMap<String, Integer> functions) {
		this.functions = functions;
	}
	
	public void addVariableTest(String varName, String varValue) {
		variableTests.put(varName, varValue);
	}
	
	public void removeVariableTest(String varName) {
		variableTests.remove(varName);
	}
	
	public void addFunctionNameTest(String functionName, String expectedValue) {
		functionNameTests.put(functionName, expectedValue);
	}
	
	public void removeFunctionNameTest(String functionName) {
		functionNameTests.remove(functionName);
	}
	
	public boolean printTest(String output) {
		String[] outputLines = output.split("\n");
		if(Arrays.asList(outputLines).contains(output)) {
			return true;
		}
		return false;
	}
	
	public boolean printMultipleTimesTest(String output, int numberOfTimes) {
		String[] outputLines = output.split("\n");
		if(Collections.frequency(Arrays.asList(outputLines), output) < numberOfTimes) {
			return false;
		}
		return true;
	}
	
	public boolean runTests() {
		for(String testVar : variableTests.keySet()) {
			if(!variables.containsKey(testVar) || !variables.get(testVar).equals(variableTests.get(testVar))) {
				return false;
			}
		}
		
		for(String testVar : functionNameTests.keySet()) {
			if(!functions.containsKey(testVar)) {
				return false;
			}
		}
		
		
		return true;
		
	}
	
}
