package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

import database.Base;

/**
 * Class BaseTest it's a class to test the methods of the Class Base.
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	Joao Paulo 	  (vilarjp93@gmail.com)
 * @version 2017.11.21
 */

public class BaseTest {
	
	// A base structure
	private Base base;
	
	/**
	 * Public method to set up the tests of the functions of the class Base.
	 */
	@Before
	public void setUp() throws Exception {
		base = new Base();
	}
	
	/**
	 * Public method to test the function put() of the class Base.
	 */
	@Test
	public void testPut() {
		base.put("1.txt", new HashSet<String>());
		assertEquals(base.containsKey("1.txt"), true);
		assertEquals(base.containsKey("2.txt"), false);
	}
	
	/**
	 * Public method to test the function remove() of the class Base.
	 */
	@Test
	public void testRemove() {
		base.remove("1.txt");
		assertEquals(base.containsKey("1.txt"), false);
	}

	/**
	 * Public method to test the function containsKey() of the class Base.
	 */
	@Test
	public void testContainsKey() {
		base.put("1.txt", new HashSet<String>());
		assertEquals(base.containsKey("1.txt"), true);
		assertEquals(base.containsKey("2.txt"), false);
	}

}
