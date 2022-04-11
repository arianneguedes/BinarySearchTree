import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTreeI<E> {
	
	protected boolean addReturn;
	protected E deleteReturn;

	public BinarySearchTree() {
		super(0);
	}
	
	public BinarySearchTree(E item) {
		super(0);
		
		add(item);
	}
	
	public BinarySearchTree(ArrayList<E> data) {
		super(0);
		//Todo		
		//Please populate a binary search tree based on the elements inside the ArrayList
		for(var item: data)
		{
			add(item);
		}
	}
	
	@Override
	public E find(E target) {
		return find(root, target);
	}
	
	@Override
	public boolean contains(E item) {
		return find(item) != null;	
	}

	private E find(Node<E> cRoot, E target) {
		if(cRoot == null) return null;
		
		int result = target.compareTo(cRoot.data);
		if(result == 0) {
			return cRoot.data;
		} else if(result < 0) {
			return find(cRoot.left, target);
		} else {
			return find(cRoot.right, target);
		}
	}
	
	@Override
	public boolean add(E item) {
		root = add(root, item);
			return addReturn;
	}
	
	protected Node<E> add(Node<E> cRoot, E item) {
		if(cRoot == null) {
			addReturn = true;
			return new Node<>(item);
		}
		int result = item.compareTo(cRoot.data);
		if(result < 0) {
			cRoot.left = add(cRoot.left, item);
		}
		else if(result > 0) {
			cRoot.right = add(cRoot.right, item);
		} else {
			addReturn = false;
		}
		return cRoot;	
	}
	
	@Override
	public E delete(E target) {
		root = delete(root, target);
		return deleteReturn;
	}

    private Node<E> delete(Node<E> cRoot, E item) {
        if (cRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return cRoot;
        }

        // Search for item to delete.
        int result = item.compareTo(cRoot.data);
        if (result < 0) {
            // item is smaller than localRoot.data.
            cRoot.left = delete(cRoot.left, item);
            return cRoot;
        } else if (result > 0) {
            // item is larger than localRoot.data.
            cRoot.right = delete(cRoot.right, item);
            return cRoot;
        } else {
            // item is at local root.
            deleteReturn = cRoot.data;
            if (cRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return cRoot.right;
            } else if (cRoot.right == null) {
                // If there is no right child, return left child.
                return cRoot.left;
            } else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (cRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data with the data in the
                    // left child.
                    cRoot.data = cRoot.left.data;
                    // Replace the left child with its left child.
                    cRoot.left = cRoot.left.left;
                    return cRoot;
                } else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node's data with ip.
                    cRoot.data = getLargestChildDeleteNode(cRoot.left);
                    return cRoot;
                }
            }
        }
    }
	    
    private E getLargestChildDeleteNode(Node<E> cRoot) {

        if (cRoot.right.right == null) {
            E returnValue = cRoot.right.data;
            cRoot.right = cRoot.right.left;
            return returnValue;
        } else {
            return getLargestChildDeleteNode(cRoot.right);
        }
    }
    
    @Override
    public boolean remove(E target) {
        return delete(target) != null;
    }
    
    @Override
    public String toString() {
    	//Todo	
    	//Override this method so that the output string is correctly formatted as per the provided output format
    	//Inorder traversal (sorted)
    	return inOrderTraversal(root).replaceAll("\\s+$", ""); //Remove the last space
    }
	
    public BinarySearchTree<String> getSubBST(int len) {
    	//Todo
    	//Create a Sub Binary Search Tree based on the length of the elements (assume they are the name of String data type)
    	//In the return BST, every tree element will have the exactly same length as with the parameter value to this function
    	//Correct the return statement
    	String orderedString = inOrderTraversal(root);
    	String[] orderedArray = orderedString.split(" ");
        	
    	ArrayList<String>orderedArrayList = getSubBSTByLength(orderedArray, len);
    	BinarySearchTree<String> subBST = new BinarySearchTree<String>(orderedArrayList);
    	return subBST;
    }

	private ArrayList<String> getSubBSTByLength(String[] array, int len) {
		ArrayList<String> cArrayList = new ArrayList<String>();
		for(int i = 0; i < array.length; i++) {
			if(array[i].length() == len) {
				cArrayList.add(array[i]);
			}
		}
		return cArrayList;
	}
	
	public String showValuesInRange(E start, E end) {
		//Todo		
		//From the original BST, display values from starting item to ending item
		//If(start>end) or if(end>start) in both cases your program will display them in ascending order
		ArrayList<E> valuesInRange = new ArrayList<E>();
		E valueOne, valueTwo;
		
    	if(start.compareTo(end) < 0) {
    		valueOne = start;
    		valueTwo = end;
    	} else {
    		valueOne = end;
    		valueTwo = start;
    	}
    	getValuesInRange(root, valueOne, valueTwo, valuesInRange);
    	
    	BinarySearchTree<E> valuesInRangeBST = new BinarySearchTree<E>(valuesInRange);
    	return valuesInRangeBST.toString();
	} 

	private void getValuesInRange(Node<E> cRoot, E valueOne, E valueTwo, ArrayList<E> valuesInRange) {
		if(cRoot == null) {
			return;
		}
		if(valueOne.compareTo(cRoot.data) < 0) {
			getValuesInRange(cRoot.left, valueOne, valueTwo, valuesInRange);
		}
		if(valueOne.compareTo(cRoot.data) <= 0  && (valueTwo.compareTo(cRoot.data) >= 0)) {
			valuesInRange.add(cRoot.data);
		}
		if(valueTwo.compareTo(cRoot.data) > 0) {
			getValuesInRange(cRoot.right, valueOne, valueTwo, valuesInRange);
		}
	}
	
	public String findLargestValue() {	
		//Todo
		//Return the largest item in String format from your original BST
		return findLargestValue(root).toString();
	}
	
	private E findLargestValue(Node<E> cRoot) {
		if(cRoot.right == null) {
			return cRoot.data;
		}
		return findLargestValue(cRoot.right);
	}
	
	public String findLowestValue() {		
		//Todo
		//Return the smallest item in String format from your original BST
		return findLowestValue(root).toString();
	}
	
	private E findLowestValue(Node<E> cRoot) {
		if(cRoot.left == null) {
			return cRoot.data;
		}
		return findLowestValue(cRoot.left);
	}
}