package Group_Project.AVLTree;

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
	public void testLoadTree(){
		throw new RuntimeException("test method not implemented");
	}
	
	@Test
	public void testsaveTree(){
		throw new RuntimeException("test method not implemented");
	}
	
	@Test
	public void testSerializeTree(){
		throw new RuntimeException("test method not implemented");
	}
	
	@Test
	public void testDeSerializeTree(){
		throw new RuntimeException("test method not implemented");
	}
}
