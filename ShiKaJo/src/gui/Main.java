package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


import database.Base;
import datastructure.Trie;
import modules.IndexModule;
import modules.SearchModule;

/* Class Trie Test */

public class Main{

    private static Scanner scan;

	public static void main(String[] args) throws  InputMismatchException {            

		scan = new Scanner(System.in);
		Trie trie = null;
        Base base = null;
        
		// Deserialization
        try
        {   
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
        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            trie = new Trie();
            base = new Base();
        }
         
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        
        IndexModule indexModule = new IndexModule(trie, base);
        SearchModule searchModule = new SearchModule(trie, base);
        InitalScreen screen = new InitalScreen(indexModule, searchModule);
    
        System.out.println(new File(".").getAbsolutePath());
        System.out.println("Trie Test\n");          
        char ch;
        
        do {
        	System.out.println("\nTrie Operations\n");
            System.out.println("1. insert");
            System.out.println("2. delete");
            System.out.println("3. update");
            System.out.println("4. show (files)");
            System.out.println("5. show (words)");
            System.out.println("6. search");

            int choice = Integer.parseInt(scan.nextLine());            
            switch (choice) {
            	case 1: 
            		System.out.println("Add words of which file:");
            		indexModule.index(scan.nextLine());
            		break;                          
            	case 2: 
            		System.out.println("Delete words of which file:");
            		indexModule.remove(scan.nextLine()); 
            		break; 
            	case 3: 
            		System.out.println("Update words of which file:");
            		indexModule.update(screen.getPathLastfileInput());
            		break; 
            	case 4: 
            		System.out.println("Added files:");
            		screen.setFilesList(indexModule.toString());
            		String s = screen.getFilesList();
            		System.out.println(s);
            		
            		break; 
            	case 5:
            		System.out.println("Added words:");
            		trie.show();
            		break;
            	case 6: 
            		System.out.println("Enter string(s) element(s) to search in database:");
            		long startTime, endTime;
            		NumberFormat formatter = new DecimalFormat("#0.00000");
            		String words = scan.nextLine();
            		System.out.println("Enter search mode:");
                    System.out.println("1. or");
                    System.out.println("2. and");
                    int choice2 = Integer.parseInt(scan.nextLine());   
                    switch (choice2) {
                    	case 1: 
                    		startTime = System.currentTimeMillis();
                    		String occurrenceOR = searchModule.search(words, "or");
                    		endTime   = System.currentTimeMillis();
                    		System.out.println("Search result:");
                    		System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
                    		System.out.println(occurrenceOR);                  
                    		break;                          
                    	case 2: 
                    		startTime = System.currentTimeMillis();
                    		String occurrenceAND = searchModule.search(words, "and");
                    		endTime   = System.currentTimeMillis();
                    		System.out.println("Search result:");
                    		System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
                    		System.out.println(occurrenceAND);
                    		break;
                    	default: 
                    		System.out.println("Wrong Entry \n ");
                    		break;
                    }
            		break;                                          
            	default: 
            		System.out.println("Wrong Entry \n ");
            		break;   
            }
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.nextLine().charAt(0);  
        } while (ch == 'Y'|| ch == 'y'); 

        // Serialization 
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
            System.out.println("Base and trie has been serialized");
        }     
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
}

