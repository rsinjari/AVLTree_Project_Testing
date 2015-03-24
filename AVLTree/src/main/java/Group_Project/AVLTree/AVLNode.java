package Group_Project.AVLTree;

/*
 * AVLNode class
 */

public class AVLNode {

	public AVLNode left;
	public AVLNode right;

	public int data;
	public int height;
	
	public AVLNode(){
		
	}
	public AVLNode(int data){
		this.data = data;
	}
	public AVLNode(AVLNode left, AVLNode right, int data){
		this.left= left;
		this.right = right;
		this.data = data;
	}
}
