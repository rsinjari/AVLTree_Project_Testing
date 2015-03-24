package Group_Project.AVLTree;

/*
 * AVLNode class
 */

public class AVLNode {

	AVLNode left;
	AVLNode right;
	AVLNode parent;
	int data;
	int height;
	
	public AVLNode(){
		
	}
	public AVLNode(int data){
		this.data = data;
	}
	public AVLNode(AVLNode left, AVLNode right, AVLNode parent ,int data){
		this.left=left;
		this.right = right;
		this.parent = parent;
		this.data = data;
	}
}
