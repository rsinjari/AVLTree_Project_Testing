package Group_Project.AVLTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class TreeManager {

	AVLTree tree;
	
	void createTree(){
		this.tree = new AVLTree();
	}
	
	//Takes a stringwriter object and writes the serialized data to the stingwriter stream.
	void serializeTree(AVLNode n, StringWriter out){
		 if (n==null) {
			    out.write("$ ");
			  } else {
			    out.write(Integer.toString(n.data)+" ");
			    serializeTree(n.left, out);
			    serializeTree(n.right, out);
			  }
	}
	
	static int deserializeCounter = 0;
	AVLNode deserializeTree(String[] sTree){
		
		if (deserializeCounter >= sTree.length)
            return null;
        if (sTree[deserializeCounter].equals("$")) {
        	deserializeCounter++;
            return null;
        }

        int data = Integer.parseInt(sTree[deserializeCounter]);
        AVLNode dTree = new AVLNode(data);
        deserializeCounter++;
        dTree.left = deserializeTree(sTree);
        dTree.right = deserializeTree(sTree);
	
		return dTree;
	}
	
	String saveTree(AVLTree tree, String filename){
		String sTree = null;	
		PrintWriter fileOut;
		try {
			fileOut = new PrintWriter(filename);
			StringWriter out = new StringWriter();
			serializeTree(tree.root, out);
			sTree = out.toString();
			fileOut.write(sTree);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sTree;
	}
	
	void loadTree(String filename){
		String s = null;
		File file = new File(filename);
	    try {
	        Scanner sc = new Scanner(file);
	        while (sc.hasNext()) {
	            s = sc.nextLine();
	        }
	        sc.close();
	    } 
	    
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }		
		
	    this.createTree();
	    String delims = "[ ]+";
		String[] sTree = s.split(delims);
	    deserializeCounter = 0;
	    this.tree.root = deserializeTree(sTree);
	    
	}
	
	
	
	
	/*
	
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
	}*/
}