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
	private ChapterTester tester;
	public CandyInterpreter candyint;
	public PanelPage currentPanelEnum;
	
	
	public static final String VariableButtonString = "Candy";
	public static final String StartIfButtonString = "Check Flavour";
	public static final String EndIfButtonString = "End Check Flavour";
	public static final String StartLoopButtonString = "Start Loopdeloop";
	public static final String EndLoopButtonString = "End Loopdeloop";
	public static final String runButtonString = "Tell Story";
	public static final String StartFunctionButtonString = "Start Adventure";
	public static final String EndFunctionButtonString = "End Adventure";
	public static final String PrintButtonString = "Show Flavour";
	public static final String CallFunctionButtonString = "Go On Adventure";
	public static final String RestartButtonString = "New Story";
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
		PAGE8,
		PAGE9,
		PAGE10,
		PAGE11,
		PAGE12,
		PAGE13,
		PAGE14,
		PAGE15,
		PAGE16,
		PAGE17
	}
	
	public View()
	{
		setSize(1920,800);
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
		
		tester = new ChapterTester();
		
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
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "candy " + input1 + " is " + input2 + "\n\n");
	}
	
	private boolean isAlphaNumeric(String input)
	{
		boolean status = false;
		
		return status;
	}
	
	public void insertStartIf(String input1, String input2)
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "If "+input1+" is the same flavour as " + input2 + "\n\n");
		textPanelModifier += "\t";
	}
	
	public void insertEndIf()
	{
		textPanelModifier = textPanelModifier.substring(0,textPanelModifier.length()-1);
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "End if \n\n");
	}
	
	public void insertStartLoop(int num)
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "Loopdeloop " +  num + " times\n\n");
		textPanelModifier += "\t";
	}
	
	public void insertEndLoop()
	{
		textPanelModifier = textPanelModifier.substring(0,textPanelModifier.length()-1);
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "End loopdeloop \n\n");
	}
	
	public void insertStartFunction(String name)
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "Adventure " + name + "\n\n");
		textPanelModifier += "\t";
	}
	
	public void insertEndFunction()
	{
		textPanelModifier = textPanelModifier.substring(0,textPanelModifier.length()-1);
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "End adventure \n\n");
	}
	
	public void insertPrint(String input)
	{
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "Show flavour " + input + "\n\n");
	}
	
	public void insertCallFunction(String input1)
	{
		
		currentTextArea.setText(currentTextArea.getText() + textPanelModifier + "Go on " + input1 + " adventure\n\n");
	}
	
	public void restartPanel()
	{
		candyint = new CandyInterpreter();
		changePanel(currentPanelEnum);
	}
	
	public void runCode()
	{
		currentResultsArea.setText("Results: \n\n");
		
		switch(currentPanelEnum)
		{
			case PAGE5:
				tester.addVariableTest("bonbon", "chocolate");
				break;
			case PAGE9:
				tester.addVariableTest("bonbon", "chocolate");
				tester.addVariableTest("truffle", "chocolate");
				break;
			case PAGE12:
				break;
			case PAGE15:
				tester.addFunctionNameTest("bedtime");
				break;
			
		}
		
		String returnString = candyint.runCode();
		tester.setVariables(candyint.getVariables());
		tester.setFunctions(candyint.getFunctions());
		
		//Test case for printing variables in chapter 1, page 4
		if(currentPanelEnum.equals(PanelPage.PAGE4)) {
			if(!tester.printTest(returnString, "chocolate")) {
				currentResultsArea.setText(currentResultsArea.getText() + "\n<<UH OH! Don't forget to print the 'chocolate' flavour!>>\n");
			}
			else
			{
				currentResultsArea.setText(currentResultsArea.getText() + "\n<<YAY! You did it!>>\n");
			}
		}
		
		if(currentPanelEnum.equals(PanelPage.PAGE12)) {
			if(!tester.printMultipleTimesTest(returnString, "fudge", 5)) {
				currentResultsArea.setText(currentResultsArea.getText() + "\n<<UH OH! Don't forget to print the 'fudge' flavour 5 times in a loop-dee-loop!>>\n");
			}
			else
			{
				currentResultsArea.setText(currentResultsArea.getText() + "\n<<YAY! You did it!>>\n");
			}
		}
		
		if(!currentPanelEnum.equals(PanelPage.PAGE12))
		{
			if(!tester.runTests()) {
				//De-grayscale button
				currentResultsArea.setText(currentResultsArea.getText() + "\n<<UH OH! Check the previous pages for what to do next!>>\n");
			}
			else
			{
				currentResultsArea.setText(currentResultsArea.getText() + "\n<<YAY! You did it!>>\n");
			}
		}
		
		tester.clear();
		
		currentResultsArea.setText(currentResultsArea.getText() + returnString);
	}
	
	private void switchPanel(JPanel panel)
	{
		textPanelModifier = "";
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
				setupStoryPage("pictures/Creating Story.jpg");
				currentPanelEnum = PanelPage.PAGE2;
				break;
			case PAGE3:
				setupStoryPage("pictures/Chapter 1 Start.jpg");
				currentPanelEnum = PanelPage.PAGE3;
				break;
			case PAGE4:
				setupStoryPage("pictures/Chapter 1 Next.jpg");
				currentPanelEnum = PanelPage.PAGE4;
				break;
			case PAGE5:
				setupLevelPanel();
				currentPanelEnum = PanelPage.PAGE5;
				break;
			case PAGE6:
				setupStoryPage("pictures/Chapter 2 Start.jpg");
				currentPanelEnum = PanelPage.PAGE6;
				break;
			case PAGE7:
				setupStoryPage("pictures/Chapter 2 Next.jpg");
				currentPanelEnum = PanelPage.PAGE7;
				break;
			case PAGE8:
				setupStoryPage("pictures/Chapter 2 End.jpg");
				currentPanelEnum = PanelPage.PAGE8;
				break;
			case PAGE9:
				setupLevelPanel();
				currentPanelEnum = PanelPage.PAGE9;
				break;
			case PAGE10:
				setupStoryPage("pictures/Chapter 3 Start.jpg");
				currentPanelEnum = PanelPage.PAGE10;
				break;
			case PAGE11:
				setupStoryPage("pictures/Chapter 3 Stop.jpg");
				currentPanelEnum = PanelPage.PAGE11;
				break;
			case PAGE12:
				setupLevelPanel();
				currentPanelEnum = PanelPage.PAGE12;
				break;
			case PAGE13:
				setupStoryPage("pictures/Chapter 4 Start.jpg");
				currentPanelEnum = PanelPage.PAGE13;
				break;
			case PAGE14:
				setupStoryPage("pictures/Chapter 4 Next.jpg");
				currentPanelEnum = PanelPage.PAGE14;
				break;
			case PAGE15:
				setupLevelPanel();
				currentPanelEnum = PanelPage.PAGE15;
				break;
			case PAGE16:
				setupStoryPage("pictures/End.jpg");
				currentPanelEnum = PanelPage.PAGE16;
				break;
			case PAGE17:
				setupLevelPanel();
				currentPanelEnum = PanelPage.PAGE17;
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
