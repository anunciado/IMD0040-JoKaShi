package datastructure;

import java.io.Serializable;
import java.util.HashMap;

import database.Stats;

public class Node implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private char letter;
	private HashMap <Character, Node> children;
	private HashMap <String, Stats> words;
	
	public Node(char letter) {
		this.letter = letter;
		this.children = new HashMap <Character, Node>();
		this.words = new HashMap <String, Stats>();
	}
	
	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	public HashMap<Character, Node> getChildren() {
		return children;
	}
	
	public boolean isEnd() {
		if(this.words.size() > 0) return true;
		return false;
	}

	public void setEnd(String file, int line) {
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
	
	public void add(char letter){
		Node trieNode = new Node(letter);
		children.put(letter, trieNode);
	}
	
	public void remove(String file){
		words.remove(file);
	}
	
	public Node get(char letter){
		return children.get(letter);
	}
	
	public int size() {
		return children.size();
	}
	
	public boolean hasChildren() {
		if(children.size() > 0) return true;
		return false;
	}
	
	public HashMap<String, Stats> getWords() {
		if(words.containsKey("blacklist.txt")) {
			return null;
		}
		else {
			return words;
		}
	}

	public String toString() {
		if(this.isEnd()) {
			if(words.containsKey("blacklist.txt")) {
				return "Palavra imprópria";
			}
			else {
				return String.valueOf(this.getLetter());
			}
		}
		return String.valueOf(this.getLetter());	
	}
}
