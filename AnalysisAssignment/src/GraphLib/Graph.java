package GraphLib;

import java.util.ArrayList;
import java.util.Vector;

public class Graph {
	private static final Visitor Visitor = null;
	protected ArrayList<Vertex> vertices = new ArrayList<>(); 
	protected ArrayList<Edge> edges = new ArrayList<>();
	protected ArrayList<ArrayList<Vertex>> adj = new ArrayList<>(); //new
	protected static int i =0;  		//new

	public Graph(){
		this.adj = new ArrayList<>();
	}
	public String getLibraryName(){
		return this.getClass().getPackage().getName();
	}
	public String getLibraryVersion(){
		return this.getClass().getPackage().getSpecificationVersion();
	}
	// the following method adds a vertex to the graph
	public void insertVertex(StringBuffer strUniqueID,StringBuffer strData,int nX,int nY) throws GraphException {
		Vertex vertex = new Vertex();
		//System.out.println(vertices);
		vertex._strUniqueID = strUniqueID;
		vertex._strData = strData;
		vertex._nX = nX;
		vertex._nY = nY;
		//System.out.println(vertex);
		vertices.add(vertex);
		adj.add(new ArrayList<Vertex>());
		adj.get(i).add(vertex);
		i++;

	}

	
	// inserts an edge between 2 specified vertices
	public void insertEdge(StringBuffer strVertex1UniqueID,
			StringBuffer strVertex2UniqueID,
			StringBuffer strEdgeUniqueID,
			StringBuffer strEdgeData,
			int nEdgeCost) throws GraphException {
		Edge edge = new Edge();
		Vertex EndVertex = new Vertex();
		edge._strData = strEdgeData;
		edge._strUniqueID = strEdgeUniqueID;
		edge._nEdgeCost = nEdgeCost;
		edge.Start._strUniqueID=strVertex1UniqueID;
		edge.End._strUniqueID=strVertex2UniqueID;
		this.edges.add(edge);
		//new	
		for(int j =0 ; j <vertices.size() ; j++){
			if(vertices.get(j)._strUniqueID==strVertex2UniqueID)
				EndVertex = vertices.get(j);
		}
		for(int c = 0;c<adj.size();c++) {		// For every vertex of the edge,add it to the list of the opposite vertex.
			if(adj.get(c).get(0)._strUniqueID.toString().equals(strVertex1UniqueID.toString())) {
				for(int loopVerticies = 0; loopVerticies<vertices.size();loopVerticies++) {
					if(vertices.get(loopVerticies)._strUniqueID.toString().equals(strVertex2UniqueID.toString())) {
						adj.get(c).add(vertices.get(loopVerticies));
					}
				}
			}
			if(adj.get(c).get(0)._strUniqueID.toString().equals(strVertex2UniqueID.toString())) {
				for(int loopVerticies = 0; loopVerticies<vertices.size();loopVerticies++) {
					if(vertices.get(loopVerticies)._strUniqueID.toString().equals(strVertex1UniqueID.toString())) {
						adj.get(c).add(vertices.get(loopVerticies));
					}
				}
			}
		}
	}

