package Group_Project.AVLTree;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.ArrayList;
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
		tm.createTree(false);
		
		tm.tree.insertNode(5);
		tm.tree.insertNode(6);
		tm.tree.insertNode(4);
		
		Assert.assertNotNull(tm.tree);
			
	}
	
	@Test
	public void testCreateTreeIntegration(){
		tm.createTree(false);
		assertNull("The tree is empty", tm.tree.root);
		//asserts that it inserts on root
		tm.tree.insertNode(1);
		assertNotNull("The tree is not empty anymore",tm.tree.root);
		assertEquals("The value entered in the tree is the one specified",1,tm.tree.root.data);
		//assert it inserts a variable in the right place
		tm.tree.insertNode(6);
		assertEquals("Assert it inserts to the right of root", 6, tm.tree.root.right.data);
		//asserts it inserts a variable to the left
		tm.tree.insertNode(0);
		assertEquals("Assert it inserts smaller value than the root to the left", 0,tm.tree.root.left.data);
		//asserts it inserts two variables correctly and it balances the tree correctly
		tm.tree.insertNode(4);
		tm.tree.insertNode(5);
		assertEquals("Assets position of 4",4,tm.tree.root.right.left.data);
		assertEquals("Assets position of 6",6,tm.tree.root.right.right.data);
		assertEquals("Assets position of 5",5,tm.tree.root.right.data);
		
		tm.tree.insertNode(10);
		tm.tree.insertNode(12);
		tm.tree.insertNode(13);
		tm.tree.insertNode(14);
		assertEquals("Assets root is 5",5,tm.tree.root.data);
		assertEquals("Assets root->left is 1",1,tm.tree.root.left.data);
		assertEquals("Assets root->left->left is 0",0,tm.tree.root.left.left.data);
		assertEquals("Assets root->left->right is 4",4,tm.tree.root.left.right.data);
		
		assertEquals("Assets root->right is 10",10,tm.tree.root.right.data);
		assertEquals("Assets root->right->left is 6",6,tm.tree.root.right.left.data);
		assertEquals("Assets root->right->right is 13",13,tm.tree.root.right.right.data);
		assertEquals("Assets root->right->right->left is 12",12,tm.tree.root.right.right.left.data);
		assertEquals("Assets root->right->right->right is 14",14,tm.tree.root.right.right.right.data);
	}
	
	@Test
	public void testSearchOntreeIntegration(){
		tm.createTree(false);
		tm.tree.insertNode(5);
		tm.tree.insertNode(0);
		tm.tree.insertNode(2);
		tm.tree.insertNode(3);
		tm.tree.insertNode(10);
		assertNull("checking that the variable doesnt exists in the tree", tm.tree.searchValue(8));
		tm.tree.insertNode(8);
		assertEquals("Checking that the value is found after beeing inserted", 8 , tm.tree.searchValue(8).data);
	}	
	
	@Test
	public void testSearchRangeIntegration(){
		tm.createTree(false);
		
		tm.tree.insertNode(1);
		assertEquals("Testing the hight of root", 1, tm.tree.findHeight(tm.tree.root));
		tm.tree.insertNode(6);
		tm.tree.insertNode(0);
		tm.tree.insertNode(20);
		tm.tree.insertNode(25);
		tm.tree.insertNode(30);
		tm.tree.insertNode(35);
		tm.tree.insertNode(45);
		tm.tree.insertNode(55);
		
		int [] aa = {20, 25, 30};
		ArrayList<AVLNode> a = tm.tree.searchRange(19, 31);
		
		for(int i = 0; i < a.size(); i++){
			assertEquals("Testing searchRange with given variables ", aa[i], a.get(i).data);
		}		
		
	}
	@Test 
	public void testBalanceTreeIntegration(){
		tm.createTree(false);
		tm.tree.insertNode(25);
		tm.tree.insertNode(57);
		tm.tree.insertNode(17);
		tm.tree.insertNode(1);
		tm.tree.insertNode(19);
		assertEquals("Asserts that 25 is the root", 25 , tm.tree.root.data);
		assertEquals("Asserts that 17 is the left child", 17 , tm.tree.root.left.data);
		assertEquals("Asserts that 57 is the right child", 57 , tm.tree.root.right.data);
		assertEquals("Asserts that 1 is the left childs left child", 1 , tm.tree.root.left.left.data);
		assertEquals("Asserts that 19 is the left childs right child", 19 , tm.tree.root.left.right.data);
		
		//we insert a value that breaks the balance and check if the tree selfbalances correctly
		tm.tree.insertNode(10);
		assertEquals("Asserts that 17 is the root", 17 , tm.tree.root.data);
		assertEquals("Asserts that 1 is the left child", 1 , tm.tree.root.left.data);
		assertEquals("Asserts that 25 is the right child", 25 , tm.tree.root.right.data);
		assertEquals("Asserts that 19 is the right childs left child", 19 , tm.tree.root.right.left.data);
		assertEquals("Asserts that 57 is the left childs right child", 57 , tm.tree.root.right.right.data);
		assertEquals("Asserts that 10 is the left child right child", 10 , tm.tree.root.left.right.data);
		tm.tree.insertNode(20);
		tm.tree.insertNode(21);
		tm.tree.insertNode(12);
		tm.tree.insertNode(15);
		tm.tree.insertNode(14);
		assertEquals("Asserts that 17 is the root", 17 , tm.tree.root.data);
		assertEquals("Asserts that 10 is the left child", 10 , tm.tree.root.left.data);
		assertEquals("Asserts that 25 is the right child", 25 , tm.tree.root.right.data);
		assertEquals("Asserts that 1 is root->left->left", 1 , tm.tree.root.left.left.data);
		assertEquals("Asserts that 14 is root->left->right child", 14 , tm.tree.root.left.right.data);
		assertEquals("Asserts that 12 is root->left->right->left child", 12 , tm.tree.root.left.right.left.data);
		assertEquals("Asserts that 15 is root->left->right->right child", 15 , tm.tree.root.left.right.right.data);
		
		assertEquals("Asserts that 57 is root->right->right", 57 , tm.tree.root.right.right.data);
		assertEquals("Asserts that 20 is root->right->left child", 20 , tm.tree.root.right.left.data);
		assertEquals("Asserts that 19 is root->right->left->left child", 19 , tm.tree.root.right.left.left.data);
		assertEquals("Asserts that 21 is root->right->left->right child", 21 , tm.tree.root.right.left.right.data);
	}
	
	@Test
	public void testDeleteIntegration(){
		tm.createTree(false);
		tm.tree.insertNode(20);
		tm.tree.insertNode(15);
		tm.tree.insertNode(14);
		tm.tree.insertNode(26);
		assertTrue("Node 20 is in the tree", tm.tree.searchValueExists(20));		
		tm.tree.delete(20);
		assertFalse("Node 20 is in the tree",tm.tree.searchValueExists(20));
		
		//Deleting the one child case
		tm.tree.insertNode(20);
		tm.tree.insertNode(15);
		assertTrue("Node 15 is in the tree", tm.tree.searchValueExists(15));
		tm.tree.delete(15);
		assertFalse("Node 15 is in the tree", tm.tree.searchValueExists(15));
		tm.tree.delete(20);
		
		//Deleting a node
		tm.tree.insertNode(1);
		tm.tree.insertNode(2);
		tm.tree.insertNode(3);
		tm.tree.insertNode(4);
		tm.tree.insertNode(5);
	
		assertTrue("Node 3 is in the tree.", tm.tree.searchValueExists(3));
		tm.tree.delete(3);
		assertFalse("Node 3 is removed from the tree.", tm.tree.searchValueExists(3));
		
		assertFalse("Node 6 doesnt exist.", tm.tree.searchValueExists(6));
		tm.tree.delete(6);
		
		tm.tree.root = null;
		tm.tree.deleteNode(tm.tree.root,0);tm.tree.insertNode(20);
		tm.tree.insertNode(15);
		tm.tree.insertNode(14);
		tm.tree.insertNode(26);
		assertTrue("Node 20 is in the tree", tm.tree.searchValueExists(20));		
		tm.tree.delete(20);
		assertFalse("Node 20 is in the tree", tm.tree.searchValueExists(20));
		
		//Deleting the one child case
		tm.tree.insertNode(20);
		tm.tree.insertNode(15);
		assertTrue("Node 15 is in the tree", tm.tree.searchValueExists(15));
		tm.tree.delete(15);
		assertFalse("Node 15 is in the tree", tm.tree.searchValueExists(15));
		tm.tree.delete(20);
		
		//Deleting a node
		tm.tree.insertNode(1);
		tm.tree.insertNode(2);
		tm.tree.insertNode(3);
		tm.tree.insertNode(4);
		tm.tree.insertNode(5);
	
		assertTrue("Node 3 is in the tree.", tm.tree.searchValueExists(3));
		tm.tree.delete(3);
		assertFalse("Node 3 is removed from the tree.", tm.tree.searchValueExists(3));
		
		assertFalse("Node 6 doesnt exist.", tm.tree.searchValueExists(6));
		tm.tree.delete(6);
		
		tm.tree.root = null;
		tm.tree.deleteNode(tm.tree.root,0);
	}
	
	@Test 
	public void testisEmptyIntegration(){
		tm.createTree(false);		
		assertTrue("Checking that the tree is empty and returns null.",tm.tree.isEmpty());
		tm.tree.insertNode(10);
		assertFalse("Checking that there is a root and its not null",tm.tree.isEmpty());
	}
	
	@Test
	public void testcheckBalanceIntegration(){
		tm.createTree(false);		
		tm.tree.insertNode(1);
		assertEquals("Testing the hight of root", 1, tm.tree.findHeight(tm.tree.root));
		tm.tree.insertNode(6);
		tm.tree.insertNode(0);
		tm.tree.insertNode(4);
		tm.tree.insertNode(5);
		assertEquals("Testing the hight of 6", 0, tm.tree.getDifference(tm.tree.root.right.right));
		assertEquals("Testing the hight of 0", 0, tm.tree.getDifference(tm.tree.root.left));
		assertEquals("Testing the hight of 4", 0, tm.tree.getDifference(tm.tree.root.right.left));
		assertEquals("Testing the hight of 5", 0, tm.tree.getDifference(tm.tree.root.right));
	}
	@Test
	public void testSerializeTree(){
		tm.createTree(false);
		StringWriter out = new StringWriter();
		tm.tree.insertNode(30);
		tm.tree.insertNode(10);
		tm.tree.insertNode(5);
		tm.serializeTree(tm.tree.root, out);
		String s = tm.serialize();
		Assert.assertNotNull(tm.tree);
		Assert.assertEquals("Strings are the same: ", out.toString(), "10 5 $ $ 30 $ $ ");
		Assert.assertEquals("Strings are the same: ", s, "10 5 $ $ 30 $ $ ");
		
		
	}
	
	@Test
	public void testDeSerializeTree(){
		tm.createTree(false);
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
}
