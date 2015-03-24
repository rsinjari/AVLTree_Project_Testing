package Group_Project.AVLTree;

public class TreeManager {

	AVLTree tree;
	
	void createTree(){
		this.tree = new AVLTree();
	}
	
	String serializeTree(AVLTree t){
		String sTree = null;
		if(t == null){
			return sTree;
		}
		//TODO: Implement Serialization
		
		return sTree;
	}
	
	AVLTree deserializeTree(String serializedTree){
		AVLTree dTree = null;
		//TODO: Implement Deserialization
		
		
		//If tree returns as null, failed to deserialize
		return dTree;
	}
	
	void saveTree(){
		//TODO: NOT SURE HOW WE ARE GOING TO SAVE THE TREES YET. 
		//PROBABLY TEXT FILE
	}
	
	void loadTree(AVLTree t){
		if(t == null){
			return;
		}
		this.tree = t;
	}
	
	
	//Debugging prints
	void inorderPrint(AVLNode n){	
		if(n == null){
			return;
		}else{
			inorderPrint(n.left);
			System.out.print(n.data+" ");
			inorderPrint(n.right);
		}
	}
	
	void preorderPrint(AVLNode n){	
		if(n == null){
			return;
		}else{
			System.out.print(n.data+" ");
			preorderPrint(n.left);
			preorderPrint(n.right);
		}
	}
	
	void postorderPrint(AVLNode n){	
		if(n == null){
			return;
		}else{
			postorderPrint(n.left);
			postorderPrint(n.right);
			System.out.print(n.data+" ");
		}
	}
}