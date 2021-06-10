package eg.edu.alexu.csd.filestructure.redblacktree.Trees;


public class Node<T extends Comparable<T>, V> implements INode<T,V> {

    private T key=null;
    private V value=null;
    private INode<T,V> leftChild, rightChild,parent;
    private boolean color=false;      //Red is True, Black is False


    public Node(T key, V value, boolean color){
        this.key = key;
        this.value = value;
        this.color = color;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    public Node() {}

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
        this.rightChild =rightChild;
    }

    @Override
    public INode<T,V> getRightChild() {
        return this.rightChild;
    }

    @Override
    public T getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
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
    @Override
    public INode<T,V> getUncle(){
        INode<T,V> uncle;
        INode<T,V> grandParent = this.getParent().getParent();

        if (grandParent.isRightChild(this.getParent())){
            uncle = grandParent.getLeftChild();
        }
        else{
            uncle = grandParent.getRightChild();
        }
        return uncle;
    }

    @Override
    public boolean isRightChild(INode<T, V> node){
        return node == this.rightChild;
    }
    @Override
    public boolean isChildLeft(){
        if(this.parent == null)
            return false;
        return this.equals(this.parent.getLeftChild());
    }
    @Override
    public INode<T, V> getSibling()
    {
        if(isChildLeft())
            return parent.getRightChild();
        else
            return parent.getLeftChild();
    }
}