	// removes vertex and its incident edges
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
		for(int i =0 ; i <adj.size() ; i++){
			if(adj.get(i).get(0)._strUniqueID.toString().equals(strVertexUniqueID.toString())) {
				adj.remove(i);
			}
				
			}
		
	}

	// removes an edge from the graph 
	public void removeEdge(StringBuffer strEdgeUniqueID) throws
	GraphException {
		StringBuffer TempForStart = null;
		StringBuffer TempForEnd = null;
		for(int i =0 ; i <edges.size() ; i++){
			if(edges.get(i)._strUniqueID.toString().equals(strEdgeUniqueID.toString())) {
				TempForEnd = edges.get(i).End._strUniqueID;
				TempForStart = edges.get(i).Start._strUniqueID;
				edges.remove(i);
				
			}
		}
		for(int loopAllAdj = 0;loopAllAdj<adj.size();loopAllAdj++) {
			if(adj.get(loopAllAdj).get(0)._strUniqueID.toString().equals(TempForStart.toString())) {
				for(int i =0;i<adj.get(loopAllAdj).size();i++) {
					if(adj.get(loopAllAdj).get(i)._strUniqueID.toString().equals(TempForEnd.toString())) {
						adj.get(loopAllAdj).remove(i);
					}
				}
			}
			if(adj.get(loopAllAdj).get(0)._strUniqueID.toString().equals(TempForEnd.toString())) {
				for(int i =0;i<adj.get(loopAllAdj).size();i++) {
					if(adj.get(loopAllAdj).get(i)._strUniqueID.toString().equals(TempForStart.toString())) {
						adj.get(loopAllAdj).remove(i);
					}
				}
			}
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
	public void DFSUtil(int x, Visitor visitor) 
    { 
		adj.get(x).get(0).visited=true;  //assign the vertex as visited
		visitor.visit(adj.get(x).get(0)); //visit the vertex
		System.out.println(adj.get(x).get(0)._strUniqueID);
    for(int k =1 ; k < adj.get(x).size(); k++){
    	if(adj.get(x).get(k).visited==false)
    	{
    		//assign the edge as visited
    		for(int z= 0; z<edges.size(); z++){
    			if(adj.get(x).get(0)._strUniqueID==edges.get(z).Start._strUniqueID
    			&& adj.get(x).get(k)._strUniqueID==edges.get(z).End._strUniqueID		
    			||
    			adj.get(x).get(0)._strUniqueID==edges.get(z).End._strUniqueID
    			&& adj.get(x).get(k)._strUniqueID==edges.get(z).Start._strUniqueID){
    				visitor.visit(edges.get(z));
    			}
    		}
    		for(int j = 0 ; j<adj.size(); j++){
    			if(adj.get(x).get(k)	._strUniqueID==adj.get(j).get(0)._strUniqueID)	    		
    				DFSUtil(j,visitor);
    		}
    	}
    }
        } 
	public void dfs(StringBuffer strStartVertexUniqueID, GradingVisitor visitor) throws GraphException {
	
    
		Vertex x =  new Vertex();
		int n =0;
		
		for(n=0 ; n <vertices.size() ; n++){
			if(vertices.get(n)._strUniqueID.toString().equals(strStartVertexUniqueID.toString())){			
			x=vertices.get(n) ;
			break;
			}
		}
    	   if(x.visited==false){ 
    		   DFSUtil(n,visitor);
  
      }
    	   System.out.println("Result" + " : " + visitor.getResult());   
	}
    public String toString(){
        String result = "";
        for(int i = 0; i < adj.size(); i++){
        	result +="[";
            for(int j = 0; j < adj.get(i).size(); j++){
                result += adj.get(i).get(j)._strUniqueID + ",";
            }
            result += "]\n";
        }
        return result;
    }

	public static void main(String[] args) throws GraphException {



		Graph G = new Graph();

		G.insertVertex(new StringBuffer("0"), new StringBuffer("1"),1, 0);
		G.insertVertex(new StringBuffer("1"), new StringBuffer("1"),1, 0);
		G.insertVertex(new StringBuffer("2"), new StringBuffer("2"),2, 0);
		G.insertVertex(new StringBuffer("3"), new StringBuffer("2"),3, 0);
		G.insertVertex(new StringBuffer("4"), new StringBuffer("2"),3, 0);


		G.insertEdge(new StringBuffer("0"), new StringBuffer("2"), new StringBuffer("1"), new StringBuffer("1"), 1);
		G.insertEdge(new StringBuffer("0"), new StringBuffer("1"), new StringBuffer("1"), new StringBuffer("1"), 1);
		G.insertEdge(new StringBuffer("0"), new StringBuffer("4"), new StringBuffer("1"), new StringBuffer("1"), 1);
		G.insertEdge(new StringBuffer("3"), new StringBuffer("2"), new StringBuffer("1"), new StringBuffer("1"), 1);
		G.insertEdge(new StringBuffer("3"), new StringBuffer("1"), new StringBuffer("1"), new StringBuffer("1"), 1);

		//G.insertEdge(new StringBuffer("1"), new StringBuffer("3"), new StringBuffer("1"), new StringBuffer("1"), 1);
	//	G.insertEdge(new StringBuffer("1"), new StringBuffer("2"), new StringBuffer("2"), new StringBuffer("1"), 1);

		//G.removeEdge(new StringBuffer("1"));
		GradingVisitor visitor =  new GradingVisitor();
		G.dfs(new StringBuffer("0") , visitor);
		System.out.println(G);
		//System.out.println(G.getLibraryVersion());

	}
}

