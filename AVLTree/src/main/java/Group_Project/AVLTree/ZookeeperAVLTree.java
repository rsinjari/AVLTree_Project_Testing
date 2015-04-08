package Group_Project.AVLTree;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

public class ZookeeperAVLTree {
	
	//Standard Zookeeper Connection.  Only works on localhost server
	static ZooKeeper zooKeeper ;
    static Integer mutex;
    java.util.concurrent.CountDownLatch connectedSignal = new java.util.concurrent.CountDownLatch(1);
    public void connect() throws KeeperException, IOException, InterruptedException {
        zooKeeper = new ZooKeeper("localhost:2181", 10000,
                new Watcher(){
                public void process(WatchedEvent event) {
                    if (event.getState() == KeeperState.SyncConnected) {
                    	connectedSignal.countDown();
                    }
                }          
        });
    }
    public void process(WatchedEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    ZookeeperAVLTree(){
    	try {
			this.connect();
			System.out.println("Connected to localhost");
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	//the root node is on location on the Zookeeper server, it needs to be set by being created on the server and then read.
	ZookeeperAVLNode root = null;
	
	
	
	//Inserts the specified node in the tree
		public void insertNode(int data){
			if(root!=null){
				updateNode(new ZookeeperAVLNode(null,null,data));
			}
			root = insert(data, root);
		}
		
		//Traverses the tree to insert nodes at the end while keeping balance
		private ZookeeperAVLNode insert(int data, ZookeeperAVLNode Node){
			if(Node == null){//if the node is the first node, it is root
				return new ZookeeperAVLNode(null,null,data);			
			}
			else if(data < Node.data){
				Node.left = insert(data, getNodeByAddress(Node.left)).location;
				Node = balanceTree(Node);
				updateNode(Node);
			}
			else if(data > Node.data){
				Node.right = insert(data, getNodeByAddress(Node.right)).location;
				Node = balanceTree(Node);
				updateNode(Node);
			}
			updateNode(Node);
	         return Node;
		}
		    
	    //Get difference in balance function
	    int getDifference (ZookeeperAVLNode Node){
	    	int height = findHeight( getNodeByAddress(Node.left) ) - findHeight( getNodeByAddress(Node.right) );
			System.out.println("Height of"+Node.left+" and "+Node.right+"Height was found to be: "+height);
			return  height;
	    }
	    
		//finds the height of the specified Node
		int findHeight(ZookeeperAVLNode n){
			int height = 0;
			if(n != null){
				int leftH = findHeight(getNodeByAddress(n.left));
				int rightH = findHeight(getNodeByAddress(n.right));			
				if(leftH > rightH){
					height = leftH + 1;
				}else{
					height = rightH + 1;
				}
			}
			//Node.height = height;
			//System.out.println("Height: "+height);
			return height;
		}
		
		//Right Right rotation Case
		ZookeeperAVLNode rrCase(ZookeeperAVLNode Node){
		
			ZookeeperAVLNode childR = getNodeByAddress(Node.right);
			Node.right = childR.left;
			updateNode(Node);
			childR.left = Node.location;
			updateNode(childR);
			return childR;
			
			/*
			AVLNode childR = Node.right;
			Node.right = childR.left;
			childR.left = Node;		
			return childR;
			*/
			
		}
		
		//Left Left rotation case
		ZookeeperAVLNode llCase(ZookeeperAVLNode Node){
			
			ZookeeperAVLNode childL = getNodeByAddress(Node.left);
			Node.left = childL.right;
			updateNode(Node);
			childL.right = Node.location;
			updateNode(childL);
			return childL;
			/*
			AVLNode childL = Node.left;
			Node.left = childL.right;
			childL.right = Node;
			return childL;
			*/
		}
			
		//Right Left rotation Case
		ZookeeperAVLNode rlCase(ZookeeperAVLNode Node){
			
			ZookeeperAVLNode childL = getNodeByAddress(Node.right);
			updateNode(childL);
			childL = getNodeByAddress(Node.right);
			updateNode(childL);
			Node.right = llCase(childL).location;
			updateNode(Node);
			return rrCase(Node);
			
			
			/*AVLNode childL = Node.right;
			childL=Node.right;
			Node.right = llCase(childL);
			return rrCase(Node);*/
		}
		
		//Left Left rotation Case
		ZookeeperAVLNode lrCase(ZookeeperAVLNode Node){
			
			ZookeeperAVLNode childL = getNodeByAddress(Node.left);
			updateNode(childL);
			childL = getNodeByAddress(Node.left);
			updateNode(childL);
			Node.left = rrCase(childL).location;
			updateNode(Node);
			return llCase(Node);
			
			/*AVLNode childL = Node.left;
			childL = Node.left;
			Node.left = rrCase(childL);
			return llCase(Node);*/
		}
	
		ZookeeperAVLNode balanceTree(ZookeeperAVLNode Node){
		if(getDifference(Node) > 1){
			if( getDifference(getNodeByAddress(Node.left)) > 0 ){
				Node = llCase(Node);
				updateNode(Node);
			}
			else{
				Node = lrCase(Node);
				updateNode(Node);
			}
		}
		else if(getDifference(Node)<-1){
			if( getDifference(getNodeByAddress(Node.right)) > 0 ) {
				Node = rlCase(Node);
				updateNode(Node);
			}
			else{
				Node = rrCase(Node);
				updateNode(Node);
			}
		}
		//updateNode(Node);
		return Node;
	}
	//updates node location with new information if it already existed in the database
	void updateNode(ZookeeperAVLNode node){
		Stat stat;
		try {
			 stat = zooKeeper.exists(node.location, false);
			 if(stat != null){ // if the node exists in the tree
				 zooKeeper.setData(node.location,serializeNode(node), stat.getVersion());
				 //zooKeeper.create(node.location, serializeNode(node), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				 //System.out.println("Exists");
			 }else{
				 zooKeeper.create(node.location, serializeNode(node), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			 }
			 //Thread.sleep(2000);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	ZookeeperAVLNode getNodeByAddress(String location){
		if(location == null){
			return null;
		}
		try {
			Stat stat = zooKeeper.exists(location, false);;
			 if(stat != null){ // if the node exists in the tree
				 return deserializeNode(zooKeeper.getData(location, false, stat));
			 }
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//for writing the objects to zookeeper server for storage
	byte[] serializeNode(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }

	//for retrieving objects from zookeeper server
    ZookeeperAVLNode deserializeNode(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return (ZookeeperAVLNode)o.readObject();
    }
	
  //Debugging prints
  	void inorderPrint(ZookeeperAVLNode n){	
  		if(n == null){
  			return;
  		}else{
  			inorderPrint(getNodeByAddress(n.left));
  			System.out.print(n.data+" ");
  			inorderPrint(getNodeByAddress(n.right));
  		}
  	}
	
}
