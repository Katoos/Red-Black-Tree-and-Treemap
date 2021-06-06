package eg.edu.alexu.csd.filestructure.redblacktree;

public class maintemp {
	public static void main(String[] args) {
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
	}
}
 
