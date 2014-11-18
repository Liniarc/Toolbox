package toolbox;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Counter extends JFrame implements ActionListener{

	private JTextField number;
	
	int nums = 1;
	ArrayList<int[]> saveValues = new ArrayList<int[]>();
	int[] counters;
	JTextField[] counterlabels;
	
	public Counter()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		number = new JTextField();
		panel.add(number);

		JButton create = new JButton("Create");
		create.setName("Create");
		create.setMargin(new java.awt.Insets(1, 5, 1, 5));
		create.addActionListener(this);
		panel.add(create);
		
		add(panel);
//
//		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		pack();
	}

	public static void main(String[] args)
	{
		Counter ex = new Counter();
		ex.setVisible(true);
	}
	
	public void createGui(int num)
	{
		getContentPane().removeAll();
		JButton button;
		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		button = new JButton("Save");
		button.setName("Save");
		button.setMargin(new Insets(0,6,0,6));
		button.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		pane.add(button, c);
		
		c.gridx = 1;
		c.weightx = 0;
		pane.add(Box.createRigidArea(new Dimension(2,0)),c);
		for (int i = 1; i <= num; i++)
		{
			button = new JButton("+");
			button.setName("+"+i);
			button.setMargin(new Insets(0,5,0,5));
			button.addActionListener(this);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 3*i-1;
			pane.add(button, c);

			button = new JButton("-");
			button.setName("-"+i);
			button.setMargin(new Insets(0,6,0,6)); 
			button.addActionListener(this);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 3*i;
			pane.add(button, c);
			
			if (i != num)
			{
				c.gridx = 3 * i + 1;
				pane.add(Box.createRigidArea(new Dimension(2, 0)), c);
			}
		}
		
		c.gridy = 0;
		JTextField textField;
		for (int i = 1; i <= num; i++)
		{
			textField = counterlabels[i-1];
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 3*i-1;
			c.gridwidth = 2;
			
			pane.add(textField, c);
		}
		
		c.gridy = 2;
		JLabel label;
		for (int i = 1; i <= num; i++)
		{
			label = new JLabel(""+counters[i-1],SwingConstants.CENTER);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 3*i-1;
			c.gridwidth = 2;
			pane.add(label, c);
		}

		if (saveValues.size() > 0)
		{
			c.gridy = 3;
			c.gridx = 0;
			c.gridwidth = 2 + num * 3;
			JSeparator separator = new JSeparator();
			pane.add(separator, c);
		}
		
		for (int i = 0; i < saveValues.size(); i++)
		{
			c.gridy = 4+i;
			c.gridx = 0;
			c.gridwidth = 1;
			label = new JLabel("#" + (i+1) + ": ",SwingConstants.RIGHT);
			pane.add(label, c);
			for (int j = 0; j < num; j++)
			{
				label = new JLabel(""+saveValues.get(i)[j],SwingConstants.CENTER);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 3*j+2;
				c.gridwidth = 2;
				pane.add(label, c);
			}
		}

		add(pane);
		pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (((JButton)e.getSource()).getName().equals("Create"))
		{
			if (number.getText().matches("\\d+"))
			{
				nums = Math.min(20, Integer.parseInt(number.getText()));
				counters = new int[nums];
				counterlabels = new JTextField[nums];
				for (int i = 0; i < nums; i++)
					counters[i] = 0;
				for (int i = 0; i < nums; i++)
					counterlabels[i] = new JTextField(""+(i+1));
				createGui(nums);
				setLocationRelativeTo(null);
			}
			else
				number.setText("Input integer");
		}
		
		if (((JButton)e.getSource()).getName().equals("Save"))
		{
			int[] saved = new int[nums];
			for (int i = 0; i < nums; i++)
			{
				saved[i] = counters[i];
				counters[i] = 0;
				
			}
			saveValues.add(saved);
			createGui(nums);
		}
		
		for (int i = 0; i < nums; i++)
		{
			if (((JButton)e.getSource()).getName().equals("+"+(i+1)))
			{
				counters[i]++;
				createGui( nums);
			}
			if (((JButton)e.getSource()).getName().equals("-"+(i+1)))
			{
				counters[i]--;
				createGui( nums);
			}
		}
	}
}