package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

/**
 * The SecondScreen is the class that show the results of the search made 
 * in the FirstScreen 
 * 
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	João Paulo 	  (vilarjp93@gmail.com)
 * @version 04.12.2017
 */
public class SecondScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JScrollPane scrollPane = new JScrollPane();
	JTextArea txtrTeste = new JTextArea();

	/**
	 * The constructor of the second screen
	 */
	public SecondScreen () {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocation(700, 10);
		setSize(500, 375);
		
		JLabel lblNewLabel = new JLabel("Files information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(189)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(174))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		
		txtrTeste.setEditable(false);
		txtrTeste.setBackground(UIManager.getColor("Button.background"));
		scrollPane.setViewportView(txtrTeste);
		getContentPane().setLayout(groupLayout);

		setVisible(true);
	}
	
	/**
	 * add the list of added files
	 * @param addFiles
	 */
	public void setAddedFiles (String addFiles) {
		txtrTeste.setText(addFiles);
		scrollPane.setViewportView(txtrTeste);
	}
	
	/**
	 * Change the words added
	 * @param 	wordsList 	The words list
	 */
	public void setAddedWords(String wordsList) {
		txtrTeste.setText(wordsList);
		scrollPane.setViewportView(txtrTeste);	
	}
	
	/**
	 * add all the infos about the keyword inserted in the search bar
	 * @param keyWord
	 */
	public void setKeyWord (String type,String keyWord, String time) {
		txtrTeste.setText("Search mode: " + type + ", execution time is: " + time + " seconds\n" + keyWord);
		scrollPane.setViewportView(txtrTeste);
	}	
}