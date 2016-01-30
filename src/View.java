import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class View extends JFrame{
	
	private JPanel currentPanel;
	private JTextArea currentTextArea;
	private JTextArea currentResultsArea;
	private String textPanelModifier;
	public CandyInterpreter candyint;
	public PanelPage currentPanelEnum;
	
	public static final String VariableButtonString = "Variable";
	public static final String StartIfButtonString = "Start If";
	public static final String EndIfButtonString = "End If";
	public static final String StartLoopButtonString = "Start Loop";
	public static final String EndLoopButtonString = "End Loop";
	public static final String runButtonString = "Run";
	public static final String StartFunctionButtonString = "Start Function";
	public static final String EndFunctionButtonString = "End Function";
	public static final String PrintButtonString = "Print";
	public static final String CallFunctionButtonString = "Call Function";
	public static final String RestartButtonString = "Restart";
	public static final String BackButtonString = "Back";
	public static final String NextButtonString = "Next";
	
	public enum PanelPage
	{
		OPTIONS,
		PAGE1,
		PAGE2,
		PAGE3,
		PAGE4,
		PAGE5,
		PAGE6,
		PAGE7,
		PAGE8
	}
	
	public View()
	{
		setSize(1024,800);
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
		
		textPanelModifier = "";
		
		candyint = new CandyInterpreter();
		
		currentPanelEnum = PanelPage.PAGE1;
		
		currentPanel = new JPanel();
		currentTextArea = new JTextArea();
		currentTextArea.setEditable(false);
		
		currentResultsArea = new JTextArea();
		currentResultsArea.setEditable(false);
		
		changePanel(PanelPage.PAGE1);
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
	
	private void setupLevelPanel()
	{
		UIManager.put("control", new Color(200,200,255));
		
		JPanel page1 = new JPanel();
		page1.setLayout(new BorderLayout());
		
		currentTextArea = new JTextArea();
		currentTextArea.setEditable(false);
		
		currentResultsArea = new JTextArea();
		currentResultsArea.setText("Results:\n\n");
		currentResultsArea.setEditable(false);
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.PAGE_AXIS));
		textPanel.add(currentTextArea);
		textPanel.add(currentResultsArea);
		
		page1.add(BorderLayout.CENTER, textPanel);
		
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
		
		JButton printButton = new JButton(PrintButtonString);
		printButton.addActionListener(new FunctionalButtonListener(this));
		
		JButton callFunctionButton = new JButton(CallFunctionButtonString);
		callFunctionButton.addActionListener(new FunctionalButtonListener(this));
		
		JButton restartButton = new JButton(RestartButtonString);
		restartButton.addActionListener(new FunctionalButtonListener(this));
		
		JPanel bottomPannel = new JPanel();
		
		bottomPannel.add(varButton);
		bottomPannel.add(startIfButton);
		bottomPannel.add(endIfButton);
		bottomPannel.add(startLoopButton);
		bottomPannel.add(endLoopButton);
		bottomPannel.add(startFunctionButton);
		bottomPannel.add(endFunctionButton);
		bottomPannel.add(callFunctionButton);
		bottomPannel.add(printButton);
		bottomPannel.add(runButton);
		bottomPannel.add(restartButton);
		
		page1.add(BorderLayout.SOUTH, bottomPannel);
		
		switchPanel(page1);
		
	}
	
	public void insertVariable(String input1, String input2)
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "candy " + input1 + " = " + input2 + "\n\n");
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
	
	public void insertStartFunction(String name)
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "FUNCTION " + name + "\n\n");
		textPanelModifier += "\t";
	}
	
	public void insertEndFunction()
	{
		textPanelModifier = textPanelModifier.substring(0,textPanelModifier.length()-1);
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "END FUNCTION \n\n");
	}
	
	public void insertPrint(String input)
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "Print " + input + "\n\n");
	}
	
	public void insertCallFunction(String input1)
	{
		
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "CALL " + input1 + " \n\n");
	}
	
	public void restartPanel()
	{
		candyint = new CandyInterpreter();
		changePanel(currentPanelEnum);
	}
	
	public void runCode()
	{
		String returnString = candyint.runCode();
		currentResultsArea.setText("Results: \n\n" + returnString);
	}
	
	private void switchPanel(JPanel panel)
	{
		remove(currentPanel);
		currentPanel = panel;
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.CENTER, panel);
		
		JPanel buttonPanel = new JPanel();
		JButton backButton = new JButton(BackButtonString);
		backButton.addActionListener(new FunctionalButtonListener(this));
		JButton nextButton = new JButton(NextButtonString);
		nextButton.addActionListener(new FunctionalButtonListener(this));
		
		buttonPanel.add(backButton);
		buttonPanel.add(nextButton);
		
		mainPanel.add(BorderLayout.SOUTH, buttonPanel);
		
		currentPanel = mainPanel;
		
		add(currentPanel);
		
		currentPanel.setVisible(true);
		setVisible(true);
	}
	
	private void setupStoryPage(String imgPath)
	{
		
		UIManager.put("control", new Color(255,255,255));
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		JPanel imgPanel = new JPanel();
		
		BufferedImage image = null;
		
		try {
			 image = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel piclab = new JLabel(new ImageIcon(image));
		imgPanel.add(piclab);
		panel.add(BorderLayout.CENTER, imgPanel);
		
		switchPanel(panel);
		
		
	}
	
	public void changePanel(PanelPage newPanel)
	{
		switch(newPanel)
		{
			case OPTIONS:
				setupOptionsPanel();
				currentPanelEnum = PanelPage.OPTIONS;
				break;
			case PAGE1:
				setupStoryPage("pictures/Intro.jpg");
				currentPanelEnum = PanelPage.PAGE1;
				break;
			case PAGE2:
				setupStoryPage("pictures/Chapter 1 Start.jpg");
				currentPanelEnum = PanelPage.PAGE2;
				break;
			case PAGE3:
				setupStoryPage("pictures/Chapter 1 Next.jpg");
				currentPanelEnum = PanelPage.PAGE3;
				break;
			case PAGE4:
				setupLevelPanel();
				currentPanelEnum = PanelPage.PAGE4;
				break;
			case PAGE5:
				setupStoryPage("pictures/Chapter 2 Start.jpg");
				currentPanelEnum = PanelPage.PAGE5;
				break;
			case PAGE6:
				setupStoryPage("pictures/Chapter 2 Next.jpg");
				currentPanelEnum = PanelPage.PAGE6;
				break;
			case PAGE7:
				setupLevelPanel();
				currentPanelEnum = PanelPage.PAGE7;
				break;
			case PAGE8:
				setupStoryPage("pictures/Chapter 3 Start.jpg");
				currentPanelEnum = PanelPage.PAGE8;
				break;
			default:
				setupLevelPanel();
				currentPanelEnum = PanelPage.PAGE1;
		}
	}
	
	public static void main(String[] args) {
		View v = new View();
	}

}
