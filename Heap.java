
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

	//WORKS
	public void buildHeap(ArrayList<Node> nodes) {
		//array from 0 to n-1 for n size array
		//i = middle array to 0
		for(int i=(nodes.size()-2)/2; i>=0;i--){
			minHeapify(nodes,i);
			//minheapify
		}
	}
	//WORKS
	public void minHeapify(ArrayList<Node> nodes, int pos){
		minHeap = nodes;
		//minheap has all nodes, make these in array order now.
		//pos = actual pos in array
		// add everything to minheap in heap order. Don't edit nodes!
		//heapify down. keep moving large values down tree.
		while(((pos*2)+2)<=nodes.size()) {
			int minpos = minChildIndex(minHeap,pos);
			Node min = minHeap.get(minpos);
			Node start = minHeap.get(pos);
			if (min.getMinDistance()< start.getMinDistance()) {
				//swap as child < parent
				//Node tmp = start;
				//REVIEW THIS!!!
				minHeap.set(pos,min);
				minHeap.set(minpos,start);
			}
			else if(min.getMinDistance()==start.getMinDistance()){
				//break tie by nodename
				if(min.getNodeName()<start.getNodeName()){
					//swap
					minHeap.set(pos,min);
					minHeap.set(minpos,start);
				}

			}
			else{
				break;
			}
			pos = minpos;
		}
	}
	//WORKS
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
		else if(r.getMinDistance()<l.getMinDistance()){
			return rpos;
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

	//Works!!!
	// might need to pass in the heap too! Check later
	public void maxHeapify(int name, int mindis){
		//simplify to O(1) by adding a field in node class for each nodes index in minheap

		for (int i=0; i<minHeap.size();i++){
			if (minHeap.get(i).getNodeName()== name && minHeap.get(i).getMinDistance()== mindis){
				int index_In = i-1;
				boolean flag = true;
				int parent = index_In/2;

				while(minHeap.get(parent)!=null) {
					if(index_In==parent|| index_In<=0){
						//they're both at root node.
						return;
					}
					if (flag){
						if (minHeap.get(parent).getMinDistance()>minHeap.get(i).getMinDistance()){
							Node tmp = minHeap.get(parent);
							Node tmp2 = minHeap.get(i);
							minHeap.set(parent,tmp2);
							minHeap.set(i,tmp);
						}
					flag = false;

					}

					else if (minHeap.get(parent).getMinDistance() > minHeap.get(index_In).getMinDistance()) {
						//swap;
						Node tmp = minHeap.get(parent);
						Node tmp2 = minHeap.get(index_In);
						minHeap.set(parent, tmp2);
						minHeap.set(index_In,tmp);

					}
					else{
						return;
					}
					index_In = parent;
					parent =  (index_In-1)/2;
				}
			}
		}



	}

  	// insertNode
	// Insert a Node into the heap
  	//
 	 // Time Complexity Requirement: theta(log(n))
	//works
	public void insertNode(Node in) {
		// add to end of heap.

		//left child = 2i+1, right child = 2i+2;
		// parent = child-1/2 , where child = index of child

		minHeap.add(in);
		if (minHeap.size()<=1){
			// only root node in rn.
			return;
		}
		int index_In = minHeap.size()-2; //child index -1
		int parent = index_In/2;
		boolean flag= true;
		while(minHeap.get(parent)!=null) {
			if (flag){
				if (minHeap.get(parent).getMinDistance()>minHeap.get(index_In+1).getMinDistance()){
					Node tmp = minHeap.get(parent);
					Node tmp2 = minHeap.get(index_In+1);
					minHeap.set(parent,tmp2);
					minHeap.set(index_In +1,tmp);
				}
				flag = false;
			}
			//recompute index_in
			else if (minHeap.get(parent).getMinDistance() > minHeap.get(index_In).getMinDistance()) {
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
		if (minHeap.size()>0) {
			return (minHeap.get(0));
		}
		else{
			return null;
		}

	}
	//WORKS
  // extractMin
  // use minheapfiy
  // Removes and returns the minimum element of the heap
  //
  // Time Complexity Requirement: theta(log(n))
  public Node extractMin() {
		// get node 0. set node 0 to last node
		// remove last node. run minheapify from there.
		if(minHeap.size()==0){
			return null;
		}
		else if (minHeap.size()==1){
			Node extract = minHeap.remove(0);
			return extract;
		}
		else {
			Node extract = minHeap.get(0);
			int last_index = minHeap.size() - 1;
			Node last = minHeap.get(last_index);
			minHeap.set(0, last);
			minHeap.remove(last_index);
			// check if remove also edits size.
			// heapify down/ minheapify
			minHeapify(minHeap, 0);

			return (extract);
		}
  }

  public int Size(){
		return minHeap.size();
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
