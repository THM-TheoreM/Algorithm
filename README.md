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
    
**Push What** We will push some codes based on this book or our discussion.

**Somthing about Data Structure** There are many different abstract data types in Java's Algorithm, such as queue, stack, bag, sort, symbol table (or search). But actually they are all based on two basic types: one by one structure, and tree structure.
 - One by One
   - Array
   - Linked List
 - Tree
   - Forest of Tree
   - Binary Heap: key in each node is larger than or equal to the key in that node's two children (if any)
   - Binary Search Tree: key in any node is larger that the keys in all nodes in that node's left subtree and smaller than the keys in all nodes in that node's right subtree
    
    

