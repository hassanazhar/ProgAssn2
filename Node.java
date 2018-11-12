

// feel free to add things to this file. Just don't remove anything

import java.util.*;
public class Node {
	
    private int minDistance;
	private int nodeName;
	private ArrayList<Node> neighbors; 
	private ArrayList<Integer> weights;
	private Node parent;
	private int index_In_Heap;
	
  public Node(int x) {
  		parent = null;
  		index_In_Heap=-1;
		nodeName = x;
		minDistance = Integer.MAX_VALUE;
		neighbors = new ArrayList<Node>();
		weights = new ArrayList<Integer>();
	}
	
  public void setNeighborAndWeight(Node n, Integer w) {
		neighbors.add(n);
		weights.add(w);
	}
	
	public ArrayList<Node> getNeighbors(){
		return neighbors;
	}
	
  public ArrayList<Integer> getWeights(){
		return weights;
	}
	
	public int getMinDistance() {
		return minDistance;
	}
	
	public void setMinDistance(int x) {
		minDistance = x;
	}
	public void setParent(Node x){
  		parent= x;
	}
	public Node getParent(){
  	return parent;
	}
	public void setIndex(int index){
  	index_In_Heap=index;
	}
	public int getIndex(){
  	return index_In_Heap;
	}
	
	public int getNodeName() {
		return nodeName;
	}
}
