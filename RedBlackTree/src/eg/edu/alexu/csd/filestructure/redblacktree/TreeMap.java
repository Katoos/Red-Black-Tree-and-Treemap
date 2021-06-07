package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.*;

public class TreeMap <T extends Comparable<T>, V> implements ITreeMap <T, V>{

    private RedBlackTree<T, V> rbTree;
    private Set<Map.Entry<T, V>> set;
    private boolean flag;
    private final INode<T, V> nil = new Node<>(true);
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
        INode<T, V> current = rbTree.searchHelper(key);
        // key not found or tree is empty.
        if (current == null || current == nil)
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
        if (root == null || root==nil )
            return null;
        while (root.getLeftChild() != null || root.getLeftChild() != nil){
            root = root.getLeftChild();
        }
        return root;
    }

    public INode<T, V> getMax(INode<T, V> root) {
        if (root == null ||root == nil)
            return null;
        while (root.getRightChild() != nil ){
            root = root.getRightChild();
        }
        return root;
    }


    public short statusOf (INode<T, V> current) {
        // root.
        if (current.getParent() == null || current.getParent() == nil)
            return 1;
        // left child.
        if (current.getParent().getKey().compareTo(current.getKey()) > 0 && current != nil)
            return 2;
        // right child.
        else if(current.getParent().getKey().compareTo(current.getKey()) < 0 && current != nil)
        	return 3;
        return 1 ;
    }

    @Override
    public T ceilingKey(T key) {
        Map.Entry<T, V> ceil = ceilingEntry(key);
        if (ceil != null || ceil != nil)
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
        if (root == null || root == nil)
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
        if (first != null || first != nil)
            return first.getKey();
        return null;
    }

    @Override
    public Map.Entry<T, V> floorEntry(T key) {
        INode<T, V> current = rbTree.searchHelper( key);
        // key not found or tree is empty.
        if (current == null || current==nil)
            return null;
        // get max in the left subtree.
        if (current.getLeftChild() != null  ) {
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
        if (floor != null ||floor!=nil)
            return floor.getKey();
        return null;
    }

    @Override
    public V get(T key) {
        return rbTree.search(key);
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
        ArrayList<Map.Entry<T, V>> result=new ArrayList<>();
        traversToKey(rbTree.getRoot(),toKey,result);
        result.remove(result.size()-1);
        return result;
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive) {
        ArrayList<Map.Entry<T, V>> result=new ArrayList<>();
        traversToKey(rbTree.getRoot(),toKey,result);
        return result;
    }

    private void traversToKey( INode<T, V> root, T key, ArrayList<Map.Entry<T, V>> result){
        if(root==null)
            return;
        traversToKey(root.getLeftChild(),key,result);
        if(key.compareTo(root.getKey()) >=0)
            result.add(castToMapEntry(root));
        traversToKey(root.getRightChild(),key,result);
    }

    @Override
    public Set<T> keySet() {
        Set<T> result=new LinkedHashSet<>();
        inorderTraversal(rbTree.getRoot(),result);
        return result;
    }

    private void inorderTraversal( INode<T, V> root, Set<T> result){
        if(root==null||root==nil)
            return;
        inorderTraversal(root.getLeftChild(),result);
        result.add(root.getKey());
        inorderTraversal(root.getRightChild(),result);
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
        if(rbTree.getRoot()==null||rbTree.getRoot()==nil)return null;
        Map.Entry<T,V>result= castToMapEntry(getMin(rbTree.getRoot()));
        remove(result.getKey());
        return result;
    }

    @Override
    public Map.Entry<T, V> pollLastEntry() {
        if(rbTree.getRoot()==null||rbTree.getRoot()==nil)return null;
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
