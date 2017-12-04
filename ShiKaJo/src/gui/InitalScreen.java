package gui;

import javax.swing.JFrame;
import modules.*;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.Base;
import datastructure.Trie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JRadioButton;

/**
 * This class represents the initial screen. It's responsible to do the search and 
 * other things that can be see in the execution 
 *  
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	Jo�o Paulo 	  (vilarjp93@gmail.com)
 * @version 04.12.2017
 */
public class InitalScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private String searchText;
	private String pathLastfileInput;
	private String nameLastfileInput;
	private String filesList;
	private String wordsList;
	private JRadioButton rdbtnAnd;
	private JRadioButton rdbtnOr;
	private IndexModule indexModule;
	private SearchModule searchModule;
	private SecondScreen ss;
	
	/**
	 * The constructor of the InitalScreen class
	 * @param 	trie	The trie
	 * @param 	base	The base
	 */
	public InitalScreen (Trie trie, Base base) {
		
		this.indexModule = new IndexModule(trie, base);
		this.searchModule = new SearchModule(trie, base);
		ss = new SecondScreen();
		ss.setVisible(false);
		pathLastfileInput = "";
		setTitle("JoKaShi Search Engine");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// When the main window is closed, it does the serialization of the database and the trie
		addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	        	try
	            {   
	            		//Saving of object in a file
	                FileOutputStream file1 = new FileOutputStream("load/trieFile.dat");
	                ObjectOutputStream out1 = new ObjectOutputStream(file1);
	                //Saving of object in a file
	                FileOutputStream file2 = new FileOutputStream("load/baseFile.dat");
	                ObjectOutputStream out2 = new ObjectOutputStream(file2);
	                // Method for serialization of object
	                out1.writeObject(trie);
	                out2.writeObject(base);
	                out1.close();
	                file1.close();
	                out2.close();
	                file2.close();
	                JOptionPane.showMessageDialog(null, "Base and trie has been serialized");
	                //System.out.println("Base and trie has been serialized");
	            }     
	            catch(IOException ex)
	            {
	            		JOptionPane.showMessageDialog(null, ex.getMessage());
	                //System.out.println("IOException is caught");
	            }
	        }
	    });
		
		setLocation(100, 50);
		setSize(550, 400);
		
		ButtonGroup radioGroup = new ButtonGroup();
		
		rdbtnAnd = new JRadioButton("and");
		rdbtnAnd.setSelected(true); //Sets the default search option to "AND"      
		rdbtnOr = new JRadioButton("or");
		
		radioGroup.add(rdbtnAnd);
		radioGroup.add(rdbtnOr);   
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// OPEN FILE BUTTON
		JButton openButton = new JButton("Open");
		
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt"); //Filter to read only .txt files
			    fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(null);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					fileChooser.setName(file.getPath());
					pathLastfileInput = file.getPath();
					nameLastfileInput = file.getName();
					JOptionPane.showMessageDialog(openButton, indexModule.index(getPathLastfileInput()));
				}	
			}
		});
		
		openButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				openButton.setToolTipText("Open a new document");
			}
		});
		
		openButton.setIcon(new ImageIcon("./images/open.png"));
		menuBar.add(openButton);
		
// UPDATE FILE BUTTON
		JButton btnUpdate = new JButton("Update");
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
			    fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(null);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					fileChooser.setName(file.getPath());
					pathLastfileInput = file.getPath();
					nameLastfileInput = file.getName();
					JOptionPane.showMessageDialog(openButton, indexModule.update(getPathLastfileInput()));
				}	
			}
		});
		
		btnUpdate.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnUpdate.setToolTipText("Update some archive");
			}
		});
		
		btnUpdate.setIcon(new ImageIcon("./images/update.png"));
		menuBar.add(btnUpdate);
		
// SAVED FILES BUTTON
		JButton btnArchives = new JButton("Saved files");
		
		btnArchives.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilesList(indexModule.toString());
				if(getFilesList().equals("")) {
					JOptionPane.showMessageDialog(btnArchives, "0 files in our database, add any first");
				}
				else {
					//SecondScreen ss = new SecondScreen("Saved Files");
					ss.setAddedFiles(getFilesList());
					ss.setTitle("Saved Files");
					ss.setVisible(true);
				}
			}
		});
		
		btnArchives.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnArchives.setToolTipText("Files saved in the system");
			}
		});
		
		btnArchives.setIcon(new ImageIcon("./images/save.png"));
		menuBar.add(btnArchives);
		
