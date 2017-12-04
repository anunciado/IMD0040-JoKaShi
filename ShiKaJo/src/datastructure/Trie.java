package datastructure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Class that implements a trie (digital tree). This class has the 
 * following field root, that represents the root node of the tree. 
 * 
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	João Paulo 	  (vilarjp93@gmail.com)
 * @version 04.12.2017
 */
public class Trie implements Serializable {
	private static final long serialVersionUID = 1L;
	private Node root;
	
	/**
	 * The constructor of the class Trie
	 */
	public Trie() {
		root = new Node(Character.MIN_VALUE);
	}
	
	/**
	 * Returns a root node
	 * @return	root 		The root node
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * Method to insert a word in the Trie 
	 * @param 	word 		The word
	 * @param 	file		The word file 
	 * @param 	line 		The word line
	 */
	public void insert(String word, String file, int line) {
        Node current = this.root; 
        for (char letter : word.toCharArray()){
            Node child = current.get(letter);
            if (child != null) {
            	current = child;
            }
            else {
            	current.add(letter);
                current = current.get(letter);
            }
        }
        current.setEnd(file, line);
	}
	
	/**
	 * Remove a node of the Trie
	 * @param 	file 		The file
	 * @param 	current 	The current node
	 */
	private void remove(String file, Node current) {
		if(current.isEnd()) {
			current.remove(file);
		}
		if(current.size() > 0) {
			for (Node child : current.getChildren().values()) {
				remove(file, child);
			}
		}	
	} 
	
	/**
	 * Remove all the occurrence of the file word 
	 * @param 	file 	The archive
	 */
	public void remove(String file) {
		Node current = this.root; 
		if(current.size() > 0) {
			for (Node child : current.getChildren().values()) {
			    remove(file, child);
			}
		}
	} 
	
	/**
	 * Return the head node of a word
	 * @param 	word 		The word
	 * @return 	node 		The node of the word
	 */
	public Node getNode(String word) {
        Node current = this.root; 
        for (char letter : word.toCharArray()){
            if (current.get(letter) == null) {
            	return null;
            }       
            else {
            	current = current.get(letter);
            }
        }      
        if (current.isEnd()) {
        	return current;
        } 
        return null;
	}
	
	/**
	 * 
	 * @param 	words 	The words
	 * @param 	node 	The node
	 * @param 	word	The word
	 */
	public void show(List <String> words, Node node, String word) {
		if(node.hasChildren()) {
			HashMap<Character, Node> children = node.getChildren();
			for (Node value : children.values()) {
				if(value.isEnd() && !value.getWords().isEmpty()) {
					words.add(word + value.getLetter());
				}
				if(value.hasChildren()) {
				  	show(words, value, word.concat(Character.toString(value.getLetter())));
				}
			}
		}
	}
	
	/**
	 * Method to show the 
	 * @param 	words 	The words
	 */
	public void show(List <String> words) {
		Node node = this.root;
		String word = "";
		if(node.hasChildren()) {
			HashMap<Character, Node> children = node.getChildren();
			for (Node value : children.values()) {
				if(value.isEnd()) {
					words.add(word + value.getLetter());
				}
				if(value.hasChildren()) {
				  	show(words, value, word.concat(Character.toString(value.getLetter())));
				}
			}
		}
	}
	   
	/**
	 * Return true if the the Trie is empty and false in otherwise
	 * @return true/false
	 */
	public boolean isEmpty() {
		if (getRoot().isEnd()) return true;
		return false;
	} 
}