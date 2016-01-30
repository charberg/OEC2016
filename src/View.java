import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class View extends JFrame{
	
	private JPanel currentPanel;
	private JTextArea currentTextArea;
	private String textPanelModifier;
	
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
		
		variableList = new ArrayList<String>();
		textPanelModifier = "";
		
		currentPanel = new JPanel();
		currentTextArea = new JTextArea();
		currentTextArea.enableInputMethods(false);
		
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
		
		currentTextArea = new JTextArea();
		currentTextArea.enableInputMethods(false);
		
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
		boolean stopLoop = false;
		String input = null;
		
		while(!stopLoop)
		{
			input = (String)JOptionPane.showInputDialog(this, "Please enter the name of your candy:", "New Variable", JOptionPane.PLAIN_MESSAGE);
			if(!Character.isDigit(input.charAt(0)) && !variableList.contains(input))
			{
				stopLoop = true;
			}
		}
		
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "candy " + input + " \n\n");
		variableList.add(input);
	}
	
	private boolean isAlphaNumeric(String input)
	{
		boolean status = false;
		
		return status;
	}
	
	public void insertStartIf()
	{

		String input1 = (String)JOptionPane.showInputDialog(this, "What variable do you want to compare?:", "Input", JOptionPane.PLAIN_MESSAGE, null, variableList.toArray(), "");
		
		String input2 = (String)JOptionPane.showInputDialog(this, "What variable do you want to compare?:", "Input", JOptionPane.PLAIN_MESSAGE, null, variableList.toArray(), "");
		
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "IF "+input1+" IS EQUAL TO " + input2 + "\n\n");
		textPanelModifier += "\t";
	}
	
	public void insertEndIf()
	{
		textPanelModifier = textPanelModifier.substring(0,textPanelModifier.length()-1);
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "END IF \n\n");
	}
	
	public void insertStartLoop()
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "START LOOP ");
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
