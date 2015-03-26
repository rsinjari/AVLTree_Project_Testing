package Group_Project.AVLTree;

import java.util.ArrayList;
import java.util.List;

	
public class AVLTree {
	
	AVLNode root;
	AVLNode searchedNode;
	
	//Inserts the specified node in the tree
	public void insertNode(int data){
		root = insert(data, root);
		updateParents(this.root);
	}
	//Updates the parents needed for the deleteion function
	public void updateParents(AVLNode Node){
		
		if(Node == null){
			return;
		}else{
			if(Node.left != null){
				Node.left.parent = Node;
			}
			if(Node.right != null){
				Node.right.parent = Node;
			}
			updateParents(Node.left);
			updateParents(Node.right);
		}
		
	}
	//Traverses the tree to insert nodes at the end while keeping balance
	private AVLNode insert(int data, AVLNode Node){
		if(Node == null){//if the node is the first node, it is root
			return new AVLNode(null,null,data);			
		}
		else if(data < Node.data){
			Node.left = insert(data,Node.left);
			Node = balanceTree(Node);
		}
		else if(data > Node.data){
			Node.right = insert(data,Node.right);
			Node = balanceTree(Node);
		}
         return Node;
	}
	    
    //Get difference in balance function
    int getDifference (AVLNode Node){
    	 
    	return findHeight(Node.left)-findHeight(Node.right);
    	
    }
    
	//finds the height of the specified Node
	public int findHeight(AVLNode Node){
		int height = 0;
		
		if(Node != null){
			int leftH = findHeight(Node.left);
			int rightH = findHeight(Node.right);			
			if(leftH > rightH){
				height = leftH + 1;
			}else{
				height = rightH + 1;
			}
		}
		
		return height;
	}
	//Right Right rotation Case
	AVLNode rrCase(AVLNode Node){
		
		AVLNode childR = Node.right;
		Node.right = childR.left;
		childR.left = Node;		
		return childR;
		
	}
	
	//Left Left roation case
	AVLNode llCase(AVLNode Node){
		
		AVLNode childL = Node.left;
		Node.left = childL.right;
		childL.right = Node;
		return childL;
	}
		
	//Right Left rotation Case
	AVLNode rlCase(AVLNode Node){
		
		AVLNode childL = Node.right;
		childL=Node.right;
		Node.right = llCase(childL);
		return rrCase(Node);
	}
	
	//Left Left rotation Case
	AVLNode lrCase(AVLNode Node){
		
		AVLNode childL = Node.left;
		childL = Node.left;
		Node.left = rrCase(childL);
		return llCase(Node);
	}
	//deletes the specified node from the tree
	void deleteNode(int data){
		if(searchValueExists(data)){
			if(searchedNode.left == null && searchedNode.right == null){
				searchedNode.parent = null;
			}
		}
	}
	
	void delete(AVLNode Node){
		//TODO: 
	}
	//checks the balance of the tree
	int checkBalance(AVLNode Node ){
		return findHeight( Node.left ) - findHeight( Node.right );
	}
	//balances the tree when its not balanced
	AVLNode balanceTree(AVLNode Node){
		if(getDifference(Node) > 1){
			if(getDifference(Node.left)>0){
				Node = llCase(Node);
			}
			else{
				Node = lrCase(Node);
			}
		}
		else if(getDifference(Node)<-1){
			if(getDifference(Node.right)>0){
				Node = rlCase(Node);
			}
			else{
				Node = rrCase(Node);
			}
		}
		return Node;
	}
	//Checks if the tree is empty
	boolean isEmpty(){
		
		return root == null;
	}
	
	//Searching for a specific Node by value
	boolean searchValueExists(int i){	
		this.searchedNode = search(this.root, i);
		if(this.searchedNode != null){
			return true;
		}
		return false;
	}
	
	AVLNode searchValue(int i){	
		AVLNode n = search(this.root, i);
		if(n != null){
			return n;
		}
		return null;
	}
	
	AVLNode search(AVLNode r, int i){
		if(r.data == i){
			return r;
		}else if(r.data < i && r.right!=null){
			return search(r.right, i);
		}else if(r.data > i && r.left!=null){
			return search(r.left, i);
		}
		return null;
	}
	
	ArrayList<AVLNode> searchRange(int low, int high){
		ArrayList<AVLNode> nodeList = new ArrayList<AVLNode>();
		AVLNode n = null;
		for(int i = low; i <= high; i++){
			n = searchValue(i);
			if(n != null){
				nodeList.add(n);
			}
		}
		return nodeList;
	}
	
	
}
