import Interfaces.INode;
import Interfaces.IRedBlackTree;

import java.security.Key;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree<T,V> {

    INode<T,V> root = new Node<>();

    @Override
    public INode<T,V> getRoot() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public V search(T key) {
        return null;
    }

    @Override
    public boolean contains(T key) {
        return false;
    }

    @Override
    public void insert(Comparable key, Object value) {
        if(root == null) {
            root.setKey(key);
            root.setColor(false);
            root.setValue(value);
            return;
        }
        INode newNode = new Node(key,value,true); //New Node Creation
        insertNode(this.root,newNode);                  //BST Insertion
        insertCases(newNode);                           //Insertion Cases
    }
    /**
     * Insert new Node in Binary Search Tree
     * @param newNode
     */
    private void insertNode(INode root,INode newNode) {
        //Base Condition
        if(root == null) {
            newNode.setParent(root.getParent());
            root = newNode;
        }
        if(newNode.getKey().compareTo(root.getKey()) > 0 )    //newNode > root
            insertNode(root.getRightChild(),newNode);
        else if (newNode.getKey().compareTo(root.getKey()) < 0) //newNode < root
            insertNode(root.getLeftChild(),newNode);
    }

    private void insertCases(INode newNode){
        //Easy Case , Parent is Black
        if(!newNode.getParent().getColor())
            return;
        boolean uncleColor = getUncleColor(newNode);
        boolean parentDirection = false ,childDirection = false ;   // Right  --> True , Left --> false
        //Check whether it has grandparent or not
        Node parent = (Node) newNode.getParent();
        Node grandParent = (Node) newNode.getParent().getParent();
        if (grandParent.isRightChild(parent))
            parentDirection = true;
        if(parent.isRightChild(newNode))
            childDirection = true;
        //Case 1: Uncle is red
        if (uncleColor) {
            recoloring(newNode);
            return;
        }
        if(parentDirection && childDirection)
            insertRightRight(newNode);
        else if (!parentDirection && childDirection)
            insertLeftRight(newNode);
        else if(parentDirection && !childDirection)
            insertRightLeft(newNode);
        else
            insertLeftLeft(newNode);
    }
    private boolean getUncleColor(INode newNode ){
        INode uncle = ((Node)newNode).getUncle();
        boolean uncleColor;
        if(uncle != null)
            uncleColor = uncle.getColor();
        else
            uncleColor = false;
        return uncleColor;
    }

    private void recoloring(INode newNode) {
        INode grandParent = newNode.getParent().getParent();
        grandParent.setColor(true);
        newNode.getParent().setColor(false);
        ((Node)newNode).getUncle().setColor(false);
        if (grandParent.getParent() != null) {
            if(grandParent.getParent().getColor())
                insertCases(grandParent);
            else
                return;
        }
        else{
            grandParent.setColor(false);
            return;
        }

    }
    //Case 3: Uncle is Black, Inserted Node is a left child
    /*
     * Parent is left , right rotate
     * Parent is right , left rotate
     */
    private void insertLeftLeft(INode newNode){
        newNode.getParent().setColor(false);
        newNode.getParent().getParent().setColor(true);
        rotateRight(newNode.getParent().getParent());
    }
    private void insertRightRight(INode newNode){
        newNode.getParent().setColor(false);
        newNode.getParent().getParent().setColor(true);
        rotateLeft(newNode.getParent().getParent());
    }
    //Case Left Right : Uncle is Black, Inserted Node is a right child and its parent is a left child
    /*
     * Parent is left , parent left rotate
     * Go to case Left Left
     */
    private void insertLeftRight(INode newNode){
        rotateLeft(newNode.getParent());
        insertLeftLeft(newNode);
    }
    //Case Right Left : Uncle is Black, Inserted Node is a left child and its parent is a right child
    /*
     * Parent is right , parent right rotate
     * Go to Case Right Right
     */
    private void insertRightLeft(INode newNode){
        rotateRight(newNode.getParent());
        insertRightRight(newNode);
    }


    @Override
    public boolean delete(T key) {
        return delete( Find(root,key));

    }
    private boolean delete(INode<T,V> deletedNode){
        if(deletedNode == null)
            return false;
        //TODO if parent is null (root case) //1
        if(deletedNode.getLeftChild() == null && deletedNode.getRightChild() == null){
            if(!deletedNode.getColor()){
                doubleBlack(deletedNode);
            }
            if(((Node<T,V>)deletedNode).isChildLeft())
                deletedNode.getParent().setLeftChild(null);
            else
                deletedNode.getParent().setRightChild(null);
        }
        else if(deletedNode.getLeftChild() == null){
            if(!deletedNode.getColor())
                deletedNode.getRightChild().setColor(false);
            deletedNode.getParent().setRightChild(deletedNode.getRightChild());
        }
        else if(deletedNode.getRightChild() == null){
            if(!deletedNode.getColor())
                deletedNode.getLeftChild().setColor(false);
            deletedNode.getParent().setLeftChild(deletedNode.getLeftChild());
        }
        else {
            INode<T,V> predecessor = findMin(deletedNode.getRightChild());
            deletedNode.setValue(predecessor.getValue());
            delete(predecessor);
        }
        return true;
    }

    /**
     * Rotate subtree at a given node to the left
     * used in insertion and deletion
     *
     * @param rotateNode
     */
    public void rotateLeft(INode<T,V> rotateNode) {
        INode<T,V> node = rotateNode.getRightChild();         //Right Child of The rotate node
        rotateNode.setRightChild(node.getLeftChild());
        //Check Whether the right node of the rotate node has left child or not
        if (node.getLeftChild() != null) {
            node.getLeftChild().setParent(rotateNode);
        }
        node.setLeftChild(rotateNode);
        setParentRotation(node,rotateNode);
    }


    /**
     * Rotate subtree at a given node to the right
     * used in insertion and deletion
     *
     * @param rotateNode
     */
    public void rotateRight(INode<T,V> rotateNode) {
        INode<T,V> node = rotateNode.getLeftChild();         //Left Child of The rotate node
        rotateNode.setLeftChild(node.getRightChild());
        //Check Whether the left node of the rotate node has right child or not
        if (node.getRightChild() != null) {
            node.getRightChild().setParent(rotateNode);
        }
        node.setRightChild(rotateNode);
        setParentRotation(node,rotateNode);
    }
    private void setParentRotation(INode<T,V> node,INode<T,V> rotateNode){
        node.setParent(rotateNode.getParent());
        /* Check if the given node to be rotated has parent or not
         * if it has parent , then set the parent child to node
         * it checks whether the rotate node is right or left .
         */
        if (rotateNode.getParent() != null) {
            if (((Node<T,V>) rotateNode.getParent()).isRightChild(rotateNode))
                rotateNode.getParent().setRightChild(node);
            else
                rotateNode.getParent().setLeftChild(node);
        } else
            this.root = node;
        rotateNode.setParent(node);
    }

    private INode<T,V> Find(INode<T,V> root,T key){
        if(root == null)
            return null;
        if(key.compareTo(root.getKey()) < 0)
             return Find(root.getLeftChild(),key);
        else if(key.compareTo(root.getKey()) > 0)
            return Find(root.getRightChild(),key);
        return root;
    }
    private INode<T,V> findMin(INode<T,V> node){
        //TODO implement find min //1
        return null;
    }
    private void doubleBlack(INode<T,V> node) {
        //CASE 0: IF DB IS ROOT
        if(node.getParent() == null)
            return;
        INode<T, V> sibling = ((Node)node).getSibling();
        //CASE 1: IF SIBLING IS RED
        if(sibling.getColor())
        {
            node.getParent().setColor(true);
            sibling.setColor(false);
            if(((Node<T, V>) node).isChildLeft()) rotateLeft(node.getParent());
            else rotateRight(node.getParent());
            doubleBlack(node);
        }
        //CASE 2: IF SIBLING IS BLACK AND BOTH CHILDREN ARE BLACK
        else if(checkCase2(sibling))
        {
            INode<T, V> parent = node.getParent();
            if(parent.getColor())
            {
                parent.setColor(false);
                sibling.setColor(true);
                return;
            }
            else
            {
                sibling.setColor(true);
                doubleBlack(parent);
            }
        }
        return;
        // if red sibling {rotate} //2
        //if black sibling + no red children  {recolor and recurse} //2
        // if black sibling + at least one red child{near & far > rotations} //1
        // sarah 1
        // aya 2
    }
    private boolean checkCase2(INode<T, V> sibling)
    {
        if(sibling.getLeftChild() == null && sibling.getRightChild() == null)
            return true;
        else if(sibling.getRightChild() == null)
        {
            if(!sibling.getLeftChild().getColor())
                return true;
        }
        else if(sibling.getLeftChild() == null)
        {
            if(!sibling.getRightChild().getColor())
                return true;
        }
        else if(!sibling.getRightChild().getColor() && !sibling.getLeftChild().getColor())
            return true;
        return false;
    }

}