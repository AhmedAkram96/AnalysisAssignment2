package GraphLib;

public class Edge {
	protected StringBuffer _strUniqueID, //a unique id identifying edge
	 						  _strData; //data associated with this edge.
										//Data could be name of edge or
										// any meaningful property for
										// an edge.
	 protected int _nEdgeCost;			 // cost of traversing this edge
	 protected Vertex Start,End;
	 
	 public Edge() {
		 this.Start = new Vertex();
		 this.End = new Vertex();
	 }
	 public StringBuffer getUniqueID( ){
	return _strUniqueID;
	 }

	 public StringBuffer getData( ){
	 return _strData;
	 }
	 public int getCost( ){
	return _nEdgeCost;
	 } 

}
