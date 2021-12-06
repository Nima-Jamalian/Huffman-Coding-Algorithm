// Huffman Coding in Java
/*
Example:
Input = {B, C, A, A, D, D, D, C, C, A, C, A, C, A, C}

Steps:
1. Calculate the frequency of each character in the string.
2. Sort the characters in increasing order of the frequency. 
These are stored in a priority queue
3. Make each unique character as a leaf node
4. Create an empty node. Assign the minimum frequency to the 
  left child of z and assign the second minimum frequency to 
  the right child of node. Set the value of the node as the 
  sum of the above two minimum frequencies.
5. Remove these two minimum frequencies from queue and add 
  the sum into the list of frequencies 
6. Insert node into the tree
7. Repeat step 3 to 5 for all the characters
8. For each non-leaf node, assign 0 to left edge and 1 to the 
   right edge.

Result:
+------------------------+-------------+---------+-----------+
|        Character       |  Frequency  |  Code   |   Size    |
+------------------------+-------------+---------+-----------+
|            A           |      5      |   11    |  5*2 = 10 |
+------------------------+-------------+---------+-----------+
|            B           |      1      |   100   |  1*3 = 3  |
+------------------------+-------------+---------+-----------+
|            C           |      6      |    0    |  6*1 = 6  |
+------------------------+-------------+---------+-----------+
|            D           |      3      |   101   |  3*3 = 9  |
+------------------------+-------------+---------+-----------+
|     4 * 8  = 32 bits   |   15 bits   |         |  28 bits  |
+------------------------+-------------+---------+-----------+

Huffman Algorithms Pseudo Code:
Create a priority queue Q consisting of each unique character.
Sort then in ascending order of their frequencies.
For all the unique characters:
    create a newNode
    extract minimum value from Q and assign it to leftChild of newNode
    extract minimum value from Q and assign it to rightChild of newNode
    calculate the sum of these two minimum values and assign it to the value of newNode
    insert this newNode into the tree
return rootNode

Sources:
https://en.wikipedia.org/wiki/Huffman_coding
https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
https://www.programiz.com/dsa/huffman-coding
https://www.journaldev.com/23246/huffman-coding-algorithm
*/
import java.util.PriorityQueue;
import java.util.Comparator;

class Node {
  int f;//frequency
  char c;//character
  Node left;
  Node right;
}

// For comparing the nodes
class MyComparator implements Comparator<Node> {
  public int compare(Node x, Node y) {
    return x.f - y.f;
  }
}

// Implementing the huffman algorithm
public class Huffman {
  public static void main(String[] args) {

    int n = 4;
    char[] input = { 'A', 'B', 'C', 'D' };
    int[] inputCharFrequency = { 5, 1, 6, 3 };

    PriorityQueue<Node> queue = new PriorityQueue<Node>(n, new MyComparator());

    for (int i = 0; i < n; i++) {
      Node node = new Node();

      node.c = input[i];
      node.f = inputCharFrequency[i];

      node.left = null;
      node.right = null;

      queue.add(node);
    }

    Node root = null;

    while (queue.size() > 1) {

      Node x = queue.poll();
      Node y = queue.poll();
      Node temp = new Node();

      temp.f = x.f + y.f;
      temp.c = '-';
      temp.left = x;
      temp.right = y;
      root = temp;
      queue.add(temp);
    }

    System.out.println();
    PrintCharAndFreq(input, inputCharFrequency);
    System.out.println();
    System.out.println(" Char | Huffman code ");
    System.out.println("--------------------");
    PrintCode(root, "");
  }
  private static void PrintCode(Node root, String s) {
    if (root.left == null && root.right == null && Character.isLetter(root.c)) {
      System.out.println(root.c + "   |  " + s);

      return;
    }
    PrintCode(root.left, s + "0");
    PrintCode(root.right, s + "1");
  }

  private static void PrintCharAndFreq(char[] input,int[] frequency){
    System.out.print(" Char and Frequency = ");
    for(int i=0; i<input.length;i++){
        System.out.print(" " +  frequency[i] + input[i]);
    }
    System.out.println();
  }
}