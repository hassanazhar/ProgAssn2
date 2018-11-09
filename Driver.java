
import java.io.File;
import java.util.*;
/*
	This Driver file will be replaced by ours during our grading.
*/
public class Driver {
	private static String filename; // input file name 
	private static boolean testDijkstra; // set to true by -d flag
	private static boolean testHeap; // set to true by -w flag
	private static Graph testGraph; // instance of your graph
	private static ArrayList<Node> allNodeNames;
	private static void usage() { // error message
		System.err.println("usage: java Driver [-d] [-h] <filename>");
		System.err.println("\t-d\tTest Dijkstra implementation");
		System.err.println("\t-h\tTest the heap implementation");
		System.exit(1);
	}
	
  public static void main(String[] args) throws Exception{
		allNodeNames = new ArrayList<Node>();
		//filename = "/Users/melaniefeng/eclipse-workspace/Program2/src/3.txt";
		//parseInputFile(filename); 
    parseArgs(args);
    parseInputFile(filename);
    testRun();
	
	}

	public static void parseArgs(String[] args) {
    boolean flagsPresent = false;
		if (args.length == 0) {
      usage();
    }
    
    filename = "";
    
    testHeap= false;
    for (String s : args) {
      if(s.equals("-d")) {
        flagsPresent = true;
        testDijkstra = true;
      } else if(s.equals("-h")) {
        flagsPresent = true;
        testHeap = true;
      } else if(!s.startsWith("-")) {
        filename = s;
      } else {
        System.err.printf("Unknown option: %s\n", s);
        usage();
      }
    }
    
    if(!flagsPresent) {
        testDijkstra = true;
        testHeap = true;
    }
	}
	public static void parseInputFile(String filename) 
			throws Exception {
		int numV = 0, numE = 0;
        Scanner sc = new Scanner(new File(filename));	
		String[]  inputSize = sc.nextLine().split(" ");
		numV = Integer.parseInt(inputSize[0]);
		numE = Integer.parseInt(inputSize[1]);
		HashMap<Integer, ArrayList<NeighborWeightTuple>> tempNeighbors = new HashMap<>();
		testGraph = new Graph(numV);
		for (int i = 0; i < numV; ++i) {
			
			String[] pairs = sc.nextLine().split(" ");
			String[] weightPairs = sc.nextLine().split(" ");
			
			Integer currNode = Integer.parseInt(pairs[0]);
			Node currentNode = new Node(currNode);
			allNodeNames.add(currNode, currentNode);
			ArrayList<NeighborWeightTuple> currNeighbors = new ArrayList<>();
			tempNeighbors.put(currNode, currNeighbors);
			
			for(int k = 1; k < pairs.length; k++) {
				Integer neighborVal = Integer.parseInt(pairs[k]);
				Integer weightVal = Integer.parseInt(weightPairs[k]);
				currNeighbors.add(new NeighborWeightTuple(neighborVal, weightVal));
			}
		}
		for (int i = 0; i < allNodeNames.size(); ++i)
		{
			Node currNode = allNodeNames.get(i);
			ArrayList<NeighborWeightTuple> neighbors = tempNeighbors.get(i);
			for (NeighborWeightTuple neighbor : neighbors)
			{
				testGraph.setEdge(currNode, allNodeNames.get(neighbor.neighborID), neighbor.weight);
			}
		}
		
		testGraph.setAllNodesArray(allNodeNames);
	}
	
  public static void testRun() {
		if (testDijkstra) {
			System.out.println(testGraph);
		}

		if (testHeap) {
			//push stuff in heap, check that all parents are less than kids.
			Heap minHeap = new Heap();
			System.out.println("HELLO");
			Node x = new Node(0);
			Node y = new Node(5);
			Node z = new Node (2);
			x.setMinDistance(10);
			y.setMinDistance(3);
			z.setMinDistance(3);
			minHeap.insertNode(x);
			minHeap.insertNode(y);
			minHeap.insertNode(z);
			System.out.println(minHeap);
			// original
			System.out.println(testGraph.getHeap());
		}
	}

	private static class NeighborWeightTuple {
		public Integer neighborID;
		public Integer weight;

		NeighborWeightTuple(Integer neighborID, Integer weight)
		{
			this.neighborID = neighborID;
			this.weight = weight;
		}
	}

}
