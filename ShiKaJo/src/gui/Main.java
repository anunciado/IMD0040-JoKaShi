package gui;

import java.io.File;
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
        
        Trie trie = new Trie();
        Base base = new Base();
        IndexModule indexModule = new IndexModule(trie, base);
        SearchModule searchModule = new SearchModule(trie);
        System.out.println(new File(".").getAbsolutePath());
        System.out.println("Trie Test\n");          
        char ch;
        /*  Perform tree operations  */
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
            		indexModule.update(scan.nextLine()); 
            		break; 
            	case 4: 
            		System.out.println("Added files:");
            		System.out.println(indexModule.show());
            		break; 
            	case 5:
            		System.out.println("Added words:");
            		trie.show();
            		break;
            	case 6: 
            		System.out.println("Enter string(s) element(s) to search in database:");
            		String words = scan.nextLine();
            		System.out.println("Enter search mode:");
                    System.out.println("1. or");
                    System.out.println("2. and");
                    int choice2 = Integer.parseInt(scan.nextLine());            
                    switch (choice2) {
                    	case 1: 
                    		String occurrenceOR = searchModule.search(scan.nextLine(), "or");
                    		System.out.println("Search result:");
                    		System.out.println(occurrenceOR);                  
                    		break;                          
                    	case 2: 
                    		String occurrenceAND = searchModule.search(scan.nextLine(), "and");
                    		System.out.println("Search result:");
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
    }
}

