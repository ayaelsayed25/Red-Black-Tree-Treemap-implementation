package eg.edu.alexu.csd.filestructure.redblacktree.Trees;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface ITreeMap<T extends Comparable<T>, V> {

    /**
     * Returns a key-value mapping associated with the least key greater than or equal to the given key, or null if there is no such key.
     * @param key
     * @return
     */
    public Map.Entry<T, V> ceilingEntry(T key);

    /**
     * Returns the least key greater than or equal to the given key, or null if there is no such key.
     * @param key
     * @return
     */
    public T ceilingKey(T key);

    /**
     * Removes all of the mappings from this map.
     */
    public void clear();

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @param key
     * @return
     */
    public boolean containsKey(T key);


    /**
     * Returns true if this map maps one or more keys to the specified value.
     * @param value
     * @return
     */
    public boolean containsValue(V value);

    /**
     * Returns a Set view of the mappings contained in this map in ascending key order.
     * @return
     */
    public Set<Map.Entry<T,V>> entrySet();

    /**
     * Returns a key-value mapping associated with the least key in this map, or null if the map is empty.
     * @return
     */
    public Map.Entry<T, V> firstEntry();

    /**
     * Returns the first (lowest) key currently in this map, or null if the map is empty.
     * @return
     */
    public T firstKey();

    /**
     * Returns a key-value mapping associated with the greatest key less than or equal to the given key, or null if there is no such key.
     * @param key
     * @return
     */
    public Map.Entry<T, V> floorEntry(T key);

    /**
     * Returns the greatest key less than or equal to the given key, or null if there is no such key.
     * @param key
     * @return
     */
    public T floorKey(T key);

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key
     * @return
     */
    public V get(T key);

    /**
     * Returns a view of the portion of this map whose keys are strictly less than toKey in ascending order..
     * @param toKey
     * @return
     */
    public ArrayList<Map.Entry<T, V>> headMap(T toKey);

    /**
     * Returns a view of the portion of this map whose keys are less than (or equal to, if inclusive is true) toKey in ascending order..
     * @param toKey
     * @param inclusive
     * @return
     */
    public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive);

    /**
     * Returns a Set view of the keys contained in this map.
     * @return
     */
    public Set<T> keySet();

    /**
     * Returns a key-value mapping associated with the greatest key in this map, or null if the map is empty.
     * @return
     */
    public Map.Entry<T, V> lastEntry();

    /**
     * Returns the last (highest) key currently in this map.
     * @return
     */
    public T lastKey();

    /**
     * Removes and returns a key-value mapping associated with the least key in this map, or null if the map is empty.
     * @return
     */
    public Map.Entry<T, V> pollFirstEntry();

    /**
     *	Removes and returns a key-value mapping associated with the greatest key in this map, or null if the map is empty.
     * @return
     */
    public Map.Entry<T, V> pollLastEntry();

    /**
     * Associates the specified value with the specified key in this map.
     * @param key
     * @param value
     */
    public void put(T key, V value);

    /**
     * Copies all of the mappings from the specified map to this map.
     * @param map
     */
    public void putAll(Map<T, V> map);

    /**
     * Removes the mapping for this key from this TreeMap if present.
     * @param key
     * @return true if removed and false otherwise.
     */
    public boolean remove(T key);

    /**
     * Returns the number of key-value mappings in this map.
     * @return
     */
    public int size();

    /**
     * Returns a Collection view of the values contained in this map.
     * @return
     */
    public Collection<V> values();

}