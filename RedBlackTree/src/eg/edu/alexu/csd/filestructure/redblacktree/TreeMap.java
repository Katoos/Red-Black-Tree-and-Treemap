package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;
import java.util.*;

public class TreeMap <T extends Comparable<T>, V> implements ITreeMap <T, V>{

    private RedBlackTree<T, V> rbTree;
    private Set<Map.Entry<T, V>> set;
    private boolean flagIsFound, flagIsNull;
    //private final INode<T, V> nil = new Node<>(true);

    public TreeMap() {
        this.rbTree = new RedBlackTree<T, V>();
        this.set = new LinkedHashSet<>();
        this.flagIsFound = false;
        this.flagIsNull = false;
    }

    public Map.Entry<T, V> castToMapEntry(INode<T, V> node) {
        if (node == null)
            return null;
        Map.Entry<T, V> entry = new Map.Entry<T, V>() {

            @Override
            public boolean equals(Object obj) {
                return this.getKey().equals(((Map.Entry<T, V>)obj).getKey()) &&
                        this.getValue().equals(((Map.Entry<T, V>)obj).getValue());
            }

            @Override
            public T getKey() {
                return node.getKey();
            }

            @Override
            public V getValue() {
                return node.getValue();
            }

            @Override
            public V setValue(V value) {
                V oldValue = node.getValue();
                node.setValue(value);
                return oldValue;
            }
        };
        return entry;
    }

    public INode<T, V> searchHelper (T key) {
        INode<T, V> temp = rbTree.getRoot();
        while (temp != rbTree.getNil()) {
            if (key.compareTo(temp.getKey()) > 0) {
                if (temp.getRightChild().isNull()) {
                    flagIsNull = true;
                    return temp;
                }
                else {
                    temp = temp.getRightChild();
                }
            }
            else if (key.compareTo(temp.getKey()) < 0) {
                if (temp.getLeftChild().isNull()) {
                    flagIsNull = true;
                    return temp;
                }
                else {
                    temp = temp.getLeftChild();
                }
            }
            else {
                break;
            }
        }
        return temp;
    }

    @Override
    public Map.Entry<T, V> ceilingEntry(T key) {
        if (key == null)
            throw new RuntimeErrorException(new Error());
        flagIsNull = false;
        INode<T, V> current = searchHelper(key);
        // key found.
        if (!flagIsNull)
            return castToMapEntry(current);
        // otherwise we have 2 cases.
        // current (parent of the the key if it had been inserted) is greater than the key.
        // like assuming that the key was virtually inserted (left Of current) then return its parent (current).
        if (current.getKey().compareTo(key) > 0)
            return castToMapEntry(current);
        // current (parent of the the key if it had been inserted) is less than the key.
        // go upwards until find a left child and return its parent.
        short status = statusOf(current);
        while (status == 3) {
            current = current.getParent();
            status = statusOf(current);
        }
        if (status == 2)
            return castToMapEntry(current.getParent());
        // if no left child is found then No ceiling entry exists.
        return null;
    }

    public INode<T, V> getMin(INode<T, V> root) {
        if (root.equals(rbTree.getNil()))
            return null;
        while (!root.getLeftChild().equals(rbTree.getNil())){
            root = root.getLeftChild();
        }
        return root;
    }

    public INode<T, V> getMax(INode<T, V> root) {
        if (root.equals(rbTree.getNil()))
            return null;
        while (!root.getRightChild().equals(rbTree.getNil())){
            root = root.getRightChild();
        }
        return root;
    }

    //

    public short statusOf (INode<T, V> current) {
        // root.
        if (current.getParent().isNull())
            return 1;
        // left child.
        if (!current.equals(rbTree.getNil()) && current.getParent().getKey().compareTo(current.getKey()) > 0)
            return 2;
        // right child.
        else if(current.getParent().getKey().compareTo(current.getKey()) < 0 && !current.equals(rbTree.getNil()))
        	return 3;
        return 1 ;
    }

    @Override
    public T ceilingKey(T key) {
        Map.Entry<T, V> ceil = ceilingEntry(key);
        if (ceil != null)
            return ceil.getKey();
        return null;
    }

    @Override
    public void clear() {
        rbTree.clear();
    }

    @Override
    public boolean containsKey(T key) {
        return rbTree.contains(key);
    }

    public void InorderTraversal (INode<T, V> root) {
        if (root.equals(rbTree.getNil()))
            return;
        InorderTraversal(root.getLeftChild());
        set.add(castToMapEntry(root));
        InorderTraversal(root.getRightChild());
    }

    public void InorderTraversal (INode<T, V> root, V value) {
        if (root.equals(rbTree.getNil()))
            return;
        InorderTraversal(root.getLeftChild(), value);
        if (root.getValue().equals(value)){
            flagIsFound = true;
            return;
        }
        InorderTraversal(root.getRightChild(), value);
    }


