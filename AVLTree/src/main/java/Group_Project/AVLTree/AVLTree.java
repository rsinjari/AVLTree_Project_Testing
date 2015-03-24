package Group_Project.AVLTree;
	
public class AVLTree {
	
	AVLNode root;
	private AVLNode searchedNode;
	
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
	void deleteNode(AVLNode Node){
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
	boolean searchValue(int i){
		
		boolean found = false;		
		found = search(root,i);
		if(!found){
			searchedNode = null;
		}
		return found;
	}
	
	boolean search(AVLNode r, int i){
		boolean found = false;
		
		while((r != null) && !found){
			int data  = r.data;
			if(i < data){
				r = r.left;
			}
			else if(i > data){
				r = r.right;
			}
			else{
				found = true;
				searchedNode = r;
			}
			found = search(r,i);
		}
		return found;
	}
}
