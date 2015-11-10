
public class BSTNode 
{
	BSTNode objLeftNode, objRightNode, parent;
	int keyValue;
	int subTreeSize;
	int rank;



	public BSTNode() {
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		objLeftNode = objRightNode = null;
		
		// Set this node's key value and weight to default.
		keyValue = rank = 0;
		subTreeSize = 1;
	}

	public BSTNode(int keyValue) {
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		objLeftNode = objRightNode = parent = null;
		subTreeSize = 1;
		
		// Set this node's key value
		this.keyValue = keyValue;
	}

	// Accessor method to set the left node.
	public void SetLeftNode(BSTNode objLeftNode) {
		// Assign the left node object reference.
		this.objLeftNode = objLeftNode;
	}
	
	// Accessor method to set the right node.
	public void SetRightNode(BSTNode objRightNode) {
		// Assign the right node object reference.
		this.objRightNode = objRightNode;
	}
	
	// Accessor method to get the left node object.
	public BSTNode GetLeftNode() {
		// Return the object.
		return(objLeftNode);
	}
	
	// Accessor method to get the right node object.
	public BSTNode GetRightNode() {
		// Return the object.
		return(objRightNode);
	}
	
	public BSTNode getParent() {
		return parent;
	}

	public void setParent(BSTNode parent) {
		this.parent = parent;
	}
	
	public int getSubTreeSize() {
		return subTreeSize;
	}

	public void setSubTreeSize(int subTreeSize) {
		this.subTreeSize = subTreeSize;
	}



	// Accessor method to set the node's key value.
	public void SetKeyValue(int keyValue) {
		// Set the value.
		this.keyValue = keyValue;
	}
	
	// Accessor method to get the node's key value.
	public int GetKeyValue() {
		// Return the value.
		return(keyValue);
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
