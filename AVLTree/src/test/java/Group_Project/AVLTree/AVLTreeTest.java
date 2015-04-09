package Group_Project.AVLTree;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {
	
	AVLTree t;
	@Before
	public void initialize(){
		t = new AVLTree();
	}
	
	@Test
	public void testInsertNode(){
		assertNull("The tree is empty", t.root);
		//asserts that it inserts on root
		t.insertNode(1);
		assertNotNull("The tree is not empty anymore",t.root);
		assertEquals("The value entered in the tree is the one specified",1,t.root.data);
		//assert it inserts a variable in the right place
		t.insertNode(6);
		assertEquals("Assert it inserts to the right of root", 6, t.root.right.data);
		//asserts it inserts a variable to the left
		t.insertNode(0);
		assertEquals("Assert it inserts smaller value than the root to the left", 0,t.root.left.data);
		//asserts it inserts two variables correctly and it balances the tree correctly
		t.insertNode(4);
		t.insertNode(5);
		assertEquals("Assets position of 4",4,t.root.right.left.data);
		assertEquals("Assets position of 6",6,t.root.right.right.data);
		assertEquals("Assets position of 5",5,t.root.right.data);
		
		t.insertNode(10);
		t.insertNode(12);
		t.insertNode(13);
		t.insertNode(14);
		assertEquals("Assets root is 5",5,t.root.data);
		assertEquals("Assets root->left is 1",1,t.root.left.data);
		assertEquals("Assets root->left->left is 0",0,t.root.left.left.data);
		assertEquals("Assets root->left->right is 4",4,t.root.left.right.data);
		
		assertEquals("Assets root->right is 10",10,t.root.right.data);
		assertEquals("Assets root->right->left is 6",6,t.root.right.left.data);
		assertEquals("Assets root->right->right is 13",13,t.root.right.right.data);
		assertEquals("Assets root->right->right->left is 12",12,t.root.right.right.left.data);
		assertEquals("Assets root->right->right->right is 14",14,t.root.right.right.right.data);
		
		
	}
	
	@Test
	public void testFindHight(){
		for(int i = 0; i < 3; i++){
			t.insertNode(i);
		}
		assertEquals("We know that 3 nodes in a balanced tree is a height of 2 from the root ",
				2,t.findHeight(t.root));
	}
	
	@Test
	public void testDeleteNode(){
				
		t.insertNode(20);
		t.insertNode(15);
		t.insertNode(14);
		t.insertNode(26);
		assertTrue("Node 20 is in the tree", t.searchValueExists(20));		
		t.delete(20);
		assertFalse("Node 20 is in the tree", t.searchValueExists(20));
		
		//Deleting the one child case
		t.insertNode(20);
		t.insertNode(15);
		assertTrue("Node 15 is in the tree", t.searchValueExists(15));
		t.delete(15);
		assertFalse("Node 15 is in the tree", t.searchValueExists(15));
		t.delete(20);
		
		//Deleting a node
		t.insertNode(1);
		t.insertNode(2);
		t.insertNode(3);
		t.insertNode(4);
		t.insertNode(5);
	
		assertTrue("Node 3 is in the tree.", t.searchValueExists(3));
		t.delete(3);
		assertFalse("Node 3 is removed from the tree.", t.searchValueExists(3));
		
		assertFalse("Node 6 doesnt exist.", t.searchValueExists(6));
		t.delete(6);
		
		t.root = null;
		t.deleteNode(t.root,0);
		
	}
	@Test
	public void testSearchValue(){
		t.insertNode(5);
		t.insertNode(0);
		t.insertNode(2);
		t.insertNode(3);
		t.insertNode(10);
		assertNull("checking that the variable doesnt exists in the tree", t.searchValue(8));
		t.insertNode(8);
		assertEquals("Checking that the value is found after beeing inserted", 8 , t.searchValue(8).data);
	}
	@Test
	public void testBalanceTree(){
		//The purpose of this method is to create a situation that requires the tree to self balance upon insertion
		t.insertNode(25);
		t.insertNode(57);
		t.insertNode(17);
		t.insertNode(1);
		t.insertNode(19);
		assertEquals("Asserts that 25 is the root", 25 , t.root.data);
		assertEquals("Asserts that 17 is the left child", 17 , t.root.left.data);
		assertEquals("Asserts that 57 is the right child", 57 , t.root.right.data);
		assertEquals("Asserts that 1 is the left childs left child", 1 , t.root.left.left.data);
		assertEquals("Asserts that 19 is the left childs right child", 19 , t.root.left.right.data);
		
		//we insert a value that breaks the balance and check if the tree selfbalances correctly
		t.insertNode(10);
		assertEquals("Asserts that 17 is the root", 17 , t.root.data);
		assertEquals("Asserts that 1 is the left child", 1 , t.root.left.data);
		assertEquals("Asserts that 25 is the right child", 25 , t.root.right.data);
		assertEquals("Asserts that 19 is the right childs left child", 19 , t.root.right.left.data);
		assertEquals("Asserts that 57 is the left childs right child", 57 , t.root.right.right.data);
		assertEquals("Asserts that 10 is the left child right child", 10 , t.root.left.right.data);
		t.insertNode(20);
		t.insertNode(21);
		t.insertNode(12);
		t.insertNode(15);
		t.insertNode(14);
		assertEquals("Asserts that 17 is the root", 17 , t.root.data);
		assertEquals("Asserts that 10 is the left child", 10 , t.root.left.data);
		assertEquals("Asserts that 25 is the right child", 25 , t.root.right.data);
		assertEquals("Asserts that 1 is root->left->left", 1 , t.root.left.left.data);
		assertEquals("Asserts that 14 is root->left->right child", 14 , t.root.left.right.data);
		assertEquals("Asserts that 12 is root->left->right->left child", 12 , t.root.left.right.left.data);
		assertEquals("Asserts that 15 is root->left->right->right child", 15 , t.root.left.right.right.data);
		
		assertEquals("Asserts that 57 is root->right->right", 57 , t.root.right.right.data);
		assertEquals("Asserts that 20 is root->right->left child", 20 , t.root.right.left.data);
		assertEquals("Asserts that 19 is root->right->left->left child", 19 , t.root.right.left.left.data);
		assertEquals("Asserts that 21 is root->right->left->right child", 21 , t.root.right.left.right.data);
	}
	@Test
	public void testCheckBalance(){
		t.insertNode(1);
		assertEquals("Testing the hight of root", 1, t.findHeight(t.root));
		t.insertNode(6);
		t.insertNode(0);
		t.insertNode(4);
		t.insertNode(5);
		assertEquals("Testing the hight of 6", 0, t.getDifference(t.root.right.right));
		assertEquals("Testing the hight of 0", 0, t.getDifference(t.root.left));
		assertEquals("Testing the hight of 4", 0, t.getDifference(t.root.right.left));
		assertEquals("Testing the hight of 5", 0, t.getDifference(t.root.right));
	}
	@Test
	public void testIsEmpty(){
		assertTrue("Checking that the tree is empty and returns null.",t.isEmpty());
		t.insertNode(10);
		assertFalse("Checking that there is a root and its not null",t.isEmpty());
	}
	@Test
	public void searchRange(){
		t.insertNode(1);
		assertEquals("Testing the hight of root", 1, t.findHeight(t.root));
		t.insertNode(6);
		t.insertNode(0);
		t.insertNode(20);
		t.insertNode(25);
		t.insertNode(30);
		t.insertNode(35);
		t.insertNode(45);
		t.insertNode(55);
		
		int [] aa = {20, 25, 30};
		ArrayList<AVLNode> a = t.searchRange(19, 31);
		
		for(int i = 0; i < a.size(); i++){
			assertEquals("Testing searchRange with given variables ", aa[i], a.get(i).data);
		}		
	}	
}
