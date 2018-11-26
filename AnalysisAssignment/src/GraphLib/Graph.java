package GraphLib;

import java.util.ArrayList;
import java.util.Vector;

public class Graph {
	protected ArrayList<Vertex> vertices; 
	protected ArrayList<Edge> edges; 

	public String getLibraryName(){
		return this.getClass().getPackage().getName();
	}
	public String getLibraryVersion(){
		return this.getClass().getPackage().getSpecificationVersion();
	}
	// the following method adds a vertex to the graph
	public void insertVertex(StringBuffer strUniqueID,StringBuffer strData,int nX,int nY) throws GraphException {
		Vertex vertex = new Vertex();
		vertex._strUniqueID = strUniqueID;
		vertex._strData = strData;
		vertex._nX = nX;
		vertex._nY = nY;
		this.vertices.add(vertex);
	}
	// inserts an edge between 2 specified vertices
	public void insertEdge(StringBuffer strVertex1UniqueID,
			 StringBuffer strVertex2UniqueID,
			 StringBuffer strEdgeUniqueID,
			 StringBuffer strEdgeData,
			 int nEdgeCost) throws GraphException {
	Edge edge = new Edge();
	edge._strData = strEdgeData;
	edge._strUniqueID = strEdgeUniqueID;
	edge._nEdgeCost = nEdgeCost;
	edge.Start._strUniqueID=strVertex1UniqueID;
	edge.End._strUniqueID=strVertex2UniqueID;
	this.edges.add(edge);
	}
	
	// removes vertex and its incident edges [1 pt]
	public void removeVertex(StringBuffer strVertexUniqueID) throws
	 GraphException{
		for(int i =0 ; i <vertices.size() ; i++){
			if(vertices.get(i)._strUniqueID==strVertexUniqueID)
				vertices.remove(i);
		}
		for(int i =0 ; i <edges.size() ; i++){
			if(edges.get(i).End._strUniqueID==strVertexUniqueID || edges.get(i).Start._strUniqueID==strVertexUniqueID)
				edges.remove(i);
		}
	}

	// removes an edge from the graph 
	public void removeEdge(StringBuffer strEdgeUniqueID) throws
	 GraphException {
		for(int i =0 ; i <edges.size() ; i++){
			if(edges.get(i)._strUniqueID==strEdgeUniqueID)
				edges.remove(i);
		}
	}
	// returns a vector of edges incident to vertex whose
	 // id is strVertexUniqueID 
	public Vector<Edge> incidentEdges(StringBuffer strVertexUniqueID)
			 throws GraphException {
		Vector<Edge> edge= new Vector<Edge>(); ;
		for(int i =0 ; i <edges.size() ; i++){
			if(edges.get(i).End._strUniqueID==strVertexUniqueID || edges.get(i).Start._strUniqueID==strVertexUniqueID)
				edge.addElement(edges.get(i)) ;
		}
	return edge;
	}
	// returns all vertices in the graph 
	public Vector<Vertex> vertices()throws GraphException{
		Vector<Vertex> vertices= new Vector<Vertex>(); ;
		for(int i =0 ; i <vertices.size() ; i++){
			vertices.addElement(vertices.get(i));
		}
		return vertices;
	}

	// returns all edges in the graph
	public Vector<Edge> edges() throws GraphException {
		Vector<Edge> edges= new Vector<Edge>(); ;
		for(int i =0 ; i <edges.size() ; i++){
			edges.addElement(edges.get(i));
		}
		return edges;
	}
	// returns an array of the two end vertices of the
	 // passed edge
	public Vertex[] endVertices(StringBuffer strEdgeUniqueID)
	 throws GraphException
	 {
		Edge edge = new Edge();
		for(int i =0 ; i <edges.size() ; i++){
			if(edges.get(i)._strUniqueID==strEdgeUniqueID)
		edge = edges.get(i);
		}
		 
		Vertex [] Endpoints = {edge.Start, edge.End};
		return Endpoints;
	 }
	
	// returns the vertex opposite of another vertex [1 pt]
	public Vertex opposite(StringBuffer strVertexUniqueID,
	 StringBuffer strEdgeUniqueID) throws
	 GraphException {
		Vertex vertex = new Vertex();
		Edge edge = new Edge();
		
		for(int i =0 ; i <edges.size() ; i++){
			if(edges.get(i)._strUniqueID==strEdgeUniqueID)
			  edge= edges.get(i);
		}
		
			if(edge.End._strUniqueID==strVertexUniqueID)
			vertex = edge.Start;	
			if(edge.Start._strUniqueID==strVertexUniqueID)
				vertex = edge.End;
		return vertex;
	}

	
	public static void main(String[] args) {
		Graph G = new Graph();
		System.out.println(G.getLibraryName());
		System.out.println(G.getLibraryVersion());

	}
}
