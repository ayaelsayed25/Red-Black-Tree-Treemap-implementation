package eg.edu.alexu.csd.filestructure.redblacktree.Trees;

import javax.management.RuntimeErrorException;
import java.util.*;

public class TreeMap<T extends Comparable<T>,V> implements ITreeMap<T,V>{
    private int size =0;
    private final RedBlackTree<T,V> root = new RedBlackTree<>();
    private final TreeSet<T> keys = new TreeSet<T>();

    @Override
    public Map.Entry<T, V> ceilingEntry(T key) {
        INode node = (INode) root.search(key);
        if(node.getRightChild().getKey() != null)
            return new MapEntry<>(node.getRightChild().getKey(),node.getRightChild().getValue());
        if (node.getParent().getKey() != null)
            return new MapEntry<>(node.getParent().getKey(),node.getParent().getValue());
        else
            return null;

    }

    @Override
    public T ceilingKey(T key) {
        return ceilingEntry(key).getKey();
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root.clear();
    }

    @Override
    public boolean containsKey(T key) {
        return root.contains(key);
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public Set<Map.Entry<T, V>> entrySet() {
        return root.getEntries(root.getRoot());
    }

    @Override
    public Map.Entry<T, V> firstEntry() {
        return firstEntry(root.getRoot());
    }
    private Map.Entry<T,V> firstEntry(INode<T,V> node){
        INode<T,V> min = root.findMin(node);
        if(min == null) return null;
        return new MapEntry<>(min.getKey(),min.getValue());
    }
    @Override
    public T firstKey() {
        INode<T,V> min = root.findMin(root.getRoot());
        if(min == null)
         return null;
        return min.getKey();
    }

    /**
     * Returns a key-value mapping associated with the greatest key less than or equal to the given key, or null if there is no such key.
     * @param key
     * @return
     */
    @Override
    public Map.Entry<T, V> floorEntry(T key) {
        INode<T,V> node = root.search(root.getRoot(),key);
        if(node == null)
            return null;
        if(node.getLeftChild()!=null){
            return lastEntry(node.getLeftChild());
        }
        //TODO ask TA about this part
        INode<T,V> predecessor = new Node<>(node.getKey(), node.getValue(), false);

        while (node.getParent()!=null && node == node.getParent().getLeftChild())
            node = node.getParent();

        if(node.getParent()!=null) predecessor = predecessor.getParent();
        return new MapEntry<>(predecessor.getKey(),predecessor.getValue());
//        node = node.getParent();
//        while (node.getParent()!=null && node == node.getParent().getLeftChild())
//            node = node.getParent();
//        return new MapEntry<>(node.getKey(),node.getValue());
    }

    @Override
    public T floorKey(T key) {
        Map.Entry<T,V> predecessor = floorEntry(key);
        if(predecessor != null)
            return predecessor.getKey();
        if (this.size == 0)
            throw new NullPointerException();
        return null;
    }

    @Override
    public V get(T key) {
        return (V) this.root.search(key);
    }
    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
        if(toKey == null)
            throw new RuntimeErrorException(new Error("Key can't be Null"));
        return getHeadMap(toKey,false);
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive) {
        if(toKey == null)
            throw new RuntimeErrorException(new Error("Key can't be Null"));
        if(inclusive)
            return getHeadMap(toKey,inclusive);
        else
            return getHeadMap(toKey,inclusive);
    }

    private ArrayList<Map.Entry<T, V>> getHeadMap(T toKey, boolean inclusive){
        List<Map.Entry<T, V>> head = new ArrayList<>();
        for(Map.Entry<T, V> entry:root.getEntries(root.getRoot())){
            if(entry.getKey().compareTo(toKey)>0)
                break;
            else
                head.add(entry);
        }
        if(!inclusive)
            head.remove(head.size()-1);
        return (ArrayList) head;

    }
    /**
     * Returns a Set view of the keys contained in this map.
     * @return
     */
    @Override
    public Set<T> keySet() {
        return keys;
    }

    @Override
    public Map.Entry<T, V> lastEntry() {
        return lastEntry(root.getRoot());
    }

    public Map.Entry<T, V> lastEntry(INode<T,V> node) {
        INode<T,V> max = root.findMax(node);
        if(max == null) return null;
        return new MapEntry<>(max.getKey(),max.getValue());
    }

    @Override
    public T lastKey() {
        INode<T,V> max = root.findMax(root.getRoot());
        if(max == null)
            return null;
        return max.getKey();
    }

    @Override
    public Map.Entry<T, V> pollFirstEntry() {
        Map.Entry<T, V> first = firstEntry();
        if (first != null) {
            remove(first.getKey());
            keys.remove(first.getKey());
            size--;
            return first;
        }
        return null;
    }

    @Override
    public Map.Entry<T, V> pollLastEntry() {
        Map.Entry<T, V> last = lastEntry();
        if (last != null) {
            remove(last.getKey());
            keys.remove(last.getKey());
            size--;
            return last;
        }
        return null;
    }

    @Override
    public void put(T key, V value) {
        if (key == null){
            throw new RuntimeErrorException(new Error("Can't put null key"));
        }
        root.insert(key,value);
        size++;
        keys.add(key);
    }

    @Override
    public void putAll(Map<T, V> map) {
        if(map == null)
            throw new RuntimeErrorException(new Error("The map to copy from is null"));
        for (Map.Entry<T, V> e : map.entrySet()){
            this.put(e.getKey(), e.getValue());
        }
    }

    @Override
    public boolean remove(T key) {
        keys.remove(key);
        root.delete(key);
        size--;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        Set<Map.Entry<T,V>> entries = root.getEntries(root.getRoot());
        for(Map.Entry<T,V> entry : entries)
            values.add(entry.getValue());
        return values;
    }
}
