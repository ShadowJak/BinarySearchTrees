
public class BST  {
	// k is 3 per the instructions.
	int k = 3;
	
	// Tracking when the ranks are correctly assigned
	boolean ranked = false;
	
	// Boolean for passing the k test.
	boolean passKTest = true;
	
	// Rank counter
	int rank = 0;

	// This is the root node, which starts off as null
	//   when the BST is empty.
	BSTNode objRootNode;
	
	// Not sure why we need this, but here it is per the instructions.
	// Returns the root node.
	public BSTNode getRootNode() {
		return objRootNode;
	}

	public void setRootNode(BSTNode objRootNode) {
		this.objRootNode = objRootNode;
	}

	// Class constructor.
	public BST() {
		// Not really necessary, provided for clarity.
		objRootNode = null;
	}

	// Method to see if the tree is empty.
	public boolean IsEmpty() {
		// Return a boolean indicating whether the
		//   three is empty or not.
		return objRootNode == null;
	}

	/* Functions to search for an element */
    public BSTNode Search( int keyValue ) {
        return Search(objRootNode, keyValue);
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int keyValue ) {
    	
    	if( objNode == null ) {
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int thisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if (keyValue < thisKeyValue) {
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if (keyValue > thisKeyValue) {
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else {
    		return objNode;
    	}
            
    	// Now call Search recursively.
    	return Search(objNode, keyValue);
	}
    
    // Method to insert a node based on the key value.
    public void Insert(int keyValue)  {
    	// Setting ranked to false when the tree changes.
    	ranked = false;
    	// Preventing duplicate entries.
    	if (Search(keyValue) == null) {
    		// The root node is returned to m_objRootNode from Insert()
    		objRootNode = Insert(keyValue, objRootNode, null, 0);
    	}
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    // Added parent to keep track of the parent of the node (obviously).
    protected BSTNode Insert(int keyValue, BSTNode objNode, BSTNode parent, int rank)  {
    	// This node is null and simply needs to be allocated and have parent set.
        if(objNode == null) {
        	objNode = new BSTNode(keyValue);
        	objNode.setParent(parent);
        	objNode.setRank(rank);
        	return objNode;
        }
        
        // Here we need to walk left.
        else if (keyValue < objNode.GetKeyValue()) {
        	// The K test. I couldn't think of an easy way to abort the function so I used a boolean to track it.
        	if (Math.abs(objNode.GetKeyValue() - keyValue) < k) {
        		passKTest = false;
        	}
        	// Set the left node of this object by recursively walking left.
        	// objNode.setParent(objNode);
        	// The current objNode will be the parent of the next node looked at.
        	// The subTreeSize of the current objNode will increase by 1 because the new
        	//   node is below. This nodes rank will also increase by 1 because there is
        	//   anotehr node before it now.
        	//objNode.rank++;
        	objNode.subTreeSize++;
        	objNode.SetLeftNode(Insert(keyValue, objNode.GetLeftNode(), objNode, rank));
        }
        
        // Here we need to talk right.
        else if (keyValue > objNode.GetKeyValue()) {
        	// The K test. I couldn't think of an easy way to abort the function so I used a boolean to track it.
        	if (Math.abs(objNode.GetKeyValue() - keyValue) < k) {
        		passKTest = false;
        	}
        	// Set the right node of this object by recursively walking right.
        	// objNode.setParent(objNode);
        	// The current objNode will be the parent of the next node looked at.
        	// The subTreeSize of the current objNode will increase by 1 because the new
        	//   node is below.
        	objNode.subTreeSize++;
        	//rank = objNode.getRank() + 1;
        	objNode.SetRightNode(Insert(keyValue, objNode.GetRightNode(), objNode, rank));
        	// Set the node below to the parent node + 1 because it is after the parent node.
        	//objNode.GetRightNode().setRank(objNode.rank + 1);
        }
        
        // The K Test. If the test fails, delete the node. We have to call delete instead of
        //   simply not adding the node because the insertion code up to this point changed
        //   information in the nodes such as subTreeSize.
        // The K test. I couldn't think of an easy way to abort the function so I used a boolean to track it.
        if (Math.abs(objNode.GetKeyValue() - keyValue) < k) {
        	passKTest = false;
        }
        
        // If the test fails, delete the node. We have to call delete instead of
        //   simply not adding the node because the insertion code up to this point changed
        //   information in the nodes such as subTreeSize.
        if (!passKTest) {
        	Delete(keyValue);
        }
        
        passKTest = true;
        
        return objNode;
    }
    
    // Helper method for Deletion
    public void Delete(int keyValue) {
    	// Setting ranked to false when the tree changes.
    	ranked = false;
    	Delete(keyValue, objRootNode);
    }
    
    // Method to delete nodes.
    protected void Delete (int keyValue, BSTNode objNode) {
    	// Base Case: We reach an end without finding the node.
    	if (objNode == null) {
    		return;
    	}
    	
    	// If the key is smaller than current node, go left.
    	else if (keyValue < objNode.GetKeyValue()) {
    		Delete(keyValue, objNode.objLeftNode);
    	}
    	// If bigger, go right.
    	else if (keyValue > objNode.GetKeyValue()) {
    		Delete(keyValue, objNode.objRightNode);
    	}
    	// Here we are "Just Right" and can start deleting.
    	if (keyValue == objNode.GetKeyValue()) {
    		// Variable to traverse the tree to the top.
    		BSTNode tempNode = objNode;
    		// Moving up the tree and reducing the subTreeSize by 1 for each node because
    		//   a node below was removed.
			while (tempNode != null) {
				tempNode.subTreeSize--;
				tempNode = tempNode.getParent();
			}
			
			// If objNode doesn't have any children, we can set the approriate pointer in the parent
			//   node to null.
    		if (objNode.objLeftNode == null && objNode.objRightNode == null) {
    			// if objNode is less than the parent, the parent's left becomes null.
    			if (objNode.GetKeyValue() < objNode.getParent().GetKeyValue()) {
    				objNode.getParent().SetLeftNode(null);
    			// Otherwise, the right is null.
    			} else {
    				objNode.getParent().SetRightNode(null);
    			}
    		// If objNode has a subtree only on the right side, that tree gets attached to the parent
    		} else if (objNode.objLeftNode == null) {
    			// Here we determine if the subtree should be attached to the left or right pointer variable.
    			if (objNode.GetKeyValue() < objNode.getParent().GetKeyValue()) {
    				objNode.getParent().SetLeftNode(objNode.objRightNode);
    			} else {
    				objNode.getParent().SetRightNode(objNode.objRightNode);
    			}
    		// Similarly, if objNode has a subtree only on the left side that tree will be attached to the parent.
    		} else if (objNode.objRightNode == null) {
    			if (objNode.GetKeyValue() < objNode.getParent().GetKeyValue()) {
    				objNode.getParent().SetLeftNode(objNode.objLeftNode);
    			} else {
    				objNode.getParent().SetRightNode(objNode.objLeftNode);
    			}
    		// If objNode has two children, we have to find the appropriate sub node to replace objNode.
    		} else {
    			// Varaible to find the largest node on the left side.
    			BSTNode greatLeftNode = objNode.GetLeftNode();
    			// Traversing through the list to find that node and shrinking subTreeSize along the way.
    			while (greatLeftNode.GetRightNode() != null) {
    				greatLeftNode.subTreeSize--;
    				greatLeftNode = greatLeftNode.GetRightNode();
    			}
    			// We set the value of objNode to that of greatLeftNode because the other information in objNode is correct.
    			objNode.SetKeyValue(greatLeftNode.GetKeyValue());
    			// Sometimes the subtree only contains one node
    			if (greatLeftNode.GetKeyValue() > greatLeftNode.getParent().GetKeyValue()) {
    				greatLeftNode.getParent().SetRightNode(null);
    			// When there is only one node in the sub tree, the parent's left pointer is set to null instead of the right.
    			} else {
    				greatLeftNode.getParent().SetLeftNode(null);
    			}
    		}
    	}
    	return;
    }
    
    // Method to return the minimum value of the tree.
    public int getMin() {
    	return getMin(objRootNode);
    }
    
    // Getting Min value in a tree.
    protected int getMin(BSTNode node) {
    	// Keep going left until null
    	while (node.GetLeftNode() != null) {
    		node = node.GetLeftNode();
    	}
    	
    	return node.GetKeyValue();
    }
    
    // Getting Max value in a tree.
    public int getMax() {
    	return getMax(objRootNode);
    }
    
    // Keep going left until null
    protected int getMax(BSTNode node) {
    	// Keep going right until null
    	while (node.GetRightNode() != null) {
    		node = node.GetRightNode();
    	}
    	return node.GetKeyValue();
    }
    
    // Get rank based on value. If the value isn't found, the rank is -1.
    public int getRank(int nValue) {
    	if (!ranked) {
    		assignRank();
    	}
    	return getRank(Search(nValue));
    }
    
    public int getRank(BSTNode node) {
    	// Returns -1 when the node doesn't exist and the rank of the node otherwise
    	return (node != null ? node.rank : -1);
    }
    
    // Assign rank to every node in order of smallest to largest
    public void assignRank() {
    	// Start Ranking at 0
    	rank = 0;
    	assignRank(objRootNode);
    	// The list is now ranked. Yay.
    	ranked = true;
    }
    
    private void assignRank(BSTNode node) {
    	if (node == null) {
    		return;
    	}
    	assignRank(node.GetLeftNode());
    	node.setRank(rank++);
    	assignRank(node.GetRightNode());
    }
    
    
	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
}
