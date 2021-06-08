package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class maintemp {
	public static void main(String[] args) {
		/*
		IRedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>() ;
	
		tree.insert(10, 55);
		tree.insert(5, 2);
		tree.insert(3, 2);
		tree.insert(1, 5);
		tree.insert(12, 52);
		
		System.out.println(tree.contains(5)) ;
		System.out.println(tree.contains(3)) ;
		System.out.println(tree.search(3));
		System.out.println(tree.search(10));

		 */

		IRedBlackTree<Integer, String> tree = new RedBlackTree<>();
		tree.insert(3, "A");
		tree.insert(6, "B");
		tree.insert(7, "C");
		tree.insert(10, "D");
		//tree.contains(null);

		ITreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
		treeMap.put(10, "A");
		treeMap.put(7, "B");
		treeMap.put(20, "C");
		treeMap.put(13, "D");
		treeMap.put(5, "E");
		treeMap.put(9, "F");
		treeMap.put(11, "G");
		treeMap.put(24, "H");

		System.out.println(treeMap.ceilingEntry(null));
		Collection<String> valueSet = treeMap.values();
		valueSet.forEach(val -> System.out.println(val));
		//System.out.println(treeMap.containsValue("ll"));
		//treeMap.clear();

//		Map.Entry<Integer, String> entry = treeMap.floorEntry(24);
//		System.out.println(entry.getKey());
//		System.out.println(treeMap.containsValue("F"));
/*
		ArrayList<Map.Entry<Integer, String>> res=treeMap.headMap(11);
		for (Map.Entry<Integer, String> re : res) {
			System.out.print(re.getKey()+" ");
		}

 */

		//System.out.println(treeMap.pollFirstEntry().getKey());

//		Set<Integer> res=treeMap.keySet();
//		for (Integer re : res) {
//			System.out.print(re+" ");
//		}


//		RedBlackTree<Integer, String> redBlackTree = new RedBlackTree<>();
//		redBlackTree.insert(10, "A");
//		redBlackTree.insert(7, "A");
//		redBlackTree.insert(20, "A");
//		redBlackTree.insert(13, "A");
//		redBlackTree.insert(5, "A");
//		redBlackTree.insert(9, "A");
//		redBlackTree.insert(11, "A");
//		redBlackTree.insert(12, "A");
//		redBlackTree.insert(14, "A");
//		redBlackTree.insert(15, "A");
//		redBlackTree.insert(16, "A");
//		redBlackTree.insert(17, "A");
//		redBlackTree.insert(18, "A");
//		redBlackTree.insert(19, "A");
//		redBlackTree.insert(21, "A");
//		redBlackTree.search(5);
//		System.out.println(redBlackTree.getRoot().getKey());

		//String str1 = "aaa", str2 = null;
		//System.out.println(str1.compareTo(str2));

	}
}
 
