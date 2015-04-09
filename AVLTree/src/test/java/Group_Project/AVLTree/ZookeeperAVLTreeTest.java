package Group_Project.AVLTree;

import static org.junit.Assert.*;

import org.apache.zookeeper.KeeperException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ZookeeperAVLTreeTest {
	
	ZookeeperAVLTree t;
	@Before
	public void initialize(){
		t = new ZookeeperAVLTree();
	}
	
	@Test
	public void testInsertNode(){
		
		t.insertNode(1);
		t.insertNode(3);
		t.insertNode(2);

		Assert.assertNotNull(t.root);
		Assert.assertEquals(t.root.data, 2);
		Assert.assertEquals((t.getNodeByAddress(t.root.left)).data, 1);
		Assert.assertEquals((t.getNodeByAddress(t.root.right)).data, 3);
	}
	@Test//(expected )
	public void testGetNodeByAddress(){
		ZookeeperAVLNode s = t.getNodeByAddress("/wrong address");
		assertNull("Wronf address testing", s);
	}
	
	@Test
	public void testFindHeight(){
		for(int i = 0; i < 3; i++){
			t.insertNode(i);
		}
		Assert.assertEquals("We know that 3 nodes in a balanced tree is a height of 2 from the root ",
				2,t.findHeight(t.root));
	}
	@Test
	public void testDeleteNode(){
		t.insertNode(1);
		t.insertNode(3);
		t.insertNode(2);
		
		t.delete(3);
		
		Assert.assertEquals(t.root.data, 1);
		Assert.assertEquals((t.getNodeByAddress(t.root.right)).data, 2);
		Assert.assertNull(t.root.left);
	}
	@Test
	public void testSearchValue(){
		for(int i = 0; i < 10; i++){
			t.insertNode(i);
		}
		for(int j = 0; j < 10; j++){
			Assert.assertEquals(j, t.searchValue(j).data);
		}
	}
	@Test
	public void testBalanceTree(){
		t.insertNode(25);
		t.insertNode(57);
		t.insertNode(17);
		t.insertNode(1);
		t.insertNode(19);
		Assert.assertEquals("Asserts that 25 is the root", 25 , t.root.data);
		Assert.assertEquals("Asserts that 17 is the left child", 17 , (t.getNodeByAddress(t.root.left)).data);
		Assert.assertEquals("Asserts that 57 is the right child", 57 ,(t.getNodeByAddress(t.root.right)).data);
		Assert.assertEquals("Asserts that 1 is the left childs left child", 1 ,(t.getNodeByAddress((t.getNodeByAddress(t.root.left)).left)).data);
		Assert.assertEquals("Asserts that 19 is the left childs right child", 19 , (t.getNodeByAddress((t.getNodeByAddress(t.root.left)).right)).data);
	}
	@Test
	public void testCheckBalance(){
		//this number shows that the tree is balanced if 1 or -1
		t.insertNode(1);
		t.insertNode(2);
		
		int bal = t.getDifference(t.root);
		Assert.assertEquals(Math.abs(bal), 1);
	}
	@Test
	public void testIsEmpty(){
		Assert.assertTrue(t.isEmpty());
	}
}
