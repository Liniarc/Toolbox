package toolbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Clock extends JFrame implements ActionListener{

	private JLabel time;
	
	int nums = 1;
	ArrayList<int[]> saveValues = new ArrayList<int[]>();

	private Timer timer;
	
	public Clock()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		time = new JLabel("TIME: 00:00:00");
		time.setFont(new Font("Arial", Font.PLAIN, 24));
		time.setForeground(Color.red);
		time.setOpaque(true);
		
		panel.add(time);
		timer = new Timer(100, new ActionListener()
		{
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				Calendar cal = Calendar.getInstance();
		    	cal.getTime();
		    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				time.setText("TIME: " + sdf.format(cal.getTime()));

				int mins = cal.get(Calendar.MINUTE);
				int secs = cal.get(Calendar.SECOND);
				i++;
				if ((mins%30)>=15&&(mins%30)<18)
				{
					time.setForeground(Color.getHSBColor((float) (i/10.), 1, .7f));
					time.setBackground(Color.getHSBColor((float) ((i+5)/10.), 1, 1));
				}
				else
				{
					time.setForeground(Color.BLACK);
					time.setBackground(Color.WHITE);
				}
			}
		});
		timer.start();

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
		Clock ex = new Clock();
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

		add(pane);
		pack();
	}
	
	public void actionPerformed(ActionEvent e) {
	}
}