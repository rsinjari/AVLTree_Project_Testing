package Group_Project.AVLTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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
	
	AVLTree deserializeTree(String tree){
		AVLTree dTree = null;
		//TODO: Implement Deserialization
		
		
		//If tree returns as null, failed to deserialize
		return dTree;
	}
	
	void saveTree(AVLTree tree, String filename) throws FileNotFoundException{
		String selializedTree = serializeTree(tree);		
		PrintWriter out = new PrintWriter(filename);
		out.println(selializedTree);
		out.close();		
	}
	
	AVLTree loadTree(AVLTree tree, String filename){
		String a = null;
		
		File file = new File(filename);
	    try {
	        Scanner sc = new Scanner(file);
	        while (sc.hasNext()) {
	            a = sc.nextLine();
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }		
		return deserializeTree(a);
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