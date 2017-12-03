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

public class IndexModule {
	private Trie trie;
	private Base base;
	private String message;
	
	public IndexModule(Trie trie, Base base) {
		this.setTrie(trie);
		this.setBase(base);
	}
	
	public String index(String location) {
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
						result = result.replaceAll("[-|_.,()]+","");
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
	
	public String remove(String file) {
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
	
	public String update(String location) {
		if (base.containsKey(location.substring(location.lastIndexOf("/") + 1))){
			this.remove(location);
			this.index(location);
			message = "File updated successfully";
		}
		else {
			message = "File not added!";
			//System.err.println(message);
		}
		
		return message;
		
	}
	
	public Trie getTrie() {
		return trie;
	}

	public void setTrie(Trie trie) {
		this.trie = trie;
	}

	public Base show() {
		return this.base;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}
	
	public String toString() {
		return base.toString();
	}

}
