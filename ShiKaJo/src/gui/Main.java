package gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import database.Base;
import datastructure.Trie;

/**
 * This class is principal class. It's responsible for call the main classes
 * 
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	João Paulo 	  (vilarjp93@gmail.com)
 * @version 04.12.2017
 */
public class Main {
	/**
	 * The main method
	 * @param 	args
	 * @throws 	InputMismatchException
	 */
	public static void main(String[] args) throws  InputMismatchException {            

		Trie trie = null;
        Base base = null;
        
		// Deserialization
        try {   
            // Reading the object from a file
            FileInputStream file1 = new FileInputStream("load/trieFile.dat");
            ObjectInputStream in1 = new ObjectInputStream(file1);
            // Reading the object from a file
            FileInputStream file2 = new FileInputStream("load/baseFile.dat");
            ObjectInputStream in2 = new ObjectInputStream(file2);
             
            // Method for deserialization of object
            trie = (Trie)in1.readObject();
            // Method for deserialization of object
            base = (Base)in2.readObject();
             
            in1.close();
            file1.close();
            in2.close();
            file2.close();
        } catch(IOException ex) {
        	JOptionPane.showMessageDialog(null, "trieFile.dat and baseFile.dat files not found, creating new ones");
            trie = new Trie();
            base = new Base();
        } catch(ClassNotFoundException ex) {
        		JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        InitalScreen screen = new InitalScreen(trie, base);
 
    }
}

