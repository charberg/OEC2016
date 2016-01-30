import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class FunctionalButtonListener implements ActionListener{
	
	private View view;
	
	public FunctionalButtonListener(View v)
	{
		view = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton src = (JButton) arg0.getSource();
		
		HashMap<String,String> variableList = view.candyint.getVariables();
		ArrayList<String> functionList = view.candyint.getFunctions();
		
		if(src.getText().equals(View.VariableButtonString))
		{
			boolean stopLoop = false;
			String input1 = null;
			
			while(!stopLoop)
			{
				input1 = (String)JOptionPane.showInputDialog(view, "Please enter the name of your candy:", "New Variable", JOptionPane.PLAIN_MESSAGE);
				if(input1 == null) return;
				
				if(!Character.isDigit(input1.charAt(0)) && !variableList.containsKey(input1))
				{
					stopLoop = true;
				}
			}
			
			String input2 = (String)JOptionPane.showInputDialog(view, "What will be inside the variable?:", "Variable Value", JOptionPane.PLAIN_MESSAGE);
			if(input2 == null) return;
			
			view.candyint.writeNewVariable(input1, input2);
			view.insertVariable(input1, input2);
		}
		else if(src.getText().equals(View.StartIfButtonString))
		{
			String input1 = (String)JOptionPane.showInputDialog(view, "What variable do you want to compare?:", "Input", JOptionPane.PLAIN_MESSAGE, null, variableList.keySet().toArray(), "");
			if(input1 == null) return;
			
			String input2 = (String)JOptionPane.showInputDialog(view, "What variable do you want to compare?:", "Input", JOptionPane.PLAIN_MESSAGE, null, variableList.keySet().toArray(), "");
			if(input2 == null) return;
			
			view.candyint.writeEquals(input1, input2);
			view.insertStartIf(input1, input2);
		}
		else if(src.getText().equals(View.EndIfButtonString))
		{
			view.candyint.endIf();
			view.insertEndIf();
		}
		else if(src.getText().equals(View.StartLoopButtonString))
		{
			Integer input1 = Integer.parseInt((String)JOptionPane.showInputDialog(view, "How many times do you want to loop?:", "Variable Value", JOptionPane.PLAIN_MESSAGE));
			if(input1 == null) return;
			
			view.candyint.writeWhile(input1);
			view.insertStartLoop(input1);
		}
		else if(src.getText().equals(View.EndLoopButtonString))
		{
			view.candyint.endWhile();
			view.insertEndLoop();
		}
		else if(src.getText().equals(View.StartFunctionButtonString))
		{
			String input1 = (String)JOptionPane.showInputDialog(view, "What do you want to name your adventure?:", "New Adventure", JOptionPane.PLAIN_MESSAGE);
			if(input1 == null) return;
			
			view.candyint.writeFunction(input1);
			view.insertStartFunction(input1);
		}
		else if(src.getText().equals(View.runButtonString))
		{
			view.candyint.runCode();
			view.runCode();
		}
		else if(src.getText().equals(View.EndFunctionButtonString))
		{
			view.candyint.endFunction();
			view.insertEndFunction();
		}
		else if(src.getText().equals(View.PrintButtonString))
		{
			String input1 = (String)JOptionPane.showInputDialog(view, "What variable do you want to compare?:", "Input", JOptionPane.PLAIN_MESSAGE, null, variableList.keySet().toArray(), "");
			if(input1 == null) return;
			
			view.candyint.printVariable(input1);
			view.insertPrint(input1);
		}
		else if(src.getText().equals(View.CallFunctionButtonString))
		{
			String input1 = (String)JOptionPane.showInputDialog(view, "What function do you want to call?:", "Input", JOptionPane.PLAIN_MESSAGE, null, functionList.toArray(), "");
			if(input1 == null) return;
			
			view.candyint.callFunction(input1);
			view.insertCallFunction(input1);
		}
		else if(src.getText().equals(View.RestartButtonString))
		{
			view.restartPanel();
		}
		else
		{
			System.out.println("Button error");
		}
	}

}