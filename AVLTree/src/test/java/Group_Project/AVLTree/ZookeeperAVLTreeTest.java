package Group_Project.AVLTree;

import org.apache.zookeeper.KeeperException;
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
		t.insertNode(42);
		t.insertNode(27);
		t.insertNode(10);
		
		System.out.println(t.root.location);
		System.out.println(t.root.left);
		System.out.println(t.root.right);
		
		t.inorderPrint(t.root);
		
		//throw new RuntimeException("test method not impleented");
	}
	/*
	@Test
	public void testFindHight(){
		throw new RuntimeException("test method not impleented");
	}
	@Test
	public void testDeleteNode(){
		throw new RuntimeException("test method not impleented");
	}
	@Test
	public void testSearchValue(){
		throw new RuntimeException("test method not impleented");
	}
	@Test
	public void testBalanceTree(){
		throw new RuntimeException("test method not impleented");
	}
	@Test
	public void testCheckBalance(){
		throw new RuntimeException("test method not impleented");
	}
	@Test
	public void testIsEmpty(){
		throw new RuntimeException("test method not impleented");
	}
	*/
}
