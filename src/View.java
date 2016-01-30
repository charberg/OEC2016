import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public class View extends JFrame{
	
	private JPanel currentPanel;
	private JTextField currentTextField;
	
	public static final String VariableButtonString = "Variable";
	public static final String StartIfButtonString = "Start If";
	public static final String EndIfButtonString = "End If";
	public static final String StartLoopButtonString = "Start Loop";
	public static final String EndLoopButtonString = "End Loop";
	public static final String IsEqualButtonString = "Is Equal";
	public static final String StartFunctionButtonString = "Start Function";
	public static final String EndFunctionButtonString = "End Function";
	
	private ArrayList<String> variableList;
	
	public enum PanelPage
	{
		DEFAULT,
		OPTIONS,
		PAGE1
	}
	
	public View()
	{
		setSize(800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Candy - The Sweetest Programming Language For Kids!");
		
		variableList = new ArrayList<String>();
		
		currentPanel = new JPanel();
		currentTextField = new JTextField();
		currentTextField.enableInputMethods(false);
		
		setupPage1();
		this.add(currentPanel);
		
		setVisible(true);
	}
	
	private void setupDefaultPanel()
	{
		
		JPanel defaultPanel = new JPanel();
		defaultPanel.setLayout(new BorderLayout());
		
		JButton button1 = new JButton("Options");
		button1.setBounds(30,30,30,30);
		
		JLabel label1 = new JLabel("NO CONTENT");
		
		ButtonListener listener1 = new ButtonListener(this);
		button1.addActionListener(listener1);
		
		defaultPanel.add(BorderLayout.SOUTH, button1);
		defaultPanel.add(BorderLayout.NORTH, label1);
		
		switchPanel(defaultPanel);
	}
	
	private void setupOptionsPanel()
	{
		
		JPanel optionPanel = new JPanel();
		JButton button1 = new JButton("Back");
		button1.addActionListener(new ButtonListener(this));
		optionPanel.add(button1);
		
		switchPanel(optionPanel);
		
	}
	
	private void setupPage1()
	{
		JPanel page1 = new JPanel();
		page1.setLayout(new BorderLayout());
		
		currentTextField = new JTextField();
		currentTextField.enableInputMethods(false);
		
		page1.add(BorderLayout.CENTER, currentTextField);
		
		JButton varButton = new JButton(VariableButtonString);
		varButton.addActionListener(new FunctionalButtonListener(this));
		
		JButton startIfButton = new JButton(StartIfButtonString);
		startIfButton.addActionListener(new FunctionalButtonListener(this));
		
		JButton endIfButton = new JButton(EndIfButtonString);
		endIfButton.addActionListener(new FunctionalButtonListener(this));
		
		JButton startLoopButton = new JButton(StartLoopButtonString);
		startLoopButton.addActionListener(new FunctionalButtonListener(this));
		
		JButton endLoopButton = new JButton(EndLoopButtonString);
		endLoopButton.addActionListener(new FunctionalButtonListener(this));
		
		JButton isEqualButton = new JButton(IsEqualButtonString);
		isEqualButton.addActionListener(new FunctionalButtonListener(this));
		
		JButton startFunctionButton = new JButton(StartFunctionButtonString);
		startFunctionButton.addActionListener(new FunctionalButtonListener(this));
		
		JButton endFunctionButton = new JButton(EndFunctionButtonString);
		endFunctionButton.addActionListener(new FunctionalButtonListener(this));
		
		JPanel bottomPannel = new JPanel();
		
		bottomPannel.add(varButton);
		bottomPannel.add(startIfButton);
		bottomPannel.add(endIfButton);
		bottomPannel.add(startLoopButton);
		bottomPannel.add(endLoopButton);
		bottomPannel.add(isEqualButton);
		bottomPannel.add(startFunctionButton);
		bottomPannel.add(endFunctionButton);
		
		
		page1.add(BorderLayout.SOUTH, bottomPannel);
		
		switchPanel(page1);
		
	}
	
	public void insertVariable()
	{
		String input = (String)JOptionPane.showInputDialog(this, "Please enter the name of your candy:", JOptionPane.PLAIN_MESSAGE);
		currentTextField.setText(currentTextField.getText() + "candy " + input + " ");
		variableList.add(input);
	}
	
	public void insertStartIf()
	{
		String input1 = (String)JOptionPane.showInputDialog(this, "What variable do you want to compare?:", "Input", JOptionPane.PLAIN_MESSAGE, null, variableList.toArray(), variableList.get(0));
		String input2 = (String)JOptionPane.showInputDialog(this, "What variable do you want to compare?:", "Input", JOptionPane.PLAIN_MESSAGE, null, variableList.toArray(), "ham");
		currentTextField.setText(currentTextField.getText() + "IF "+input1+" IS EQUAL TO "+input2+" ");
	}
	
	public void insertEndIf()
	{
		currentTextField.setText(currentTextField.getText() + "END IF ");
	}
	
	public void insertStartLoop()
	{
		currentTextField.setText(currentTextField.getText() + "START LOOP ");
	}
	
	public void insertEndLoop()
	{
		currentTextField.setText(currentTextField.getText() + "END LOOP ");
	}
	
	public void insertStartFunction()
	{
		currentTextField.setText(currentTextField.getText() + "START FUNCTION ");
	}
	
	public void insertEndFunction()
	{
		currentTextField.setText(currentTextField.getText() + "END FUNCTION ");
	}
	
	private void switchPanel(JPanel panel)
	{
		remove(currentPanel);
		currentPanel = panel;
		add(currentPanel);
		currentPanel.setVisible(true);
		setVisible(true);
	}
	
	public void changePanel(PanelPage newPanel)
	{
		switch(newPanel)
		{
			case DEFAULT:
				setupDefaultPanel();
				break;
			case OPTIONS:
				setupOptionsPanel();
				break;
			case PAGE1:
				
			default:
				setupDefaultPanel();
		}
	}
	
	public static void main(String[] args) {
		View v = new View();
	}

}
