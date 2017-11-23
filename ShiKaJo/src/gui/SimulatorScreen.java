package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimulatorScreen extends JFrame {
	private int width;
	private int heigth;
	private JTextField searchText;
	
	public SimulatorScreen () {
		
		setTitle("SearchSim");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 50);
		setSize(500, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivesSavedMenu = new JMenu("Archives saved");
		mnArchivesSavedMenu.setIcon(new ImageIcon("./images/save.png"));
		mnArchivesSavedMenu.setBackground(UIManager.getColor("Button.darkShadow"));
		menuBar.add(mnArchivesSavedMenu);
		
		JButton openButton = new JButton("Open");
		openButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				openButton.setToolTipText("Open a new document");
			}
		});
		openButton.setIcon(new ImageIcon("./images/open.png"));
		menuBar.add(openButton);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		Panel panel_2 = new Panel();
		panel_2.setForeground(SystemColor.inactiveCaptionText);
		getContentPane().add(panel_2, BorderLayout.CENTER);
		
		searchText = new JTextField();
		searchText.setToolTipText("");
		searchText.setColumns(10);
		
		JButton searchButton = new JButton("");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchButton.setToolTipText("Search");
			}
		});
		searchButton.setIcon(null);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		searchButton.setIcon(new ImageIcon("./images/search.png"));
		searchButton.setHorizontalAlignment(SwingConstants.LEFT);
		searchButton.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel logoLabel = new JLabel(" ");
		logoLabel.setIcon(new ImageIcon("./images/JoKaShi.png"));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(114)
					.addComponent(searchText, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchButton)
					.addGap(233))
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addGap(163)
					.addComponent(logoLabel, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
					.addGap(299))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(logoLabel)
					.addGap(68)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(searchButton)
						.addComponent(searchText, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(70))
		);
		panel_2.setLayout(gl_panel_2);
		
		setVisible(true);
	}
	
}