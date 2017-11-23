package datastructure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Node implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private char letter;
	private HashMap <Character, Node> children;
	private HashMap <String, HashMap<Integer,Integer>> words;
	
	public Node(char letter) {
		this.letter = letter;
		this.children = new HashMap <Character, Node>();
		this.words = new HashMap <String, HashMap<Integer,Integer>>();
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
			HashMap<Integer, Integer> lines = this.words.get(file);
			if(lines.containsKey(line)) {
				lines.put(line, lines.get(line) + 1);
			}
			else {
				lines.put(line, 1);
			}
			this.words.put(file, lines);
		}
		else {
			HashMap<Integer, Integer> lines = new HashMap<Integer, Integer>();
			lines.put(line, 1);
			this.words.put(file, lines);
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
	
	public String toString() {
		if(this.isEnd()) {
			if(words.containsKey("blacklist.txt")) {
				return "Palavra imprópria";
			}
			else {
				String s = "";
				for (Map.Entry <String, HashMap<Integer,Integer>> entry : words.entrySet()) {
					String file = entry.getKey();
				    HashMap<Integer,Integer> lines = entry.getValue();
				    for (Map.Entry<Integer, Integer> entry2 : lines.entrySet()) {
				        int line = entry2.getKey();
				        int occurrence = entry2.getValue();
				        if (occurrence > 1){
					        s = s.concat(file + ": " + occurrence + "​ ocorrência​s da palavra " + Character.MIN_VALUE + "​​ na linha " + line + "\n");
				        }
				        else{
					        s = s.concat(file + ": " + occurrence + "​ ocorrência​ da palavra " + Character.MIN_VALUE + "​​ na linha " + line + "\n");
				        }
				    }
				}
				return s;
			}
		}
		return String.valueOf(this.getLetter());	
	}
}
