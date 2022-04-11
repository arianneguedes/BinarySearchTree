import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class BinaryTree<E> {
	
	protected class Node<E>{
		protected E data;
		protected Node<E> left;
		protected Node<E> right;

		public Node(E data) {
		  this.data = data;
		  left = null;
		  right = null;
		}
		
		public String toString() {
		return data.toString();
		}
	}
	
	protected Node<E> root;
	Random r;
	
	public BinaryTree(int seed) {
		root = null;
		if(seed==0)
			r=new Random(System.currentTimeMillis());
		else 
			r=new Random(seed);
		
	}
	public BinaryTree(E data) {
		root=new Node<>(data);
	}
	
	public BinaryTree(ArrayList<E> data){
		this(0);
		
		for(var item: data)
		{
			append(item);
		}
	}
		
	protected Node<E> getRoot()
	{
		return root;
	}
	
	public void setLeftSubTree(BinaryTree<E> tree)
	{
		root.left=tree.getRoot();
	}

	public void setRightSubTree(BinaryTree<E> tree)
	{
		root.right=tree.getRoot();
	}
	
	
	protected String preOrderTraversal(Node<E> cRoot)
	{
		if(cRoot==null) return "";
		return cRoot.data+" " +preOrderTraversal(cRoot.left) + preOrderTraversal(cRoot.right);
	}
	
	protected String inOrderTraversal(Node<E> cRoot)
	{
		if(cRoot==null) return "";
		return inOrderTraversal(cRoot.left) +cRoot.data+" "+inOrderTraversal(cRoot.right);
	}
	
	protected String postOrderTraversal(Node<E> cRoot)
	{
		if(cRoot==null) return "";
		return postOrderTraversal(cRoot.left) + postOrderTraversal(cRoot.right)+cRoot.data+" ";
	}
	
	protected Node<E> randomAppend(Node<E> parent, Node<E> newNode)
	{
		
		if(parent==null) return newNode;
		
		if(r.nextDouble()<0.5) 			
			parent.left=randomAppend(parent.left, newNode);
		else parent.right=randomAppend(parent.right, newNode);

	return parent;
	}

	public void append(E data)
	{
		Node<E> newNode= new Node<>(data);
		if(root==null)
		{
			root=newNode;
		}
		else
		{
			if(r.nextDouble()<0.5) 
			{
				if(root.left==null)
					root.left=randomAppend(root.left, newNode);
			}
		else {
			root.right=randomAppend(root.right,newNode);
		}
	}
	}
	public String toString()
	{
		return "PreOrder: "+preOrderTraversal(root)+"\nInOder: "+inOrderTraversal(root)+"\nPostOder: "+postOrderTraversal(root);
	}
	
	public String getLeafNodes()
	{
		return preOrderTraversal(root);
	}
		
	protected String preOrderTraversalParent(Node<E> cRoot)
	{
		if(cRoot==null) return "";
		String s="";
		
		if(cRoot.left!=null&&cRoot.right!=null) s+=cRoot.data+" ";
		s+=preOrderTraversalParent(cRoot.left);
		s+=preOrderTraversalParent(cRoot.right);
		return s;

		
	}
	
	public String getParentNodes() {
		//return preOrderTraversalParent(root);
		return getParentNodesRecursive(root);
	}

	protected String getParentNodesRecursive(Node<E> cRoot){
     	if (cRoot == null) return "";
        return (cRoot.left != null && cRoot.right != null) ? cRoot.data + " "+ getParentNodesRecursive(cRoot.left) + getParentNodesRecursive(cRoot.right): "";
	}
	
	
	
	/* the following code is only for displaying the tree... 
	 * You do not need to understanding the following code, it is out of the scope of the course!*/
	
	
	//System.out.println(myBinaryTree.displayTree(1)); //one space separation

	int findHeight(Node<E> root)
	{
	   if(root==null) return 0;
	    {
	        return findHeight(root.left)>findHeight(root.right)?1+findHeight(root.left):1+findHeight(root.right);
	    }
	}
	private class QNode<E> {
	    Node<E> node;
	    int height;

	    public QNode(Node<E> node, int h) {
	        this.node = node;
	        this.height = h;
	    }
	}
	
	private String printTree(Node<E> root, int h, int sp) {
		
		String treeString="\n\n";
		 LinkedList<QNode<E>> queue = new LinkedList<>();
	        ArrayList<Node<E>> level = new ArrayList<>();
	        queue.add(new QNode(root, h));
	        
	        String space="";
	        for(int i=0; i<=sp; i++)
	        {
	        	space+=" ";
	        }

	        for (;;) {
	        	QNode cNode = queue.poll();

	            if (cNode.height < h) {
	                h = cNode.height;

	                for (int i = (int)Math.pow(2, h) - 1; i > 0; i--) { 
	                    //System.out.print(space);
	                    treeString+=space;
	                }

	                for (Node<E> n : level) {
	                    //System.out.print(n == null ? space : n.data);
	                    treeString+=n == null ? space : n.data;

	                    for (int i = (int)Math.pow(2, h + 1); i > 1; i--) {
	                        //System.out.print(space);
	                        treeString+=space;
	                        
	                    }
	                }

	                //System.out.println();
	                treeString+="\n\n";
	                level.clear();

	                if (cNode.height <= 0) {
	                    break;
	                }
	            }

	            level.add(cNode.node);

	            if (cNode.node == null) {
	                queue.add(new QNode(null, h - 1));
	                queue.add(new QNode(null, h - 1));
	            }
	            else {
	                queue.add(new QNode(cNode.node.left, h - 1));
	                queue.add(new QNode(cNode.node.right, h - 1));
	            }
	        }
	        return treeString;
	    }
	
	
	public String displayTree(int sp)
	{
		return printTree(root, findHeight(root), sp);
	}
}