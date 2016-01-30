import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class View extends JFrame{
	
	private JPanel currentPanel;
	private JTextArea currentTextArea;
	private String textPanelModifier;
	public CandyInterpreter candyint;
	
	public static final String VariableButtonString = "Variable";
	public static final String StartIfButtonString = "Start If";
	public static final String EndIfButtonString = "End If";
	public static final String StartLoopButtonString = "Start Loop";
	public static final String EndLoopButtonString = "End Loop";
	public static final String runButtonString = "Run";
	public static final String StartFunctionButtonString = "Start Function";
	public static final String EndFunctionButtonString = "End Function";
	
	private ArrayList<String> variableList;
	
	public enum PanelPage
	{
		OPTIONS,
		PAGE1
	}
	
	public View()
	{
		setSize(800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Candy - The Sweetest Programming Language For Kids!");
		
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(info.getName())) {
	            try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            break;
	        }
	    }
		UIManager.put("control", new Color(200,200,255));
		UIManager.put("nimbusBase", new Color(255,0,0));
		UIManager.put("TextArea.font", new Font("segoe print", Font.BOLD, 14));
		
		variableList = new ArrayList<String>();
		textPanelModifier = "";
		
		candyint = new CandyInterpreter();
		
		currentPanel = new JPanel();
		currentTextArea = new JTextArea();
		currentTextArea.setEditable(false);
		
		setupPage1();
		this.add(currentPanel);
		
		setVisible(true);
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
		
		currentTextArea = new JTextArea();
		currentTextArea.setEditable(false);
		
		page1.add(BorderLayout.CENTER, currentTextArea);
		
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
		
		JButton runButton = new JButton(runButtonString);
		runButton.addActionListener(new FunctionalButtonListener(this));
		
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
		bottomPannel.add(startFunctionButton);
		bottomPannel.add(endFunctionButton);
		bottomPannel.add(runButton);
		
		page1.add(BorderLayout.SOUTH, bottomPannel);
		
		switchPanel(page1);
		
	}
	
	public void insertVariable(String input1, String input2)
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "candy " + input1 + " = " + input2 + "\n\n");
		variableList.add(input1);
	}
	
	private boolean isAlphaNumeric(String input)
	{
		boolean status = false;
		
		return status;
	}
	
	public void insertStartIf(String input1, String input2)
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "IF "+input1+" IS EQUAL TO " + input2 + "\n\n");
		textPanelModifier += "\t";
	}
	
	public void insertEndIf()
	{
		textPanelModifier = textPanelModifier.substring(0,textPanelModifier.length()-1);
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "END IF \n\n");
	}
	
	public void insertStartLoop(int num)
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "LOOP " +  num + " TIMES\n\n");
		textPanelModifier += "\t";
	}
	
	public void insertEndLoop()
	{
		textPanelModifier = textPanelModifier.substring(0,textPanelModifier.length()-1);
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "END LOOP \n\n");
	}
	
	public void insertStartFunction()
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "START FUNCTION ");
		textPanelModifier += "\t";
	}
	
	public void insertEndFunction()
	{
		textPanelModifier = textPanelModifier.substring(0,textPanelModifier.length()-1);
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "END FUNCTION \n\n");
	}
	
	public void runCode()
	{
		candyint.runCode();
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
			case OPTIONS:
				setupOptionsPanel();
				break;
			case PAGE1:
				setupPage1();
				break;
			default:
				setupPage1();
		}
	}
	
	public static void main(String[] args) {
		View v = new View();
	}

}
