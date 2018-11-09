
import java.util.ArrayList;


public class Heap {

	private ArrayList<Node> minHeap; // do not remove

	public Heap() {
		minHeap = new ArrayList<Node>(); // do not remove
	}
	// buildHeap
	//
	// Given an ArrayList of Nodes, build a minimum heap keyed on each
	// Node's minDistance
	//
	// Time Complexity Requirement: theta(n)
	public void buildHeap(ArrayList<Node> nodes) {
		//array from 0 to n-1 for n size array
		//i = middle array to 0
		for(int i=(nodes.size()-2)/2; i>=0;i--){
			minHeapify(nodes,i);
			//minheapify
		}
	}
	public void minHeapify(ArrayList<Node> nodes, int pos){
		//pos = actual pos in array
		//heapify down. keep moving large values down tree.
		//might need to change to +1
		while(((pos*2)+2)<=nodes.size()) {
			int minpos = minChildIndex(nodes,pos);
			Node min = nodes.get(minpos);
			Node start = nodes.get(pos);
			if (min.getMinDistance()< start.getMinDistance()) {
				//swap as child < parent
				//Node tmp = start;
				//REVIEW THIS!!!
				nodes.set(pos,min);
				nodes.set(minpos,start);
			}
			else if(min.getMinDistance()==start.getMinDistance()){
				//break tie by nodename
				if(min.getNodeName()<start.getNodeName()){
					//swap
					nodes.set(pos,min);
					nodes.set(minpos,start);
				}

			}
			else{
				break;
			}
			pos = minpos;
		}
	}
	private int minChildIndex(ArrayList<Node>nodes, int pos){
		int rpos = (pos*2)+2;
		if (rpos>=nodes.size()){
			// right child doesn't exist. return left child.
			int lpos = (pos*2)+1;
			return (lpos);
		}
		int lpos = pos*2+1;
		Node l = nodes.get(lpos);
		Node r = nodes.get(rpos);
		if(l.getMinDistance()<r.getMinDistance()){
			return lpos;
		}
		else if(l.getMinDistance()==r.getMinDistance()){
			if (l.getNodeName()<r.getNodeName()){
				return lpos;
			}
			else{
				return rpos;
			}
		}
		return rpos;
	}




  // insertNode
  //
  // Insert a Node into the heap
  //
  // Time Complexity Requirement: theta(log(n))
	public void insertNode(Node in) {
		// add to end of heap.

		//left child = 2i+1, right child = 2i+2;
		// parent = child-1/2 , where child = index of child
		minHeap.add(in);
		int index_In = minHeap.size()-2; //child index -1
		int parent = index_In/2;
		while(minHeap.get(parent)!=null) {
			if (minHeap.get(parent).getMinDistance() > minHeap.get(index_In).getMinDistance()) {
				//swap;
				Node tmp = minHeap.get(parent);
				Node tmp2 = minHeap.get(index_In);
				minHeap.set(parent, tmp2);
				minHeap.set(index_In,tmp);


			}
			else{
				break;
			}
			index_In = parent;
			parent =  (index_In-1)/2;
		}
		//heapify up

	
	}
	
  // findMin
  //
  // Returns the minimum element of the heap
  //
  // Time Complexity Requirement: theta(1)
	public Node findMin() {
		return(minHeap.get(0));
	}

  // extractMin
  //
  // Removes and returns the minimum element of the heap
  //
  // Time Complexity Requirement: theta(log(n))
	public Node extractMin() {
		// get node 0. set node 0 to last node
		// remove last node. run minheapify from there.
		Node extract = minHeap.get(0);
		int last_index = minHeap.size()-1;
		Node last = minHeap.get(last_index);
		minHeap.set(0,last);
		minHeap.remove(last_index);
		// check if remove also edits size.
		// heapify down/ minheapify
		minHeapify(minHeap,0);

		return (extract);
  }
	
  public String toString() {
		String output = "";
		for(int i = 0; i < minHeap.size(); i++) {
			output+= minHeap.get(i).getNodeName() + " ";
		}
		return output;
	}
	
///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

	public ArrayList<Node> toArrayList() {
		return minHeap;
	}
}
