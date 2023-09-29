package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import representation.ReverseStar;

public class DepthFirstSearch {
  public static Scanner sc;
  private ReverseStar graph;
  private int[][] matrix;
  private int t;

  public DepthFirstSearch(String file) throws FileNotFoundException {
    sc = new Scanner(new File(file));
    graph = new ReverseStar(file);
    // matrix [vertices v][4] -> vertice value[v][0],  tt[v][1], td[v][2] e predecessor[v][3]
    matrix = new int[graph.getVertexAmt() + 1][4];
    // inicialize global time and predecessors
    t = 0;
    for (int i = 1; i <= graph.getVertexAmt(); i++) {
      matrix[i][0] = i;
      matrix[i][1] = 0;      
      matrix[i][2] = 0;
      matrix[i][3] = 0;
    }
    // enquanto existir algum matriz[v][2] = 0 -> realizar busca em v
    while(isToDiscover()) {
      int v = getSearchVertex();
      if (v != -1) search(v);
    }
  }

  private void search(int v) {
    t++;
    // td = t
    matrix[v][2] = t;
    // get number of sucessors
    int[] pointer = graph.getPointer();
    int sucessors = pointer[v + 1] - pointer[v];
    System.out.println("sucessors: " + sucessors);

    // for each v neighbor
    for (int i = 0; i < sucessors; i++) {
      int w = graph.getDestination()[pointer[v] + i];
      // if w not discovered
      if (matrix[w][2] == 0 ) {
        // {v, w} -> tree
        System.out.println("Tree -> {" + v + ", " + w + "}");
        // w parent = v
        matrix[w][3] = v;
        search(w);
      }
      // if w isn't over and isn't v ancestral
      else if (matrix[w][1] == 0 && w != matrix[v][3]) {
        // {v, w} -> back
        System.out.println("Back -> {" + v + ", " + w + "}");
      }
    }

    // print matrix
    for (int l = 0; l < matrix.length; l++)  {  
      for (int c = 0; c < matrix[l].length; c++)     { 
          System.out.print(matrix[l][c] + " "); //imprime caracter a caracter
      }  
      System.out.println(" "); //muda de linha
    } 
    System.out.println();
    
    t++;
    // tt = t
    matrix[v][1] = t;
  }

  public void print() {
    System.out.println("End");
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
