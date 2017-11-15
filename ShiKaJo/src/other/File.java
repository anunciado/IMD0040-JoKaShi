package other;

import java.util.HashMap;

public class File {
	private int numberOfWords;
	private String name;
	private HashMap<Integer, String> lines;
	
	public int getNumberOfWords() {
		return numberOfWords;
	}
	public void setNumberOfWords(int numberOfWords) {
		this.numberOfWords = numberOfWords;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<Integer, String> getLines() {
		return lines;
	}
	public void setLines(HashMap<Integer, String> lines) {
		this.lines = lines;
	}
	public File(String name) {
		this.name = name;
		this.numberOfWords = 0;
		lines = new HashMap<Integer, String>();
	}
	public void add(int line, String strLine) {
		lines.put(line, strLine);
	}
	
}
