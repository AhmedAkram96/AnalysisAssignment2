package GraphLib;

//the following class could be used as the building block of a path where a
//path consists of path segments and each path segment consist of a
//vertex and associated edge with it.


public class PathSegment {
protected Vertex _vertex; // the vertex in this path segment
protected Edge _edge; // the edge associated with this vertex
protected int Ma3koosa = -1;
public Vertex getVertex( ){
return _vertex;
}
public Edge getEdge( ){
return _edge;
}
//public String toString(){
//	String To = "";
//	if(this.Ma3koosa == 0){
//		To= this._edge.End._strUniqueID.toString();
//	}
//	else{
//		To= this._edge.Start._strUniqueID.toString();
//	}
//	
//	String res = "From : " + this._vertex._strUniqueID.toString() + " To : " +To;
//	res += " Edge ID:" + this._edge._strUniqueID.toString();
//	
//	return res;
//}

@Override
public String toString()
{
	return getVertex()._strUniqueID.toString() + "";
}

} 
