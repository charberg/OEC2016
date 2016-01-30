import java.util.HashMap;


public class chapterTester {

	private HashMap<String, String> variables;
	private HashMap<String, Integer> functions;	//Int represents number of variables
	
	private HashMap<String, String> variableTests;
	private HashMap<String, String> functionNameTests;
	private HashMap<String, Integer> functionArgTests;

	
	public chapterTester() {
		
		variables = new HashMap<String, String>();
		functions = new HashMap<String, Integer>();
		
		variableTests = new HashMap<String, String>();
		functionNameTests = new HashMap<String, String>();
		functionArgTests = new HashMap<String, Integer>();
		
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
	
	public void addFunctionArgsTest(String functionName, int expectedNumberOfArgs) {
		functionArgTests.put(functionName, new Integer(expectedNumberOfArgs));
	}
	
	public void removeFunctionArgsTest(String functionName) {
		functionArgTests.remove(functionName);
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
		
		for(String testVar : variableTests.keySet()) {
			if(!variables.containsKey(testVar) || !(functions.get(testVar) == (functionArgTests.get(testVar)))) {
				return false;
			}
		}
		
		return true;
		
	}
	
}
