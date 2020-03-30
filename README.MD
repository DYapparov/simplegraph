#Simple Graph
 
##Description
Simple Graph lib, which supports 2 types of graphs for custom vertices type:
  - directed;
  - undirected
  
##Launch
The only way to check this is to run tests:

<code>.\gradlew check</code>

##Available operations:
 - addVertex - adds vertex to the graph
 - addEdge - adds edge to the graph
 - getPath - returns a list of edges between 2 vertices (path is not guaranteed to be optimal)

##TBD
Add weighted edges support in your lib.
Add traverse function that will take a user defined function and apply it on every vertex of the graph.
Make you graphs thread safe.