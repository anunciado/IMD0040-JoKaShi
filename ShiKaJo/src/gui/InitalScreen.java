package gui;

import javax.swing.JFrame;

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

import java.io.File;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

public class InitalScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private String searchText;
	private String pathLastfileInput;
	private JRadioButton rdbtnAnd;
	
	public InitalScreen () {
		pathLastfileInput = "";
		setTitle("InitalScreen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 50);
		setSize(500, 400);
		
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
		
		JButton openButton = new JButton("Open");
		
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int i = fileChooser.showOpenDialog(null);
				
				if(i != 1) {
					File file = fileChooser.getSelectedFile();
					fileChooser.setName(file.getPath());
					pathLastfileInput = file.getPath();
					System.out.println(pathLastfileInput);
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
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnUpdate.setToolTipText("Update some archive");
			}
		});
		btnUpdate.setIcon(new ImageIcon("./images/update.png"));
		menuBar.add(btnUpdate);
		
		JMenu mnArchivesSavedMenu = new JMenu("Archives saved");
		mnArchivesSavedMenu.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				mnArchivesSavedMenu.setToolTipText("Archives saved in the system");
			}
		});
		mnArchivesSavedMenu.setIcon(new ImageIcon("./images/save.png"));
		mnArchivesSavedMenu.setBackground(UIManager.getColor("Button.darkShadow"));
		menuBar.add(mnArchivesSavedMenu);
		
		JMenu mnDelete = new JMenu("Delete");
		mnDelete.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				mnDelete.setToolTipText("Delete some system archive");
			}
		});
		mnDelete.setIcon(new ImageIcon("./images/trash.png"));
		menuBar.add(mnDelete);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		Panel panel_2 = new Panel();
		panel_2.setForeground(SystemColor.inactiveCaptionText);
		getContentPane().add(panel_2, BorderLayout.CENTER);
		
		JTextField searchTextField = new JTextField();
		searchTextField.setToolTipText("");
		searchTextField.setColumns(10);
		
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
				searchTextField.setText("");
				System.out.println(searchText);
				SecondScreen ss = new SecondScreen();
				//TODO call the other screen (The result screen of the search)
			}
		});
		searchButton.setIcon(new ImageIcon("./images/search.png"));
		searchButton.setHorizontalAlignment(SwingConstants.LEFT);
		searchButton.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel logoLabel = new JLabel(" ");
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setIcon(new ImageIcon("./images/JoKaShi.png"));
		
		ButtonGroup radioGroup = new ButtonGroup();
		
		rdbtnAnd = new JRadioButton("and");
		JRadioButton rdbtnOr = new JRadioButton("or");
		
		radioGroup.add(rdbtnAnd);
		radioGroup.add(rdbtnOr);
		
		rdbtnAnd.setSelected(true);		
		
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
	 * Return if the "and" radio button is selected
	 * @return true/false
	 */
	public boolean isAnd () {
		return rdbtnAnd.isSelected();
	}

}