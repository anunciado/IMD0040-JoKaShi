package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datastructure.Node;
import datastructure.Trie;

/**
 * Class TrieTest it's a class to test the methods of the Class Trie. 
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	Joao Paulo 	  (vilarjp93@gmail.com)
 * @version 2017.11.21
 */

public class TrieTest {
	
	// A trie data structure
	private Trie trie;
	
	/**
	 * Public method to set up the tests of the functions of the class Trie.
	 */
	@Before
	public void setUp() throws Exception {
		trie = new Trie();
	}
	
	/**
	 * Public method to test the function insert() of the class Trie.
	 */
	@Test
	public void testInsert() {
		trie.insert("palavra", "1.txt", 1);
		Node node = trie.getNode("palavra");
		if (node != null) {
			assertTrue(true);
		}
		else {
			fail();
		}
	}
	
	/**
	 * Public method to test the function remove() of the class Trie.
	 */
	@Test
	public void testRemove() {
		trie.remove("1.txt");
		assertEquals(trie.getNode("palavra"), null);
	}

}
