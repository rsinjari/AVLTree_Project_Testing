package Group_Project.AVLTree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
	
	@Test
	public void testBalanceTreeIntegration(){
		tm.createTree(true);
		assertNull("The tree is empty", tm.zootree.root);
		//asserts that it inserts on root
		tm.zootree.insertNode(1);
		assertNotNull("The tree is not empty anymore",tm.zootree.root);
		assertEquals("The value entered in the tree is the one specified",1,tm.zootree.root.data);
		//assert it inserts a variable in the right place
		tm.zootree.insertNode(6);
		assertEquals("Assert it inserts to the right of root", 6, tm.zootree.getNodeByAddress(tm.zootree.root.right).data );
		//asserts it inserts a variable to the left
		tm.zootree.insertNode(0);
		assertEquals("Assert it inserts smaller value than the root to the left", 0,tm.zootree.getNodeByAddress(tm.zootree.root.left).data);
		//asserts it inserts two variables correctly and it balances the tree correctly
		tm.zootree.insertNode(4);
		tm.zootree.insertNode(5);
		assertEquals("Assets position of 4",4,tm.zootree.getNodeByAddress(tm.zootree.getNodeByAddress(tm.zootree.root.right).left).data);
		assertEquals("Assets position of 6",6,tm.zootree.getNodeByAddress(tm.zootree.getNodeByAddress(tm.zootree.root.right).right).data);
		assertEquals("Assets position of 5",5,tm.zootree.getNodeByAddress(tm.zootree.root.right).data);
		
		tm.zootree.insertNode(10);
		tm.zootree.insertNode(12);
		tm.zootree.insertNode(13);
		tm.zootree.insertNode(14);
		assertEquals("Assets root is 5",5,tm.zootree.root.data);
		assertEquals("Assets root->left is 1",1,tm.zootree.getNodeByAddress(tm.zootree.root.left).data);
		assertEquals("Assets root->left->left is 0",0,tm.zootree.getNodeByAddress(tm.zootree.getNodeByAddress(tm.zootree.root.left).left).data);
		assertEquals("Assets root->left->right is 4",4,tm.zootree.getNodeByAddress(tm.zootree.getNodeByAddress(tm.zootree.root.left).right).data);
		
		assertEquals("Assets root->right is 10",10,tm.zootree.getNodeByAddress(tm.zootree.root.right).data);
		assertEquals("Assets root->right->left is 6",6,tm.zootree.getNodeByAddress(tm.zootree.getNodeByAddress(tm.zootree.root.right).left).data);
		assertEquals("Assets root->right->right is 13",13,tm.zootree.getNodeByAddress(tm.zootree.getNodeByAddress(tm.zootree.root.right).right).data);
		assertEquals("Assets root->right->right->left is 12",12,tm.zootree.getNodeByAddress(tm.zootree.getNodeByAddress(tm.zootree.getNodeByAddress(tm.zootree.root.right).right).left).data);
		assertEquals("Assets root->right->right->right is 14",14,tm.zootree.getNodeByAddress(tm.zootree.getNodeByAddress(tm.zootree.getNodeByAddress(tm.zootree.root.right).right).right).data);
	}
	@Test
	public void testCheckBalanceIntegration(){
		//this number shows that the tree is balanced if 1 or -1
		tm.createTree(true);
		tm.zootree.insertNode(1);
		tm.zootree.insertNode(2);
		
		int bal = tm.zootree.getDifference(tm.zootree.root);
		Assert.assertEquals(Math.abs(bal), 1);
	}
	@Test
	public void testIsEmptyIntegration(){
		tm.createTree(true);
		Assert.assertTrue(tm.zootree.isEmpty());
	}
	@Test
	public void testUpdateNodeIntegration(){
		tm.createTree(true);
		int epoch = Math.abs((int) System.currentTimeMillis());
		//System.out.println(epoch);
		tm.zootree.updateNode(null);
		ZookeeperAVLNode a = new ZookeeperAVLNode(null, null, epoch);
		tm.zootree.updateNode(a);
		assertEquals("Testing the updateed data", epoch, tm.zootree.getNodeByAddress(a.location).data);
	}
	
	@Test
	public void testInsertNodeInteration(){
		tm.createTree(true);
		tm.zootree.insertNode(1);
		tm.zootree.insertNode(3);
		tm.zootree.insertNode(2);

		Assert.assertNotNull(tm.zootree.root);
		Assert.assertEquals(tm.zootree.root.data, 2);
		Assert.assertEquals((tm.zootree.getNodeByAddress(tm.zootree.root.left)).data, 1);
		Assert.assertEquals((tm.zootree.getNodeByAddress(tm.zootree.root.right)).data, 3);
	}
	@Test//(expected )
	public void testGetNodeByAddressIntegration(){
		tm.createTree(true);
		ZookeeperAVLNode s = tm.zootree.getNodeByAddress("/wrong address");
		assertNull("Wronf address testing", s);
	}
	
	@Test
	public void testFindHeight(){
		tm.createTree(true);
		for(int i = 0; i < 3; i++){
			tm.zootree.insertNode(i);
		}
		Assert.assertEquals("We know that 3 nodes in a balanced tree is a height of 2 from the root ",
				2,tm.zootree.findHeight(tm.zootree.root));
	}
	@Test
	public void testDeleteNode(){
		tm.createTree(true);
		tm.zootree.insertNode(1);
		tm.zootree.insertNode(3);
		tm.zootree.insertNode(2);
		
		tm.zootree.delete(3);
		
		Assert.assertEquals(tm.zootree.root.data, 1);
		Assert.assertEquals((tm.zootree.getNodeByAddress(tm.zootree.root.right)).data, 2);
		Assert.assertNull(tm.zootree.root.left);
	}
	@Test
	public void testSearchValue(){
		tm.createTree(true);
		for(int i = 0; i < 10; i++){
			tm.zootree.insertNode(i);
		}
		for(int j = 0; j < 10; j++){
			Assert.assertEquals(j, tm.zootree.searchValue(j).data);
		}
	}

}
