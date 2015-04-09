package Group_Project.AVLTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class TreeManager {

	AVLTree tree = null;
	ZookeeperAVLTree zootree = null;
	static boolean isZookeeper = false;
	
	void createTree(boolean isZookeeper){
		this.isZookeeper = isZookeeper;
		if(isZookeeper){
			this.zootree = new ZookeeperAVLTree();			
		}
		else{
			this.tree = new AVLTree();
		}		
	}
	
	String serialize(){
		StringWriter out = new StringWriter();
		String s;
		if(isZookeeper){
			serializeZooTree( zootree.root, out);
		}else{
			serializeTree(tree.root, out);
		}
		return s = out.toString();		
	}
	
	//Takes a string writer object and writes the serialized data to the stingwriter stream.
	void serializeTree(AVLNode root, StringWriter out){
		 if (root==null) {
			    out.write("$ ");
			  } else {
			    out.write(Integer.toString(root.data)+" ");
			    serializeTree(root.left, out);
			    serializeTree(root.right, out);
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
	
	
	void serializeZooTree(ZookeeperAVLNode root, StringWriter out){
		 if (root==null) {
			    out.write("$ ");
			  } else {
			    out.write(Integer.toString(root.data)+" ");
			    serializeZooTree(zootree.getNodeByAddress(root.left), out);
			    serializeZooTree(zootree.getNodeByAddress(root.right), out);
			  }
	}

	/*String saveTree(AVLTree tree, String filename){
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