package GraphLib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Graph {
	private static final Visitor Visitor = null;
	protected ArrayList<Vertex> vertices = new ArrayList<>(); 
	protected ArrayList<Edge> edges = new ArrayList<>();
	protected ArrayList<ArrayList<Vertex>> adj = new ArrayList<>(); //new
	protected static int i =0;  		//new
	protected static boolean FoundPath = false;
	protected ArrayList<String> Traversal = new  ArrayList<>(); 

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

	// returns the vertex opposite of another vertex 
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
	public void DFSUtil(int x, Visitor visitor , ArrayList <Vertex> Result) 
    { 
		adj.get(x).get(0).visited=true;  //assign the vertex as visited
		Result.add(adj.get(x).get(0)); //Stack for vertices
    for(int k =1 ; k < adj.get(x).size(); k++){
    	if(adj.get(x).get(k).visited==false)
    	{
    		//assign the edge as visited
    		for(int z= 0; z<edges.size(); z++){
				if(adj.get(x).get(0)._strUniqueID.toString().equals(edges.get(z).Start._strUniqueID.toString())
						&& adj.get(x).get(k)._strUniqueID.toString().equals(edges.get(z).End._strUniqueID.toString())		
						||
						adj.get(x).get(0)._strUniqueID.toString().equals(edges.get(z).End._strUniqueID.toString())
						&& adj.get(x).get(k)._strUniqueID.toString().equals(edges.get(z).Start._strUniqueID.toString())){
					visitor.visit(edges.get(z));
				}
			}
    		for(int j = 0 ; j<adj.size(); j++){
    			if(adj.get(x).get(k)._strUniqueID.toString().equals(adj.get(j).get(0)._strUniqueID.toString()))	    		
    				DFSUtil(j,visitor,Result);
    		}
    	}
    }
        } 
	public void dfs(StringBuffer strStartVertexUniqueID, GradingVisitor visitor) throws GraphException {
		
		
		ArrayList <Vertex> Result = new ArrayList<Vertex>();
		Vertex x =  new Vertex();
		int n =0;
		
		for(n=0 ; n <vertices.size() ; n++){
			if(vertices.get(n)._strUniqueID.toString().equals(strStartVertexUniqueID.toString())){			
			x=vertices.get(n) ;
			break;
			}
		}
    	   if(x.visited==false){ 
    		   DFSUtil(n,visitor, Result);
  
      }
    	   for(int i = 0 ; i<Result.size() ; i++){
    		   visitor.visit(Result.get(i));
    	   }
    	   System.out.println("Result" + " : " + visitor.getResult());   
    	   
    	   for(int i =0;i<vertices.size();i++){
    			vertices.get(i).visited = false;
    			vertices.get(i).processed = false;

    		}
    	  
	}
	public void BFSUtil(int x, ArrayList <Vertex> Result, Visitor visitor) 
    { 
		ArrayList <Vertex> visited = new ArrayList<Vertex>();
		adj.get(x).get(0).visited=true;  
		for(int i=1; i<adj.get(x).size(); i++){
			if(adj.get(x).get(i).visited==false && !(adj.get(x).get(i).processed)){
			Result.add(adj.get(x).get(i));
			adj.get(x).get(i).processed=true;
			}
			for(int z= 0; z<edges.size(); z++){
				if(adj.get(x).get(0)._strUniqueID.toString().equals(edges.get(z).Start._strUniqueID.toString())
						&& adj.get(x).get(i)._strUniqueID.toString().equals(edges.get(z).End._strUniqueID.toString())		
						){
					visitor.visit(edges.get(z));
				}
			}
		}
		System.out.print("Result : " + "   ");
		for(int i=0; i <Result.size();i++){
			System.out.print(Result.get(i)._strUniqueID);
		}
		System.out.println();
		for(int i=0; i <Result.size();i++){
			if(Result.get(i).visited==false){
				for(int j = 0 ; j<adj.size(); j++){
	    			if(Result.get(i)._strUniqueID==adj.get(j).get(0)._strUniqueID)	    		
	    				BFSUtil(j,Result,visitor);
	    		}
			}
		}
    } 
	public void Bfs(StringBuffer strStartVertexUniqueID, GradingVisitor visitor) throws GraphException {
		
		ArrayList <Vertex> Result = new ArrayList<Vertex>();
		Vertex x =  new Vertex();
		int n =0;
		
		for(n=0 ; n <vertices.size() ; n++){
			if(vertices.get(n)._strUniqueID.toString().equals(strStartVertexUniqueID.toString())){			
			x=vertices.get(n) ;
			break;
			}
		}
    	   if(x.visited==false){ 
    		   Result.add(vertices.get(n));
    		   BFSUtil(n, Result, visitor);
    	   }
    	   for(int i = 0 ; i<Result.size() ; i++){
   		   visitor.visit(Result.get(i));
    	   }
   	   System.out.println("Result" + " : " + visitor.getResult()); 
   	for(int i =0;i<vertices.size();i++){
		vertices.get(i).visited = false;
		vertices.get(i).processed = false;

	}
  

	}
	public Vector<PathSegment> pathDFS(
			String strStartVertexUniqueID, String strEndVertexUniqueID)
					throws GraphException{
		Vertex x =  new Vertex();
		int n =0;
		Vector<PathSegment> res = new Vector<>();
		for(n=0 ; n <vertices.size() ; n++){
			if(vertices.get(n)._strUniqueID.toString().equals(strStartVertexUniqueID.toString())){			
				x=vertices.get(n) ;
				break;
			}
		}
		if(x.visited==false){ 
			PathDFSUtil(n, strEndVertexUniqueID,res);

		}
		for(int i =0;i<vertices.size();i++){
			vertices.get(i).visited = false;
			vertices.get(i).processed = false;

		}
	  	
		return res;
	}
	public void PathDFSUtil(int x,String strEndVertexUniqueID,Vector<PathSegment> res) 
	{ 
		Traversal.add(vertices.get(x)._strUniqueID.toString());
		//System.out.println(adj.get(x).get(0)._strUniqueID.toString());
		if(adj.get(x).get(0)._strUniqueID.toString().equals(strEndVertexUniqueID.toString())){
			FoundPath = true;
			for(int i =0;i<Traversal.size()-1;i++){
				PathSegment toAdd = new  PathSegment();
				//System.out.println(Traversal.get(i));
				String start = Traversal.get(i).toString();
				String end = Traversal.get(i+1).toString();
				for(int j = 0;j<edges.size();j++){
					if(edges.get(j).Start._strUniqueID.toString().equals(start)
							&& edges.get(j).End._strUniqueID.toString().equals(end) ){
						
						toAdd._edge = edges.get(j);
						toAdd.Ma3koosa = 0;
						
						break;
					}
					if(edges.get(j).End._strUniqueID.toString().equals(start)
							&& edges.get(j).Start._strUniqueID.toString().equals(end) ){
						
						toAdd._edge = edges.get(j);
						toAdd.Ma3koosa = 1;
						
						break;
					}
						
				}
				for(int j = 0;j<vertices.size();j++){
					if(vertices.get(j)._strUniqueID.toString().equals(start)){
						toAdd._vertex = vertices.get(j);
						//System.out.println(vertices.get(j)._strUniqueID.toString());
						
					}
						
				}
				res.addElement(toAdd);
			}
			return;
		}
		adj.get(x).get(0).visited=true;  //assign the vertex as visited
		for(int k =1 ; k < adj.get(x).size(); k++){
			if(adj.get(x).get(k).visited==false)
			{
				for(int z= 0; z<edges.size(); z++){
					if(adj.get(x).get(0)._strUniqueID==edges.get(z).Start._strUniqueID
							&& adj.get(x).get(k)._strUniqueID==edges.get(z).End._strUniqueID		
							||
							adj.get(x).get(0)._strUniqueID==edges.get(z).End._strUniqueID
							&& adj.get(x).get(k)._strUniqueID==edges.get(z).Start._strUniqueID){
					}
				}
				for(int j = 0 ; j<adj.size(); j++){
					if(adj.get(x).get(k)._strUniqueID.toString().equals(adj.get(j).get(0)._strUniqueID.toString()))	    		
						if(!FoundPath)
							PathDFSUtil(j,strEndVertexUniqueID,res);
				}
			}
		}
		Traversal.remove(Traversal.size()-1);

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
	public String PrintVectorOfPaths(Vector<PathSegment> res){
		String ans = "";
		for(int i =0;i<res.size();i++){
			ans +=res.get(i).toString();
		}
		return ans;
	}

	
	
	
	public Vertex[] closestPair() throws GraphException 
	{
		Vertex[] res = new Vertex[2];
		Distance D = closestPairHelper(this.vertices);
		res[0] = D.V1;
		res[1] =D.V2;
		return res;
		
	}
	
	private float EuclideanDist (float x1 , float y1, float x2, float y2){
		//System.out.println("x1 : " + " " + x1);
		//System.out.println("x2 : " + " " + x2);

		//System.out.println((int) Math.sqrt( Math.pow((x1-x2),2) + 
		//		Math.pow((y1-y2),2)));
		return (int) Math.sqrt( Math.pow((x1-x2),2) + 
				Math.pow((y1-y2),2)); 
	}
	private Distance MinimumDistance(Distance A,Distance B){
		if(A.EcdDistance<B.EcdDistance)
			return A;
			else
			return B;
	}
	private ArrayList<Vertex> Splitter(ArrayList<Vertex> input,int start,int end){
		ArrayList<Vertex>res = new ArrayList<>();
			for(int i =start;i<end;i++){
				res.add(input.get(i));
			}
	
//		for(int i =0;i<res.size();i++){
//			System.out.print(res.get(i)._nX + " , ");
//		}
//		System.out.println("\n");
		return res;
}
	private Distance MiddleMinDist(ArrayList<Vertex> Graph , Distance d){
		float min = d.EcdDistance;
		Distance minDist = d;	
		for(int i=0; i<Graph.size()-1; i++){
			for(int j = 0;j<Graph.size()-i-1;j++){
				 if (Graph.get(j)._nY > Graph.get(j+1)._nY) 
	                { 
	                    // swap temp and arr[i] 
	                    Vertex temp = Graph.get(j); 
	                    Graph.set(j, Graph.get(j+1)); 
	                    Graph.set(j+1,temp);
	                } 
			}
		}
		for(int i=0; i<Graph.size()-1; i++){
			for(int j = i+1;j<Graph.size() && Graph.get(i)._nY - Graph.get(j)._nY < min;j++){
				 if (EuclideanDist(Graph.get(i)._nX,Graph.get(i)._nY, Graph.get(j)._nX, Graph.get(j)._nY)< min) 
	                { 
				//	System.out.println("DDDDDD");
					  min=EuclideanDist(Graph.get(i)._nX,Graph.get(i)._nY, Graph.get(j)._nX, Graph.get(j)._nY);
	                  minDist.V1= Graph.get(i);
	                  minDist.V2= Graph.get(j);
	                  minDist.EcdDistance=min;
	                } 
			}
		}
		return minDist;
	}
	
	private Distance closestPairHelper(ArrayList<Vertex> Graph){
		
		if(Graph.size()==2){
			Distance D =new Distance();
			D.V1 = Graph.get(0);
			D.V2 = Graph.get(1);
			D.EcdDistance = EuclideanDist(Graph.get(0)._nX, Graph.get(0)._nY, Graph.get(1)._nX
					, Graph.get(1)._nY);
			return D;
		}

		else if(Graph.size()==3){
			Distance D =new Distance();
			D.V1 = Graph.get(0);
			D.V2 = Graph.get(1);
			D.EcdDistance = EuclideanDist(Graph.get(0)._nX, Graph.get(0)._nY, Graph.get(1)._nX
					, Graph.get(1)._nY);
			Distance D1 =new Distance();
			D1.V1 = Graph.get(0);
			D1.V2 = Graph.get(2);
			D1.EcdDistance = EuclideanDist(Graph.get(0)._nX, Graph.get(0)._nY, Graph.get(2)._nX
					, Graph.get(2)._nY);
			Distance D2 =new Distance();
			D2.V1 = Graph.get(1);
			D2.V2 = Graph.get(2);
			D2.EcdDistance = EuclideanDist(Graph.get(1)._nX, Graph.get(1)._nY, Graph.get(2)._nX
					, Graph.get(2)._nY);
			
			
		if(Float.min(D2.EcdDistance,D1.EcdDistance)< D.EcdDistance){
				if(D2.EcdDistance<D1.EcdDistance)
				return D2;
				else
				return D1;
		}
		else
				return D;
			
			
		}
		
		for(int i=0; i<Graph.size()-1; i++){
			for(int j = 0;j<Graph.size()-i-1;j++){
				 if (Graph.get(j)._nX > Graph.get(j+1)._nX) 
	                { 
	                    // swap temp and arr[i] 
	                    Vertex temp = Graph.get(j); 
	                    Graph.set(j, Graph.get(j+1)); 
	                    Graph.set(j+1,temp);
	                } 
			}
		}
		int middle=0;
		if(Graph.size()%2==0){
			middle = Graph.size()/2 -1;
		}
		else
			middle =Graph.size()/2;
		
		Vertex midPoint = Graph.get(middle);
		ArrayList<Vertex> Left = Splitter(Graph,0,middle+1);
		ArrayList<Vertex> Right =Splitter(Graph,middle+1,Graph.size());
		Distance DL = closestPairHelper(Left);
		Distance DR = closestPairHelper(Right);
		Distance d = MinimumDistance(DL, DR);
		
		ArrayList<Vertex> MiddleArea = new ArrayList<>();
		for(int i = 0;i<this.vertices.size();i++){
			if(Math.abs(this.vertices.get(i)._nX - Graph.get(middle)._nX)< d.EcdDistance){
				MiddleArea.add(this.vertices.get(i));
			}
		}
	//	System.out.println(d.EcdDistance);
//		for(int i =0 ; i <MiddleArea.size(); i++){
//			System.out.print(MiddleArea.get(i)._strUniqueID + ",");

		//}
	//	System.out.println();
		return MiddleMinDist(MiddleArea, d);
		
	}
	
	
	
	
	
	public static void main(String[] args) throws GraphException {



		Graph G = new Graph();

	//	G.insertVertex(new StringBuffer("0"), new StringBuffer("1"),5, 2);
		G.insertVertex(new StringBuffer("1"), new StringBuffer("1"),-99, 9);
		G.insertVertex(new StringBuffer("2"), new StringBuffer("2"),-5, 1);
		G.insertVertex(new StringBuffer("3"), new StringBuffer("2"),59, 4);
		G.insertVertex(new StringBuffer("4"), new StringBuffer("2"),-917, 7);
		G.insertVertex(new StringBuffer("5"), new StringBuffer("1"),13, 6);
	//	G.insertVertex(new StringBuffer("6"), new StringBuffer("1"),121, 2);
	//	G.insertVertex(new StringBuffer("7"), new StringBuffer("1"),1, 0);



		G.insertEdge(new StringBuffer("1"), new StringBuffer("4"), new StringBuffer("1"), new StringBuffer("1"), 1);
		G.insertEdge(new StringBuffer("1"), new StringBuffer("2"), new StringBuffer("2"), new StringBuffer("1"), 1);
		G.insertEdge(new StringBuffer("2"), new StringBuffer("3"), new StringBuffer("3"), new StringBuffer("1"), 1);
		G.insertEdge(new StringBuffer("2"), new StringBuffer("4"), new StringBuffer("4"), new StringBuffer("1"), 1);
		G.insertEdge(new StringBuffer("2"), new StringBuffer("5"), new StringBuffer("5"), new StringBuffer("1"), 1);
		G.insertEdge(new StringBuffer("4"), new StringBuffer("5"), new StringBuffer("6"), new StringBuffer("1"), 1);
		G.insertEdge(new StringBuffer("3"), new StringBuffer("5"), new StringBuffer("7"), new StringBuffer("1"), 1);
		
		//G.removeEdge(new StringBuffer("1"));
		GradingVisitor visitor =  new GradingVisitor();
		//G.dfs(new StringBuffer("1") , visitor);
		//G.Bfs(new StringBuffer("1") , visitor);
		//Vector<PathSegment> T = G.pathDFS("1", "5");
		//System.out.println(T);
		//System.out.println(G.getLibraryVersion());
//		Distance x = G.closestPairHelper(G.vertices);
//		
//		System.out.println(x.EcdDistance);
//		System.out.println(x.V1._strUniqueID);
//		System.out.println(x.V2._strUniqueID);
//		
//		Vertex [] d = G.closestPair();
//		
//		for(int i =0;i<d.length;i++){
//			System.out.println(d[i]._strUniqueID.toString() + " , ");
//		}
	}
}

