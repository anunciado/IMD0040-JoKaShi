package datastructure;

import java.io.Serializable;

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
	
	public String contains(String word) {
        Node current = this.root; 
        for (char letter : word.toCharArray()){
            if (current.get(letter) == null) {
            	return "Nenhuma ocorrência da palavra " + word;
            }       
            else {
            	current = current.get(letter);
            }
        }      
        if (current.isEnd() == true) {
        	String occurrence = current.toString();
        	occurrence = occurrence.replaceAll(Character.toString(Character.MIN_VALUE), word);
        	return occurrence;
        } 
        return "Nenhuma ocorrência da palavra " + word;
	}
	
	/* public List<String> getWordSuggestions(String word) {
        List<String> suggestionsList = new ArrayList <String>();
		return null; 

    } 
	*/
	public boolean isEmpty() {
		if (getRoot().isEnd()) return true;
		return false;
	} 
}