    @Override
    public boolean containsValue(V value) {
        if (value == null)
            throw new RuntimeErrorException(new Error());
        flagIsFound = false;
        InorderTraversal(rbTree.getRoot(), value);
        return flagIsFound;
    }

    @Override
    public Set<Map.Entry<T, V>> entrySet() {
        set.clear();
        InorderTraversal(rbTree.getRoot());
        return set;
    }

    @Override
    public Map.Entry<T, V> firstEntry() {
        return castToMapEntry(getMin(rbTree.getRoot()));
    }

    @Override
    public T firstKey() {
        Map.Entry<T, V> first = firstEntry();
        if (first != null)
            return first.getKey();
        return null;
    }

    @Override
    public Map.Entry<T, V> floorEntry(T key) {
        if (key == null)
            throw new RuntimeErrorException(new Error());
        flagIsNull = false;
        INode<T, V> current = searchHelper(key);
        // key found.
        if (!flagIsNull)
            return castToMapEntry(current);
        // otherwise we have 2 cases.
        // current (parent of the the key if it had been inserted) is less than the key.
        // like assuming that the key was virtually inserted (right Of current) then return its parent (current).
        if (current.getKey().compareTo(key) < 0)
            return castToMapEntry(current);
        // current (parent of the the key if it had been inserted) is greater than the key.
        // go upwards until find a right child and return its parent.
        short status = statusOf(current);
        while (status == 2) {
            current = current.getParent();
            status = statusOf(current);
        }
        if (status == 3)
            return castToMapEntry(current.getParent());
        // if no right child is found then No floor entry exists.
        return null;
    }

    @Override
    public T floorKey(T key) {
        Map.Entry<T, V> floor = floorEntry(key);
        if (floor != null)
            return floor.getKey();
        return null;
    }

    @Override
    public V get(T key) {
        return rbTree.search(key);
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
        if (toKey == null)
            throw new RuntimeErrorException(new Error());
        ArrayList<Map.Entry<T, V>> result = new ArrayList<>();
        traversToKey(rbTree.getRoot(),toKey,result);
        result.remove(result.size()-1);
        return result;
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive) {
        ArrayList<Map.Entry<T, V>> result = new ArrayList<>();
        traversToKey(rbTree.getRoot(),toKey,result);
        return result;
    }

    private void traversToKey( INode<T, V> root, T key, ArrayList<Map.Entry<T, V>> result){
        if(root.isNull())
            return;
        traversToKey(root.getLeftChild(),key,result);
        if(key.compareTo(root.getKey()) >=0)
            result.add(castToMapEntry(root));
        traversToKey(root.getRightChild(),key,result);
    }

    @Override
    public Set<T> keySet() {
        Set<T> result = new LinkedHashSet<>();
        set = entrySet();
        for (Map.Entry<T, V> entry : set) {
            result.add(entry.getKey());
        }
        return result;
    }

//    private void inorderTraversal( INode<T, V> root, Set<T> result){
//        if(root.isNull())
//            return;
//        inorderTraversal(root.getLeftChild(),result);
//        result.add(root.getKey());
//        inorderTraversal(root.getRightChild(),result);
//    }

    @Override
    public Map.Entry<T, V> lastEntry() {
        return castToMapEntry(getMax(rbTree.getRoot()));
    }

    @Override
    public T lastKey() {
        Map.Entry<T, V> last = lastEntry();
        if (last != null)
            return last.getKey();
        return null;
    }

    @Override
    public Map.Entry<T, V> pollFirstEntry() {
        if(rbTree.isEmpty())
            return null;
        Map.Entry<T,V>result= castToMapEntry(getMin(rbTree.getRoot()));
        remove(result.getKey());
        return result;
    }

    @Override
    public Map.Entry<T, V> pollLastEntry() {
        if(rbTree.isEmpty())
            return null;
        Map.Entry<T,V>result= castToMapEntry(getMax(rbTree.getRoot()));
        remove(result.getKey());
        return result;
    }

    @Override
    public void put(T key, V value) {
        rbTree.insert(key, value);
    }

    @Override
    public void putAll(Map<T, V> map) {
        if (map == null)
            throw new RuntimeErrorException(new Error());
    	for(Map.Entry<T,V> entry : map.entrySet()) {
    		  put(entry.getKey() , entry.getValue());
    	}
    }

    @Override
    public boolean remove(T key) {
        return rbTree.delete(key);
    }

    @Override
    public int size() {
        return rbTree.size();
    }

    @Override
    public Collection<V> values() {
        Collection<V> result = new LinkedHashSet<>();
        set = entrySet();
        for (Map.Entry<T, V> entry : set) {
            result.add(entry.getValue());
        }
        return result;
    }

//    private void inorderTraversal( INode<T, V> root, Collection<V> result){
//        if(root.isNull())
//            return;
//        inorderTraversal(root.getLeftChild(),result);
//        result.add(root.getValue());
//        inorderTraversal(root.getRightChild(),result);
//    }
    
    
}
