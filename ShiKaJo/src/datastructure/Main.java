package datastructure;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import modules.IndexModule;
import modules.SearchModule;

/* Class Trie Test */

public class Main{

    private static Scanner scan;

	public static void main(String[] args) throws  InputMismatchException {            

		scan = new Scanner(System.in);
        
        Trie trie = new Trie();
        IndexModule indexModule = new IndexModule(trie);
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
            System.out.println("4. search");

            int choice = scan.nextInt();            
            switch (choice) {
            	case 1: 
            		System.out.println("Add words of which file:");
            		indexModule.index(scan.next());                     
            		break;                          
            	case 2: 
            		System.out.println("Delete words of which file:");
            		indexModule.remove(scan.next()); 
            		break; 
            	case 3: 
            		System.out.println("Update words of which file:");
            		indexModule.update(scan.next()); 
            		break; 
            	case 4: 
            		System.out.println("Enter string element to search in database:");
            		//String words = scan.nextLine();
            		String words = scan.next();
            		String occurrence = searchModule.search(words);
            		System.out.println("Search result:");
            		System.out.println(occurrence);
            		break;                                          
            	default: 
            		System.out.println("Wrong Entry \n ");
            		break;   
            }
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');               
    }
}

