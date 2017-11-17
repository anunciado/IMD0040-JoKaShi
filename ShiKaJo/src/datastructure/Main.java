package datastructure;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.Base;
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
            System.out.println("4. show");
            System.out.println("5. search");

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
            		System.out.println("Enter string element to search in database:");
            		String occurrence = searchModule.search(scan.nextLine());
            		System.out.println("Search result:");
            		System.out.println(occurrence);
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