// SAVED WORDS BUTTON
		JButton btnWords = new JButton("Saved words");
		
		btnWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setWordsList(indexModule.getWords());
				if(getWordsList().equals("")) {
					JOptionPane.showMessageDialog(btnWords, "0 words in our database, add any first");
				}
				else {
					//SecondScreen ss = new SecondScreen("Saved Words");
					ss.setAddedWords(getWordsList());
					ss.setTitle("Saved Words");
					ss.setVisible(true);
				}
			}
		});
		
		btnWords.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnArchives.setToolTipText("Words saved in the system");
			}
		});
		
		btnWords.setIcon(new ImageIcon("./images/save.png"));
		menuBar.add(btnWords);

// DELETE BUTTON
		JButton btnDelete = new JButton("Delete file");
			
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = JOptionPane.showInputDialog(btnDelete, "Name of the file you want to remove (ex: file.txt)");
				if(file != null) {
					JOptionPane.showMessageDialog(btnDelete, indexModule.remove(file));
				}
			}
		});
		
		btnDelete.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnDelete.setToolTipText("Delete some system archive");
			}
		});
		
		btnDelete.setIcon(new ImageIcon("./images/trash.png"));
		menuBar.add(btnDelete);
// PANEL
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		Panel panel_2 = new Panel();
		panel_2.setForeground(SystemColor.inactiveCaptionText);
		getContentPane().add(panel_2, BorderLayout.CENTER);
// SEARCH BAR
		JTextField searchTextField = new JTextField("");
		searchTextField.setToolTipText(null);
		searchTextField.setColumns(10);
// SEARCH BUTTON
		JButton searchButton = new JButton("");
		
		searchButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				searchButton.setToolTipText("Search");
			}
		});
		
		searchButton.setIcon(null);
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchText = searchTextField.getText();
				long startTime, endTime;
				NumberFormat formatter = new DecimalFormat("#0.00000");
				if(searchText.equals("")) {
					JOptionPane.showMessageDialog(searchButton, "Error, nothing to search for");
				}
				else {
					searchTextField.setText("");
					//SecondScreen ss = new SecondScreen("Result of search: ");
					boolean option = rdbtnAnd.isSelected();
					if(option) {
						startTime = System.currentTimeMillis();
						String occurrence = searchModule.search(searchText, "and");
						endTime = System.currentTimeMillis();
						String time = formatter.format((endTime - startTime) / 1000d);
						ss.setKeyWord("AND",occurrence, time);
						ss.setTitle("Result of Search");
						ss.setVisible(true);
					}
					else {
						startTime = System.currentTimeMillis();
						String occurrence = searchModule.search(searchText, "or");
						endTime = System.currentTimeMillis();
						String time = formatter.format((endTime - startTime) / 1000d);
						ss.setKeyWord("OR",occurrence, time);
						ss.setTitle("Result of Search");
						ss.setVisible(true);
					}
				}
			}
		});
		
		searchButton.setIcon(new ImageIcon("./images/search.png"));
		searchButton.setHorizontalAlignment(SwingConstants.LEFT);
		searchButton.setVerticalAlignment(SwingConstants.BOTTOM);
// JOKASHI LOGO	
		JLabel logoLabel = new JLabel(" ");
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setIcon(new ImageIcon("./images/JoKaShi.png"));
// LAYOUT		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(163)
					.addComponent(logoLabel, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
					.addGap(299))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(114)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(rdbtnAnd, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(261))
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(rdbtnOr, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGap(261))
							.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(searchTextField, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(searchButton)
								.addGap(233)))))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(138, Short.MAX_VALUE)
					.addComponent(logoLabel)
					.addGap(68)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(searchButton)
						.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnAnd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnOr)
					.addGap(17))
		);
		panel_2.setLayout(gl_panel_2);
		
		setVisible(true);
	}
	
	/**
	 * The path of the last file selected
	 * @return pathLastFileInput
	 */
	public String getPathLastfileInput() {
		return pathLastfileInput;
	}
	
	/**
	 * The name of the last file selected
	 * @return pathLastFileInput
	 */
	public String getNameLastfileInput() {
		return nameLastfileInput;
	}
	
	/**
	 * Shows a list with all files inserted
	 * @return pathLastFileInput
	 */
	public String getFilesList() {
		return filesList;
	}
	
	/**
	 * Updates the list with all files inserted
	 * @param filesList updated file list
	 */
	public void setFilesList (String filesList ) {
		this.filesList = filesList;
	}
	
	/**
	 * Shows a list with all words inserted
	 * @return wordsList
	 */
	public String getWordsList() {
		return wordsList;
	}
	
	/**
	 * Updates the list with all words inserted
	 * @param wordsList updated words list
	 */
	public void setWordsList (String wordsList ) {
		this.wordsList = wordsList;
	}

}