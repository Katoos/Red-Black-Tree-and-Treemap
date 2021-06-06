package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.*;

public class TreeMap <T extends Comparable<T>, V> implements ITreeMap <T, V>{

    private RedBlackTree<T, V> rbTree;
    private Set<Map.Entry<T, V>> set;
    private boolean flag;

    public TreeMap() {
        this.rbTree = new RedBlackTree<T, V>();
        this.set = new LinkedHashSet<>();
        this.flag = false;
    }

    public Map.Entry<T, V> castToMapEntry(INode<T, V> node) {
        Map.Entry<T, V> entry = new Map.Entry<T, V>() {
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

    @Override
    public Map.Entry<T, V> ceilingEntry(T key) {
        INode<T, V> current = rbTree.searchHelper(rbTree.getRoot(), key);
        // key not found or tree is empty.
        if (current == null)
            return null;
        // get min in the right subtree.
        if (current.getRightChild() != null) {
            return castToMapEntry(getMin(current.getRightChild()));
        }
        short status = statusOf(current);
        // case no right subtree.
        // if it is left child return parent.
        if (status == 2)
            return castToMapEntry(current.getParent());
        // otherwise go up until find a left child and return its parent.
        while (status == 3) {
            current = current.getParent();
            status = statusOf(current);
        }
        if (status == 2)
            return castToMapEntry(current.getParent());
        // if no left child is found then it's the max entry.
        return null;
    }

    public INode<T, V> getMin(INode<T, V> root) {
        if (root == null)
            return null;
        while (root.getLeftChild() != null){
            root = root.getLeftChild();
        }
        return root;
    }

    public INode<T, V> getMax(INode<T, V> root) {
        if (root == null)
            return null;
        while (root.getRightChild() != null){
            root = root.getRightChild();
        }
        return root;
    }


    public short statusOf (INode<T, V> current) {
        // root.
        if (current.getParent() == null)
            return 1;
        // left child.
        if (current.getParent().getKey().compareTo(current.getKey()) > 0)
            return 2;
        // right child.
        return 3;
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
        if (root == null)
            return;
        InorderTraversal(root.getLeftChild());
        set.add(castToMapEntry(root));
        InorderTraversal(root.getRightChild());
    }

    public void InorderTraversal (INode<T, V> root, V value) {
        if (root == null)
            return;
        InorderTraversal(root.getLeftChild(), value);
        if (root.getValue().equals(value)){
            flag = true;
            return;
        }
        InorderTraversal(root.getRightChild(), value);
    }


    @Override
    public boolean containsValue(V value) {
        flag = false;
        InorderTraversal(rbTree.getRoot(), value);
        return flag;
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
        INode<T, V> current = rbTree.searchHelper(rbTree.getRoot(), key);
        // key not found or tree is empty.
        if (current == null)
            return null;
        // get max in the left subtree.
        if (current.getLeftChild() != null) {
            return castToMapEntry(getMax(current.getLeftChild()));
        }
        short status = statusOf(current);
        // case no left subtree.
        // if it is right child return parent.
        if (status == 3)
            return castToMapEntry(current.getParent());
        // otherwise go up until find a right child and return its parent.
        while (status == 2) {
            current = current.getParent();
            status = statusOf(current);
        }
        if (status == 3)
            return castToMapEntry(current.getParent());
        // if no left child is found then it's the min entry.
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
        return null;
    }

    @Override
    public Map.Entry<T, V> pollLastEntry() {
        return null;
    }

    @Override
    public void put(T key, V value) {
        rbTree.insert(key, value);
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
