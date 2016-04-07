#Algorithm

**Introduction** This is algorithm learning based on java. The material is *Algorithm: Fourth Edition* written by Robert Sedgewick and Kevin Wayne. You can find the sources on http://algs4.cs.princeton.edu/home/, and you can find courses on https://www.coursera.org/course/algs4partI and https://www.coursera.org/course/algs4partII. In this learning program, we use notepad++ with nppexec to run java.

**Java** You can download java and textbook libraries from http://algs4.cs.princeton.edu/code/. If you are not good at Java, you can follow another introductory text, *An Intruduction to Programming in Java: An Interdisciplinary Approach*.

**Notepad++** We use notepad++ nothing but cool.

**Nppexec** 
    
    NPP_SAVE
    javac -encoding UTF-8 "$(FULL_CURRENT_PATH)"
    echo
    echo ==========编译成功后开始运行========== 
    java -cp "$(CURRENT_DIRECTORY);D:\Program Files\java\jdk\lib\algs4.jar" "$(NAME_PART)"
    

**Eclipse** 
 - Installation: You can download from the official website http://www.eclipse.org/downloads/, to choose between 32 bit and 64 bit, it depends on your JAVA VERSION instead of your computer,  http://stackoverflow.com/questions/11461607/cant-start-eclipse-java-was-started-but-returned-exit-code-13 is very helpful! 
 - Create a project: To start with the first project helloworld using Eclipse, you can refer to http://jingyan.baidu.com/article/48b558e373e1ea7f39c09a57.html. 
 - Import an existing project: You can follow "File"→"Import"→"General"→"Existing Projects into Workspace".

**Push What** We will push some codes based on this book or our discussion.

**Somthing about Data Structure and Algorithm** There are many different abstract data types in Java's Algorithm, such as queue, stack, bag, sort, symbol table (or search). But actually they are all based on two basic types: one by one structure, and tree structure.
 - One by One
   - Array
   - Linked List
 - Tree
   - Forest of Tree
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
   - Graph.java
```java
    import a;
    
    public class b
   {
    }
```
