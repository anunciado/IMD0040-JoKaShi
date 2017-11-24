package database;

import java.util.HashMap;

public class Stats implements Comparable<Stats> {
	private HashMap<Integer,Integer> lines;
	
	public Stats() {
		this.setLines(new HashMap<Integer,Integer>());
	}

	public HashMap<Integer,Integer> getLines() {
		return lines;
	}

	public void setLines(HashMap<Integer,Integer> lines) {
		this.lines = lines;
	}

	public int calculeOcurrence() {
		int ocurrence = 0;
		for (Integer line : lines.values()) {
			ocurrence += line;
		}
		return ocurrence;
	}

	@Override
	public int compareTo(Stats stats) {
		if(stats.calculeOcurrence() > this.calculeOcurrence()) return 1;
		else if(stats.calculeOcurrence() > this.calculeOcurrence()) return 0;
		else return -1;
	}
}
