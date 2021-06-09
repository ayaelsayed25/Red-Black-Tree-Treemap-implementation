package eg.edu.alexu.csd.filestructure.redblacktree.Trees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class TreeMap<T extends Comparable<T>,V> implements ITreeMap<T,V>{
    RedBlackTree<T,V> root = new RedBlackTree<>();
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
        return null;
    }

    @Override
    public T firstKey() {
        return null;
    }

    @Override
    public Map.Entry<T, V> floorEntry(T key) {
//        Node<T,V> node = root.Fin;
        return null;
    }

    @Override
    public T floorKey(T key) {
        return null;
    }

    @Override
    public V get(T key) {
        return null;
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
        return null;
    }

    @Override
    public Map.Entry<T, V> pollLastEntry() {
        return null;
    }

    @Override
    public void put(T key, V value) {

    }

    @Override
    public void putAll(Map<T, V> map) {

    }

    @Override
    public boolean remove(T key) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
