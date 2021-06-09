package eg.edu.alexu.csd.filestructure.redblacktree.Trees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class TreeMap<T extends Comparable<T>,V> implements ITreeMap<T,V>{
    RedBlackTree<T,V> root = new RedBlackTree<>();
    private int size =0;
    @Override
    public Map.Entry<T, V> ceilingEntry(T key) {
        return null;
    }

    @Override
    public T ceilingKey(T key) {
        return null;
    }

    @Override
    public void clear() {
        this.size = 0;
    }

    @Override
    public boolean containsKey(T key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public Set<Map.Entry<T, V>> entrySet() {
        return null;
    }

    @Override
    public Map.Entry<T, V> firstEntry() {
        INode<T,V> min = root.findMin(root.getRoot());
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

    @Override
    public Map.Entry<T, V> floorEntry(T key) {
        INode<T,V> node = root.search(root.getRoot(),key);
        if(node == null)
            return null;

        return null;
    }

    @Override
    public T floorKey(T key) {
        return null;
    }

    @Override
    public V get(T key) {
        return this.root.search(key);
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
        return null;
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive) {
        return null;
    }

    @Override
    public Set<T> keySet() {
        return null;
    }

    @Override
    public Map.Entry<T, V> lastEntry() {
        return null;
    }

    @Override
    public T lastKey() {
        return null;
    }

    @Override
    public Map.Entry<T, V> pollFirstEntry() {
        //Note don't forget to decrease size if removed
        size--;
        return null;
    }

    @Override
    public Map.Entry<T, V> pollLastEntry() {
        size--;
        return null;
    }

    @Override
    public void put(T key, V value) {
        //don't forget to increase size
        size++;
    }

    @Override
    public void putAll(Map<T, V> map) {
        if(map == null)
            throw new NullPointerException("The map to copy from is null");
        for (Map.Entry<T, V> e : map.entrySet()){
            this.put(e.getKey(), e.getValue());
            size++;
        }
    }

    @Override
    public boolean remove(T key) {
        //don't forget to decrease size
        size--;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
