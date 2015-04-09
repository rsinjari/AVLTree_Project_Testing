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
		
		assertNull("The tree is empty", t.root);
		//asserts that it inserts on root
		t.insertNode(1);
		assertNotNull("The tree is not empty anymore",t.root);
		assertEquals("The value entered in the tree is the one specified",1,t.root.data);
		//assert it inserts a variable in the right place
		t.insertNode(6);
		assertEquals("Assert it inserts to the right of root", 6, t.getNodeByAddress(t.root.right).data);
		//asserts it inserts a variable to the left
		t.insertNode(0);
		assertEquals("Assert it inserts smaller value than the root to the left", 0,t.getNodeByAddress(t.root.left).data);
		//asserts it inserts two variables correctly and it balances the tree correctly
		t.insertNode(4);
		t.insertNode(5);
		assertEquals("Assets position of 4",4,t.getNodeByAddress(t.getNodeByAddress(t.root.right).left).data);
		assertEquals("Assets position of 6",6,t.getNodeByAddress(t.getNodeByAddress(t.root.right).right).data);
		assertEquals("Assets position of 5",5,t.getNodeByAddress(t.root.right).data);
		
		t.insertNode(10);
		t.insertNode(12);
		t.insertNode(13);
		t.insertNode(14);
		assertEquals("Assets root is 5",5,t.root.data);
		assertEquals("Assets root->left is 1",1,t.getNodeByAddress(t.root.left).data);
		assertEquals("Assets root->left->left is 0",0,t.getNodeByAddress(t.getNodeByAddress(t.root.left).left).data);
		assertEquals("Assets root->left->right is 4",4,t.getNodeByAddress(t.getNodeByAddress(t.root.left).right).data);
		
		assertEquals("Assets root->right is 10",10,t.getNodeByAddress(t.root.right).data);
		assertEquals("Assets root->right->left is 6",6,t.getNodeByAddress(t.getNodeByAddress(t.root.right).left).data);
		assertEquals("Assets root->right->right is 13",13,t.getNodeByAddress(t.getNodeByAddress(t.root.right).right).data);
		assertEquals("Assets root->right->right->left is 12",12,t.getNodeByAddress(t.getNodeByAddress(t.getNodeByAddress(t.root.right).right).left).data);
		assertEquals("Assets root->right->right->right is 14",14,t.getNodeByAddress(t.getNodeByAddress(t.getNodeByAddress(t.root.right).right).right).data);
		
		
		
		/*t.insertNode(25);
		t.insertNode(57);
		t.insertNode(17);
		t.insertNode(1);
		t.insertNode(19);
		Assert.assertEquals("Asserts that 25 is the root", 25 , t.root.data);
		Assert.assertEquals("Asserts that 17 is the left child", 17 , (t.getNodeByAddress(t.root.left)).data);
		Assert.assertEquals("Asserts that 57 is the right child", 57 ,(t.getNodeByAddress(t.root.right)).data);
		Assert.assertEquals("Asserts that 1 is the left childs left child", 1 ,(t.getNodeByAddress((t.getNodeByAddress(t.root.left)).left)).data);
		Assert.assertEquals("Asserts that 19 is the left childs right child", 19 , (t.getNodeByAddress((t.getNodeByAddress(t.root.left)).right)).data);*/
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
