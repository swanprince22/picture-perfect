# picture-perfect
Picture Perfect is an image-sliding puzzle that uses a linked list data structure to represent the puzzle pieces. To win, players must slide the pieces until the original image is fully reconstructed.

The puzzle was designed with the following considerations:

1. Creating a solvable shuffled puzzle
- The puzzle is shuffled using the Fisher-Yates algorithm.
- The number of inversions in the shuffled pieces is checked to ensure it is odd, guaranteeing a solvable puzzle.
Reference: https://developerslogblog.wordpress.com/2020/04/01/how-to-shuffle-an-slide-puzzle/

2. Creating a solver
- The solver uses a Breadth-First Search (BFS) algorithm to find a sequence of moves that solves the puzzle.
Reference: https://www.geeksforgeeks.org/dsa/breadth-first-search-or-bfs-for-a-graph/

