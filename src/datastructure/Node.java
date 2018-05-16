package datastructure;

import java.io.Serializable;
import java.util.HashMap;

import database.Stats;

/**
 * This class represents a node of the Trie. Each node haas
 * a letter, n-children and m-words
 * 
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	Jo„o Paulo 	  (vilarjp93@gmail.com)
 * @version 04.12.2017
 */
public class Node implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private char letter; 							/** < The letter */
	private HashMap <Character, Node> children;		/** < The children */
	private HashMap <String, Stats> words;			/** < The words */
	
	/**
	 * The constructor of the Node
	 * @param letter 	The letter
	 */
	public Node (char letter) {
		this.letter = letter;
		this.children = new HashMap <Character, Node>();
		this.words = new HashMap <String, Stats>();
	}
	
	/**
	 * Return the letter if the Node
	 * @return 	letter 		The letter
	 */
	public char getLetter () {
		return letter;
	}

	/**
	 * Change the letter of the Node
	 * @param 	letter 		The letter
	 */
	public void setLetter (char letter) {
		this.letter = letter;
	}
	
	/**
	 * Return the children of the Node
	 * @return 	children 	The children
	 */
	public HashMap<Character, Node> getChildren () {
		return children;
	}
	
	/**
	 * Return true if the node is leaf (that is, if the node hasn't any children) 
	 * and false in otherwise 
	 * @return 	true/false
	 */
	public boolean isEnd () {
		if(this.words.size() > 0 && !words.containsKey("blacklist.txt")) return true;
		return false;
	}

	/**
	 * Set the end in the node
	 * @param 	file	The file
	 * @param 	line 	The file line
	 */
	public void setEnd (String file, int line) {
		if(this.words.containsKey(file)) {
			Stats stats = this.words.get(file);
			if(stats.getLines().containsKey(line)) {
				stats.getLines().put(line, stats.getLines().get(line) + 1);
			}
			else {
				stats.getLines().put(line, 1);
			}
			this.words.put(file, stats);
		}
		else {
			Stats stats = new Stats();
			stats.getLines().put(line, 1);
			this.words.put(file, stats);
		}
	}
	
	/**
	 * Add a word in the node
	 * @param 	letter 		The letter
	 */
	public void add (char letter){
		Node trieNode = new Node(letter);
		children.put(letter, trieNode);
	}
	
	/**
	 * Remove the key of the HashMap
	 * @param 	file 	The file
	 */
	public void remove (String file) {
		words.remove(file);
	}
	
	/**
	 * Return the node according with the letter received 
	 * @param 	letter 	The letter
	 * @return 	node 	The child node 
	 */
	public Node get (char letter) {
		return children.get(letter);
	}
	
	/** 
	 * Return the size of the node, that is the children amount
	 * @return	The amount children
	 */
	public int size () {
		return children.size();
	}
	
	/**
	 * Verify if the node has children. Return true if has some 
	 * children and false in otherwise
	 * @return	true/false
	 */
	public boolean hasChildren () {
		if(children.size() > 0) return true;
		return false;
	}
	
	/**
	 * Get the words of the node
	 * @return 	words 	The words
	 */
	public HashMap<String, Stats> getWords () {
		if(words.containsKey("blacklist.txt")) {
			return new HashMap <String, Stats>();
		}
		else {
			return words;
		}
	}

	/**
	 * Convert the node like a string
	 */
	public String toString () {
		if(this.isEnd()) {
			if(words.containsKey("blacklist.txt")) {
				return "Palavra impr√≥pria";
			}
			else {
				return String.valueOf(this.getLetter());
			}
		}
		return String.valueOf(this.getLetter());	
	}
}
