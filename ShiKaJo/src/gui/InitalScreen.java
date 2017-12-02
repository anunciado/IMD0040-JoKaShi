package gui;

import javax.swing.JFrame;
import modules.*;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JMenu;
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
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

public class InitalScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private String searchText;
	private String pathLastfileInput;
	private String nameLastfileInput;
	private String filesList;
	private JRadioButton rdbtnAnd;
	private JRadioButton rdbtnOr;
	private IndexModule indexModule;
	private SearchModule searchModule;
	
	
	public InitalScreen (IndexModule indexModule, SearchModule searchModule) {
		this.indexModule = indexModule;
		this.searchModule = searchModule;
		pathLastfileInput = "";
		setTitle("InitalScreen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 50);
		setSize(500, 400);
		
		ButtonGroup radioGroup = new ButtonGroup();
		
		rdbtnAnd = new JRadioButton("and");
		rdbtnAnd.setSelected(true);
		rdbtnOr = new JRadioButton("or");
		
		radioGroup.add(rdbtnAnd);
		radioGroup.add(rdbtnOr);
		
//		Jframe jf = new JFrame();
//		String[] options = new String[2];
//        options[0] = new String("Yes");
//        options[1] = new String("No");
//        int answer = JOptionPane.showOptionDialog(jf,"Do you wish get out of the system?", "Resume", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
//        if(answer == JOptionPane.CLOSED_OPTION) System.exit(0);
//        if(answer == JOptionPane.YES_OPTION) {
//        	
//        }     
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
// OPEN FILE BUTTON
		JButton openButton = new JButton("Open");
		
		openButton.addActionListener(new ActionListener() {
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
		JButton btnArchives= new JButton("Saved files");
		
		btnArchives.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilesList(indexModule.toString());
				SecondScreen ss = new SecondScreen("Saved Files");
				ss.setAddedFiles(getFilesList());
			}
		});
		
		btnArchives.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnArchives.setToolTipText("Files saved in the system");
			}
		});
		
		btnArchives.setIcon(new ImageIcon("./images/save.png"));
		menuBar.add(btnArchives);
		
// DELETE BUTTON
		JMenu mnDelete = new JMenu("Delete");
		mnDelete.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				mnDelete.setToolTipText("Delete some system archive");
			}
		});
		mnDelete.setIcon(new ImageIcon("./images/trash.png"));
		menuBar.add(mnDelete);
		getContentPane().setLayout(new BorderLayout(0, 0));
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
				if(searchText.equals("")) {
					JOptionPane.showMessageDialog(searchButton, "Error, nothing to search for");
				}
				else {
					searchTextField.setText("");
					SecondScreen ss = new SecondScreen("Result of search: ");
					boolean option = rdbtnAnd.isSelected();
					if(option) {
						ss.setKeyWord(searchModule.search(searchText, "and"));
					}
					else {
						ss.setKeyWord(searchModule.search(searchText, "or"));
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

}