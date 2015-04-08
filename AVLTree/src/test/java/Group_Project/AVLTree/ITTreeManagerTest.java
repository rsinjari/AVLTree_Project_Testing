package Group_Project.AVLTree;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ITTreeManagerTest {
	
	TreeManager tm;
	
	@Before
	public void initialize(){
		tm = new TreeManager();
	}
	
	@Test
	public void testCreateTree(){
		tm.createTree();
		
		tm.tree.insertNode(5);
		tm.tree.insertNode(6);
		tm.tree.insertNode(4);
		
		Assert.assertNotNull(tm.tree);
	}
	
	@Test
	public void testSerializeTree(){
		tm.createTree();
		StringWriter out = new StringWriter();
		tm.tree.insertNode(30);
		tm.tree.insertNode(10);
		tm.tree.insertNode(5);
		tm.serializeTree(tm.tree.root, out);
		
		Assert.assertNotNull(tm.tree);
		Assert.assertEquals("Strings are the same: ", out.toString(), "10 5 $ $ 30 $ $ ");
		
	}
	
	@Test
	public void testDeSerializeTree(){
		tm.createTree();
		String [] ss = {};
		tm.tree.root = tm.deserializeTree(ss);
		assertNull(tm.tree.root);
		
		String[] s = {"10","5","$","$","30","$","$"};
		
		tm.tree.root = tm.deserializeTree(s);
		
		Assert.assertNotNull(tm.tree.root);
		Assert.assertEquals("The root should be 10: ", tm.tree.root.data, 10);
		Assert.assertEquals("Left child of root should be 5: ", tm.tree.root.left.data, 5);
		Assert.assertEquals("Right child of root should be 30: ", tm.tree.root.right.data, 30);
		
	}
	
	@Test
	public void testsaveTree(){
		tm.createTree();
		tm.tree.insertNode(12);
		tm.tree.insertNode(25);
		tm.tree.insertNode(5);
		String expected = "5 12 25";
		String a = tm.saveTree(tm.tree, "filename");
		assertEquals("Testing save tree",expected, a);
	}
	
	@Test
	public void testLoadTree() throws FileNotFoundException{
		/*String s = " ";	
		
		String testFileName = "testSerialize.txt";
	    String expected = "10 5 $ $ 30 $ $ ";
	    
	    Scanner sc = new Scanner(testFileName);
		while (sc.hasNext()) {
		    s = sc.nextLine();
		}
		sc.close();		
		
	    assertEquals(expected, s);*/
	}
	
	
}
