import Interfaces.INode;

public class Node<T extends Comparable<T>, V> implements INode<T,V> {

    private T key;
    private V value;
    private INode<T,V> leftChild, rightChild,parent;
    private boolean color;

    public Node(){}
    /*
    public  Node(V value,INode leftChild,INode rightChild,INode parent){
        this.value=value;
        this.leftChild=leftChild;
        this.rightChild=rightChild;
        this.parent=parent;
    }*/
    @Override
    public void setParent(INode<T,V> parent) {
        this.parent = parent;
    }

    @Override
    public INode<T,V> getParent() {
        return this.parent;
    }

    @Override
    public void setLeftChild(INode<T,V> leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public INode<T,V> getLeftChild() {
        return this.leftChild;
    }

    @Override
    public void setRightChild(INode<T,V> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public INode<T,V> getRightChild() {
        return this.rightChild;
    }

    @Override
    public T getKey() {
        return null;
    }

    @Override
    public V getValue() {
        return null;
    }


    @Override
    public void setKey(T key) {
        this.key = key;
    }


    @Override
    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean getColor() {
        return this.color;
    }

    @Override
    public void setColor(boolean color) {
        this.color = color;
    }

    @Override
    public boolean isNull() {
        return this.key == null;
    }
    public boolean isLeftChild(INode<T,V> node){
        //TODO is == right ?
        if(node==this.leftChild)
            return true;
        else
            return false;
    }
    public boolean isRightChild(INode<T,V> node){
        if(node==this.rightChild)
            return true;
        else
            return false;
    }
    public boolean isChildLeft(){
        if(this.parent == null)
            return false;
        return this.equals(this.parent.getLeftChild());
    }
    public INode<T, V> getSibling()
    {
        if(isChildLeft())
            return parent.getRightChild();
        else
            return parent.getLeftChild();
    }
}