package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import representation.ReverseStar;

public class DepthFirstSearch {
  public static Scanner sc;
  private ReverseStar graph;
  private int[][] matrix;
  private int[] pointer, destination, arcTypes;
  private int t;

  /**
   * Depht First Search class using Reverse Star
   * @param file
   * @throws FileNotFoundException
   */
  public DepthFirstSearch(String file) throws FileNotFoundException {
    sc = new Scanner(new File(file));
    graph = new ReverseStar(file);
    pointer = graph.getPointer();
    destination = graph.getDestination();
    arcTypes = new int[destination.length];
    // matrix [vertices v][4] -> vertice value[v][0],  td[v][1], tt[v][2] e predecessor[v][3]
    matrix = new int[graph.getVertexAmt() + 1][4];
    
    // inicialize global time and predecessors
    t = 0;
    for (int i = 0; i <= graph.getVertexAmt(); i++) {
      matrix[i][0] = i;
      matrix[i][1] = 0;      
      matrix[i][2] = 0;
      matrix[i][3] = 0;
    }
    
    // until there's vertex to discover
    while(isToDiscover()) {
      int v = getSearchVertex();
      if (v != -1) search(v);
    }
  }

  private void search(int v) {
    t++;
    matrix[v][1] = t; // td = t

    // get number of sucessors
    int sucessors = pointer[v + 1] - pointer[v];

    // for each v neighbor
    for (int i = 0; i < sucessors; i++) {
      int w = destination[pointer[v] + i];
      // if w not discovered
      if (matrix[w][1] == 0 ) {
        // {v, w} -> tree
        arcTypes[pointer[v] + i] = 1;
        matrix[w][3] = v; // w parent = v
        search(w);
      }
      // {v, w} -> back
      else if (matrix[w][2] == 0 && w != matrix[v][3]) arcTypes[pointer[v] + i] = 2;
      // {v, w} -> forward
      else if (matrix[v][1] < matrix[w][1]) arcTypes[pointer[v] + i] = 3;
      // {v, w} -> cross
      else if (matrix[v][1] > matrix[w][1]) arcTypes[pointer[v] + i] = 4;
    }
    
    t++;
    matrix[v][2] = t; // tt = t
  }

  /**
   * Method to print all tree arcs from a graph
   */
  public void print() {
    System.out.println();

    for (int index = 1; index < pointer.length - 1; index++) {
      int v = index;
      int w;
      int sucessors = pointer[v + 1] - pointer[v];
      for (int j = 0; j < sucessors; j++) {
        if (arcTypes[pointer[v] + j] == 1) {
          w = destination[pointer[v] + j];
          System.out.println("Tree -> {" + v + ", " + w + "}");
        }
      }
    }
  }

  public void printArcs(int v) {

    if (v <= graph.getVertexAmt() && v > 0) {
      int w;
      int sucessors = pointer[v + 1] - pointer[v];
      for (int j = 0; j < sucessors; j++) {
        if (arcTypes[pointer[v] + j] == 1) {
          w = destination[pointer[v] + j];
          System.out.println("Tree -> {" + v + ", " + w + "}");
        }
        if (arcTypes[pointer[v] + j] == 2) {
          w = destination[pointer[v] + j];
          System.out.println("Back -> {" + v + ", " + w + "}");
        }
        if (arcTypes[pointer[v] + j] == 3) {
          w = destination[pointer[v] + j];
          System.out.println("Forward -> {" + v + ", " + w + "}");
        }
        if (arcTypes[pointer[v] + j] == 4) {
          w = destination[pointer[v] + j];
          System.out.println("Cross -> {" + v + ", " + w + "}");
        }
      }
    }
    else System.err.println("Invalid vertex.");
  }

  // utilities -------------------------------------------------------------------------

  /**
   * Method to verify if there's vertices yet to discover
   * @return
   */
  private boolean isToDiscover() {
    for (int i = 1; i <= graph.getVertexAmt(); i++) {
      if (matrix[i][2] == 0) return true;
    }
    return false;
  }
  /**
   * Method to find next vertice to search
   * @return vertex value index or -1
   */
  private int getSearchVertex() {
    for (int i = 1; i <= graph.getVertexAmt(); i++) {
      if (matrix[i][2] == 0) return i;
    }
    return -1;
  }
}
