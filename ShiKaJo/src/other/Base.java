package other;

import java.util.ArrayList;

public class Base {
	private ArrayList <File> files;
	
	public Base() {
		files = new ArrayList <File>();
	}
	
	public void add(File file) {
		files.add(file);
	}
}
