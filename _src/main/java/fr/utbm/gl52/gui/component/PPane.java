package fr.utbm.gl52.gui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PPane extends JPanel{
	
	private static final long serialVersionUID = 8157489844588357649L;
	private JButton btn;
	private boolean isExtended = false;
	private final int extendedHeight;
	
	public PPane(String title, int height){
		extendedHeight = height;
		setLayout(new BorderLayout());
		
		JLabel label = new JLabel(title);
		
		btn = new JButton("►");
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isExtended){
					btn.setText("►");
					isExtended = false;
					//setMaximumSize(new Dimension(getWidth(), 20));
					setPreferredSize(new Dimension(getWidth(), 15));
					repaint();
				} else {
					//setMaximumSize(new Dimension(getWidth(), extendedHeight));
					setPreferredSize(new Dimension(getWidth(), extendedHeight));
					btn.setText("▼");
					isExtended = true;
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(this.getWidth(), 26));
		panel.setMinimumSize(new Dimension(this.getWidth(), 26));
		panel.add(btn, BorderLayout.WEST);
		panel.add(label, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
		setPreferredSize(new Dimension(this.getWidth(), 15));
		add(panel, BorderLayout.NORTH);
	}
	
	public void desactivateButton(){
		btn.setVisible(false);
		setPreferredSize(new Dimension(this.getWidth(), 400));
	}
}
