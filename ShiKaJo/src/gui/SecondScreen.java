package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class SecondScreen extends JFrame {
	
		JPanel panel = new JPanel();
		JTextArea textArea = new JTextArea();
	
	public SecondScreen (String labelTitle) {
		setTitle("SecondScreen");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocation(700, 10);
		setSize(500, 375);
		
		JLabel lblNewLabel = new JLabel(labelTitle);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
			
		
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(189)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
					.addGap(174))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(333, Short.MAX_VALUE))
		);
		
		
		
		
		getContentPane().setLayout(groupLayout);
		
		
		setVisible(true);
	}
	
	/**
	 * add the list of added files
	 * @param addFiles
	 */
	public void setAddedFiles (String addFiles) {
		textArea = new JTextArea(addFiles);
		textArea.setBackground(UIManager.getColor("CheckBox.background"));
		textArea.setEditable(false);
		panel.add(textArea);
		panel.repaint();
	}
	
	/**
	 * add all the infos about the keyword inserted in the search bar
	 * @param keyWord
	 */
	public void setKeyWord (String keyWord) {
		textArea = new JTextArea(keyWord);
		textArea.setBackground(UIManager.getColor("CheckBox.background"));
		textArea.setEditable(false);
		panel.add(textArea);
		panel.repaint();
		
	}
}