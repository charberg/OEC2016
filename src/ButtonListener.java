import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonListener implements ActionListener{
	
	private View view;
	
	public ButtonListener(View v)
	{
		view = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton src = (JButton) arg0.getSource();
		if(src.getText() == "Options")
		{
			view.changePanel(View.PanelPage.OPTIONS);
		}
		else if(src.getText() == "Back")
		{
			System.out.println("BACK TO DEFAULT");
			view.changePanel(View.PanelPage.PAGE1);
		}
		else
		{
			System.out.println("Button error");
		}
	}

}
