package Group_Project.AVLTree;

import java.io.Serializable;

public class ZookeeperAVLNode implements Serializable {
	//Strings left and right hold location data, locations of Zookeeper nodes are strings which are sent to
	//the zookeeper server
	public String left;
	public String right;
	public int data;
	public String location;
	
	public ZookeeperAVLNode(){
		
	}
	
	public ZookeeperAVLNode(String left, String right, int data){
		this.left= left;
		this.right = right;
		this.data = data;
		location = "/ZookeeperAVLNode"+data;
	}
	
	
	
}
