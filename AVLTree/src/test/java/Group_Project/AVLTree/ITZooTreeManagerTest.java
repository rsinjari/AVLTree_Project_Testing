package Group_Project.AVLTree;

import static org.junit.Assert.assertNull;

import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ITZooTreeManagerTest {
	
	TreeManager tm;
	
	@Before
	public void initialize(){
		tm = new TreeManager();
	}
	
	@Test
	public void testCreateTree(){
		tm.createTree(true);
		
		tm.zootree.insertNode(5);
		tm.zootree.insertNode(6);
		tm.zootree.insertNode(4);
		
		Assert.assertNotNull(tm.zootree);
	}
	
	
	@Test
	public void testSerializeTree(){
		tm.createTree(true);
		StringWriter out = new StringWriter();
		tm.zootree.insertNode(30);
		tm.zootree.insertNode(10);
		tm.zootree.insertNode(5);
		tm.serializeZooTree(tm.zootree.root, out);
		String s = tm.serialize();
		Assert.assertNotNull(tm.zootree);
		Assert.assertEquals("Strings are the same: ", out.toString(), "10 5 $ $ 30 $ $ ");
		Assert.assertEquals("Strings are the same: ", s, "10 5 $ $ 30 $ $ ");
	}

}
