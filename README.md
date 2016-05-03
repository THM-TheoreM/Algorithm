# Algorithm

**Introduction** This is algorithm learning based on java. The material is *Algorithm: Fourth Edition* written by Robert Sedgewick and Kevin Wayne. You can find the sources on http://algs4.cs.princeton.edu/home/, and you can find courses on https://www.coursera.org/course/algs4partI and https://www.coursera.org/course/algs4partII.

**Textbook Library** You can download textbook libraries from http://algs4.cs.princeton.edu/code/.

**Push What** We will push some codes based on this book or our discussion.

**Somthing about Data Structure and Algorithm** There are many different abstract data types in Java's Algorithm, such as queue, stack, bag, sort, symbol table (or search). But actually they are all based on two basic types: one by one structure, and tree structure.
 - One by One
   - Array
   - Linked List
 - Tree
   - Binary Tree(二叉树)
   - Heap-Ordered Binary Tree: key in each node is larger than or equal to the key in that node's two children(if any)
   - Binary Heap(二叉堆): complete heap-ordered binary tree
   - Binary Search Tree(二叉搜索树): key in any node is larger that the keys in all nodes in that node's left subtree and smaller than the keys in all nodes in that node's right subtree
   - Red-black Binary Search Tree
   - 2-3 Tree
   - 2-3 Search Tree: Page424
   - Balanced 2-3 Search Tree: 2-3 tree whose null links are all the same distance from the root
   - BTree
 - Graph
   - UF
   - Graph\DFS\DFP\BFP\CC\Cycle\Bipartite\BipartiteX\BipartiteMatching\GraphGenerator\EulerianPath\EullerianCycle
   - SymbolGraph\DegreeOfSeparation
   - Digraph\DDFS\DFDP\BFDP\SCC\DirectCycle\DirectCycleX\DepthFirstOrder\Topological\TopologicalX\TransitiveClosure\DiGraphGenerator\DirectEulerianPath\DirectEulerianCycle
   - SymbolDiGraph
   - Edge\EdgeWeightedGraph\EdgeWeightDirectedCycle\LazyPrimeMST\PrimeMST\KruskalMST\DijkstraUndirectSP
   - DirectEdge\EdgeWeightedDigraph\AdjMatrixEdgeWeightDiGraph\DijkstraSP\DijkstraAllPairSP\AcyclicLP\AcyclicSP\BellmanFordSP
```java
     public static int numberOfSelfLoops(Graph G)
     {
        int count=0;
	    for(int v=0;v<G.V();v++)
		    for(int w: G.adj(v))
			    if(w==v) count++;
		    return count/2;
     }
```
```java
     public static int numberOfParallelEdges(Graph G, int v, int w)
     {
	    int count=0;b
	    for(int i: G.adj(v))
		    if(i==w) count++;
	    return count;
     }
```