package Group_Project.AVLTree;

import java.util.ArrayList;
import java.util.List;

	
public class AVLTree {
	
	AVLNode root;
	AVLNode searchedNode;
	
	//Inserts the specified node in the tree
	public void insertNode(int data){
		root = insert(data, root);
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
	int findHeight(AVLNode Node){
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
		//Node.height = height;
		return height;
	}
	
	//Right Right rotation Case
	AVLNode rrCase(AVLNode Node){
	
		AVLNode childR = Node.right;
		Node.right = childR.left;
		childR.left = Node;		
		return childR;
		
	}
	
	//Left Left rotation case
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
	
	//Delete node with data if it exists
	public boolean delete(int data){
		AVLNode deleteMe = searchValue(data);
		if( deleteMe != null ){
			deleteNode(this.root, data);
			return true;
		}
		return false;
	}
	
	//deletes the specified node from the tree
	AVLNode deleteNode(AVLNode root, int data){
	    if (root == null){
	        return root;
	    }
	   
	    //Slightly different than the already implemented search for node function
	    if ( data < root.data ){
	        root.left = deleteNode(root.left, data);
	    }
	    else if( data > root.data ){
	        root.right = deleteNode(root.right, data);
	    }else{
	        // node with only one child or no child
	        if( (root.left == null) || (root.right == null) ){
	            AVLNode temp = root.left!=null ? root.left : root.right;
	            // No child case
	            if(temp == null){
	                temp = root;
	                root = null;
	            }else{ // One child case
	             root = temp; 
	            }
	        }else{
	            // node with two children: Get the inorder successor (smallest
	            // in the right subtree)
	        	AVLNode minNode = root.right;
	        	while (minNode.left != null){
	                minNode = minNode.left;
	        	}
	        	AVLNode temp = minNode;
	            // Copy the inorder successor's data to this node
	            root.data = temp.data;
	            // Delete the inorder successor
	            root.right = deleteNode(root.right, temp.data);
	        }
	 
	    // If the tree had only one node then return
	    if (root == null){
	      return root;
	    }
	    checkBalance(root);
	    }
	    return root;
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
