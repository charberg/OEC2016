import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		//TODO Change this to be done on model not view
		if(src.getText().equals(View.VariableButtonString))
		{
			view.insertVariable();
		}
		else if(src.getText().equals(View.StartIfButtonString))
		{
			view.insertStartIf();
		}
		else if(src.getText().equals(View.EndIfButtonString))
		{
			view.insertEndIf();
		}
		else if(src.getText().equals(View.StartLoopButtonString))
		{
			view.insertStartLoop();
		}
		else if(src.getText().equals(View.EndLoopButtonString))
		{
			view.insertEndLoop();
		}
		else if(src.getText().equals(View.StartFunctionButtonString))
		{
			view.insertStartFunction();
		}
		else if(src.getText().equals(View.EndFunctionButtonString))
		{
			view.insertEndFunction();
		}
		else
		{
			System.out.println("Button error");
		}
	}

}