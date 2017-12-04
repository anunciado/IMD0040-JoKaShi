package modules;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


import database.Base;
import datastructure.Trie;

/**
 * This class represents the indexing module. The fields of this class are:
 * a trie, a base and a message string.
 * 
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	João Paulo 	  (vilarjp93@gmail.com)
 * @version 04.12.2017
 */
public class IndexModule {
	private Trie trie;
	private Base base;
	private String message;
	
	/**
	 * The constructor of the IndexModule class
	 * @param 	trie 	The trie
	 * @param 	base 	The base
	 */
	public IndexModule (Trie trie, Base base) {
		this.setTrie(trie);
		this.setBase(base);
	}
	
	/**
	 * Method responsible for get the words and insert in the trie
	 * @param 	location 	String
	 * @return	message 	The message
	 */
	public String index (String location) {
		try{
			location = location.substring(location.lastIndexOf("/") + 1);
			if (base.containsKey(location)){
				message = "File already added!";
				//System.err.println(message);
			}
			else {
				// Open the file that is the first 
				// command line parameter
				FileInputStream fstream = new FileInputStream(location);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				Set <String> auxiliar = new HashSet<String>();
				String strLine;
				//Read File Line By Line
				for (int i = 1; (strLine = br.readLine()) != null; i++) {
					List<String> words = new ArrayList<String>(Arrays.asList(strLine.split(" ")));
					for (String word: words) {
						word = word.replaceAll(" " , "");
					}
					words.removeAll(Arrays.asList(""));
					for (String word: words) {
						String result = Normalizer.normalize(word, Normalizer.Form.NFD);
						result = result.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
						result = result.replaceAll("[-|_.,()';\"]+","");
						result = result.replaceAll("\\{!-@\\}\\{[-]\\}","");
						this.trie.insert(result, location, i);
						auxiliar.add(result);
					}
				}
				base.put(location, auxiliar);
				//Close the input stream
				in.close();
				message = "File uploaded successfully";
			} 
		}
		catch (Exception e){//Catch exception if any
			message = "Error: " + e.getMessage();
			//System.err.println(message);
		}
		return message;
	}
	
	/**
	 * Remove the objects related to a file  
	 * @param 	file 		The file
	 * @return 	message 	The message
	 */
	public String remove (String file) {
		file = file.substring(file.lastIndexOf("/") + 1);
		this.show();
		if (base.containsKey(file)){
			this.trie.remove(file);
			this.base.remove(file);
			this.show();
			message = file + " removed successfully";
		}
		else {
			message = "File not added!";
			//System.err.println(message);
		}
		return message;
	}
	
	/**
	 * Update the objects related to a file
	 * @param 		location 	The location
	 * @return		message 	The message
	 */
	public String update (String location) {
		if (base.containsKey(location.substring(location.lastIndexOf("/") + 1))){
			this.remove(location);
			this.index(location);
			message = "File updated successfully";
		} else {
			message = "File not added!";
			//System.err.println(message);
		}
		
		return message;
	}
	
	/**
	 * Gets the trie
	 * @return 	trie 	The trie
	 */
	public Trie getTrie() {
		return trie;
	}

	/**
	 * Change the trie
	 * @param 	trie 	The trie
	 */
	public void setTrie(Trie trie) {
		this.trie = trie;
	}

	/**
	 * Returns the base of this class
	 * @return 	base 	The base
	 */
	public Base show() {
		return this.base;
	}

	/**
	 * Return the base of this class
	 * @return 	base 	The base
	 */
	public Base getBase() {
		return base;
	}

	/**
	 * Change the base value of this class
	 * @param 	base 	The base
	 */
	public void setBase(Base base) {
		this.base = base;
	}
	
	/**
	 * Convert this class to string
	 */
	public String toString() {
		return base.toString();
	}

	/**
	 * Return the words indexed
	 * @return 	words 	The words
	 */
	public String getWords() {
		List <String> words = new ArrayList <String>();
		trie.show(words);
		StringBuffer strReturn = new StringBuffer();
		for(String word : words) {
			strReturn.append(word + "\n");
		}
		return strReturn.toString();
	}
}
