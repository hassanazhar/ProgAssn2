
import java.awt.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class Graph {
    //vertices is adjacency list
	private ArrayList<Node> vertices; //this is a list of all vertices, populated by Driver class. this has all the correct values
	private Heap minHeap; 	//this is the heap to use for Dijkstra
	public Graph(int numVertices) {
		minHeap = new Heap();
		vertices = new ArrayList<Node>();
    // feel free to add anything else you may want	
	}

  // findShortestPathLength
  //
  // Returns the distance of the shortest path from root to x
  public int findShortestPathLength(Node root, Node x) {
	    //create minheap from vertices.
        //
      ArrayList<Node> ret = findAShortestPath(root,x);
      return (x.getMinDistance());
  }

  // findAShortestPath
  //
  // Returns a list of nodes represent one of the shortest paths
  // from root to x
  public ArrayList<Node> findAShortestPath(Node root, Node x){
	    //have parent
      // keep rebuilding minheep each time given a different root and x to find
      ArrayList<Node> retlist = findEveryShortestPathLength(root);
      ArrayList<Node> ret = new ArrayList<>();
      if (x== root){
          return null;
      }
      Node child = x;
      Node par  = child.getParent();
      while(par != null){
            ret.add(child);
            child = par;
            par = child.getParent();
            if(par==null){
                if (child !=root){
                    return null;
                }
                else{
                    ret.add(child);
                    Collections.reverse(ret);
                    return ret;
                }
            }
      }
      Collections.reverse(ret);
	  return ret;
  }

  // eachShortestPathLength
  //
  // Returns an ArrayList of Nodes, where minDistance of each node is the
  // length of the shortest path from it to the root. -1 if unreach. This ArrayList
  // should contain every Node in the graph. Note that 
  // root.getMinDistance() = 0
  public ArrayList<Node> findEveryShortestPathLength(Node root) {
	    for(Node i : vertices){
	        i.setMinDistance(Integer.MAX_VALUE);
	        i.setParent(null);
        }
        root.setMinDistance(0); // this will update it in vertices too
        ArrayList<Node> localvert=new ArrayList<>(vertices.size());
        localvert.addAll(vertices);
        // when find new mindistance, heapify up.

        ArrayList<Node> returnMinList = new ArrayList<>();

        //returnlist at end has sorted by name of vertices
	    minHeap.buildHeap(localvert);
        while(minHeap.Size()>0){
            Node v = minHeap.extractMin(); //= root at first
            for (int i=0; i<v.getNeighbors().size();i++){
                //use cut property. dont' consider nodes already in returnminlist
                Node u = v.getNeighbors().get(i);
                if (returnMinList.contains(u)){
                    continue;
                }
                List<Integer> weights = v.getWeights();
                if (u.getMinDistance()>(v.getMinDistance()+ weights.get(i))){
                    // maybe issue accessing weights
                    u.setMinDistance(v.getMinDistance()+weights.get(i));
                    u.setParent(v);
                    //heapify up.
                    // can pass in nodename
                    int name = u.getNodeName();
                    int mindist = u.getMinDistance();
                    minHeap.maxHeapify(name,mindist); //heapify node u up. O(n)
                }
            }
            returnMinList.add(v);
        }
        // unreachable nodes
        for (Node i : returnMinList){
            if (i.getMinDistance()== Integer.MAX_VALUE){
                i.setMinDistance(-1);

            }
        }
        return returnMinList;


  }
  
  //returns edges and weights in a string.
  public String toString() {
    String o = "";
    for(Node v: vertices) {
      boolean first = true;
      o += "Node ";
      o += v.getNodeName();
      o += " has neighbors: ";
      ArrayList<Node> ngbr = v.getNeighbors();
      for(Node n : ngbr) {
        o += first ? n.getNodeName() : ", " + n.getNodeName();
        first = false;
      }
      first = true;
      o += " with weights ";
      ArrayList<Integer> wght= v.getWeights();
      for (Integer i : wght) {
        o += first ? i : ", " + i;
        first = false;
      }		
      o += System.getProperty("line.separator");
    
    }

    return o;
  }
  
///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

  public Heap getHeap() {

	    return minHeap;
  }
    
  public ArrayList<Node> getAllNodes(){
    return vertices;
  }
     
	//used by Driver class to populate each Node with correct neighbors and corresponding weights
	public void setEdge(Node curr, Node neighbor, Integer weight) {
		curr.setNeighborAndWeight(neighbor, weight);
	}
    //This is used by Driver.java and sets vertices to reference an ArrayList of all nodes.
  public void setAllNodesArray(ArrayList<Node> x) {
    vertices = x;	
  }    
}
