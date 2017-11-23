package datastructure;

import java.io.Serializable;
import java.util.HashMap;

import database.Stats;

public class Trie implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Node root;
	
	public Trie() {
		root = new Node(Character.MIN_VALUE);
	}
	
	public Node getRoot() {
		return root;
	}

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
	
	public void remove(String file) {
		Node current = this.root; 
		if(current.size() > 0) {
			for (Node child : current.getChildren().values()) {
			    remove(file, child);
			}
		}
	} 
	
	public HashMap<String, Stats> contains(String word) {
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
        	return current.getWords();
        } 
        return null;
	}
	
	public void show(Node node, String word) {
		if(node.hasChildren()) {
			HashMap<Character, Node> children = node.getChildren();
			for (Node value : children.values()) {
				if(value.isEnd()) {
					System.out.println(word + value.getLetter());
				}
				if(value.hasChildren()) {
				  	show(value, word.concat(Character.toString(value.getLetter())));
				}
			}
		}
	}
	
	public void show() {
		Node node = this.root;
		String word = "";
		if(node.hasChildren()) {
			HashMap<Character, Node> children = node.getChildren();
			for (Node value : children.values()) {
				if(value.isEnd()) {
				 	System.out.println(word + value.getLetter());
				}
				if(value.hasChildren()) {
				  	show(value, word.concat(Character.toString(value.getLetter())));
				}
			}
		}
	}	   
	   
	public boolean isEmpty() {
		if (getRoot().isEnd()) return true;
		return false;
	} 
}