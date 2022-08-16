import java.util.*;
import java.io.*;

public class KruskalMST {
	public static void main(String[] args) {
		ArrayList<Edge> graphEdges = new ArrayList<Edge>();
		graphEdges.add(new Edge(4,1,1));
		graphEdges.add(new Edge(1,2,2));
		graphEdges.add(new Edge(4,2,3));
		graphEdges.add(new Edge(3,2,3));
		graphEdges.add(new Edge(5,1,4));
		graphEdges.add(new Edge(4,3,5));
		graphEdges.add(new Edge(2,6,7));
		graphEdges.add(new Edge(3,6,8));
		graphEdges.add(new Edge(5,4,9));
		int nodeCount = 8;		
		KruskalMST graph = new KruskalMST();
		graph.KrushkalMSTMST(graphEdges, nodeCount);
	}
	public void KrushkalMSTMST(ArrayList<Edge> graphEdges, int nodeCount){
		String outputMessage="";
		Collections.sort(graphEdges);		
		ArrayList<Edge> mstEdges = new ArrayList<Edge>();	
		DisjointSet nodeSet = new DisjointSet(nodeCount+1);		
		for(int i=0; i<graphEdges.size() && mstEdges.size()<(nodeCount-1); i++){		
			Edge currentEdge = graphEdges.get(i);
			int root1 = nodeSet.find(currentEdge.getVertex1());		
			int root2 = nodeSet.find(currentEdge.getVertex2());
			outputMessage+="Find ("+currentEdge.getVertex1()+") Returns "+root1+", Find ("+currentEdge.getVertex2()+") Returns "+root2;		
			String unionMessage=",\t:No Union Performed\n";		
			if(root1 != root2){			
				mstEdges.add(currentEdge);		
				nodeSet.union(root1, root2);	
				unionMessage=",\t:Union("+root1+", "+root2+") Done\n";		
			}
			outputMessage+=unionMessage;
		}
		outputMessage+="\nFinal Minimum Spanning Tree ("+mstEdges.size()+" Edges):\n";
		int mstTotalEdgeWeight=0;
		for(Edge edge: mstEdges){
			outputMessage+=edge +"\n";	
			mstTotalEdgeWeight += edge.getWeight();
		}
		outputMessage+="\nTotal weight of all edges in MST = "+mstTotalEdgeWeight;

		System.out.println(outputMessage);
	}
}
class Edge implements Comparable<Edge>{
	private int vertex1;
	private int vertex2;
	private int weight;

	public Edge(int vertex1, int vertex2, int weight){
		this.vertex1=vertex1;
		this.vertex2=vertex2;
		this.weight=weight;
	}
	public int getVertex1(){
		return vertex1;
	}
	public int getVertex2(){
		return vertex2;
	}
	public int getWeight(){
		return weight;
	}
	@Override
	public int compareTo(Edge otherEdge) {				
		return this.getWeight() - otherEdge.getWeight();
	}
	@Override
	public String toString() {
		return "\nEdge ("+getVertex1()+", "+getVertex2()+") Weight = "+getWeight();
	}
}
class DisjointSet{
	private int[] set;	

	public int[] getSet(){		
		return set;
	}
	public DisjointSet(int numElements) {		
		set = new int [numElements];
		for(int i = 0; i < set.length; i++){	
			set[i] = -1;
		}
	}
	public void union(int root1, int root2) {
		if(set[root2] < set[root1]){		
			set[root1] = root2;		
		}
		else {
			if(set[root1] == set[root2]){
				set[root1]--;		
			}
			set[root2] = root1;		
		}
	}
	public int find(int x) {
		if(set[x] < 0){		
			return x;
		}
		int next = x;		
		while(set[next] > 0){		
			next=set[next];
		}
		return next;
	}	
}