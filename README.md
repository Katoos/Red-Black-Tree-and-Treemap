# Red-Black-Tree-and-Treemap

<b>1.1 Red Black Tree</b>

A red black tree is a kind of self-balancing binary search tree in computer science. Each node of
the binary tree has an extra bit, and that bit is often interpreted as the color (red or black) of
the node. These color bits are used to ensure the tree remains approximately balanced during
insertions and deletions. Balance is preserved by painting each node of the tree with one of two
colors in a way that satisfies certain properties, which collectively constrain how unbalanced
the tree can become in the worst case. When the tree is modified, the new tree is subsequently
rearranged and repainted to restore the coloring properties. The properties are designed in
such a way that this rearranging and recoloring can be performed effciently.

<b>1.2 TreeMap</b>

A Red-Black tree based NavigableMap implementation. The map is sorted according to the
natural ordering of its keys, or by a Comparator provided at map creation time, depending on
which constructor is used. This implementation provides guaranteed log(n) time cost for the
containsKey, get, put and remove operations. Algorithms are adaptations of those in Cormen,
Leiserson, and Rivest's Introduction to Algorithms.
