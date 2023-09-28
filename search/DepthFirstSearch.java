package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import representation.AdjacenceMatrix;

// matriz [vertices v][4] -> guarda valor do vertice[v][0],  tt[v][1], td[v][2] e predecessor[v][3]
// t = 0 -> inicia tempo globlal
// iniciar tempos e predecessores
// enquanto existir algum matriz[v][2] = 0 -> realizar busca em v
// funcao de busca
// t++
// matriz[v][2] = t (td = t)
// para todo vertice w vizinho de [v]
  // se matriz[w][2] = 0 (v nao descoberto)
    // aresta {v, w} -> arvore
    // matriz[w][3] = v
    // busca em w
  // senao se matriz[w][1] = 0 && w != matriz[v][3] (w nao acabou e nao e pai de v -> w e ancestral mas nao pai de v)
    // aresta {v, w} -> retorno
// t++
// matriz[v][1] = t (tt = t)

public class DepthFirstSearch {
  public static Scanner sc;
  private AdjacenceMatrix graph;
  private int[][] matrix;
  private int t;

  public DepthFirstSearch(String file) throws FileNotFoundException {
    sc = new Scanner(new File(file));
    graph = new AdjacenceMatrix(file);
    // matrix [vertices v][4] -> vertice value[v][0],  tt[v][1], td[v][2] e predecessor[v][3]
    matrix = new int[graph.getVertices()][4];
    // inicialize global time and predecessors
    t = 0;
    for (int i = 0; i < graph.getVertices(); i ++) {
      matrix[i][0] = i+1;
      matrix[i][1] = 0;      
      matrix[i][2] = 0;
      matrix[i][3] = 0;
    }
    // enquanto existir algum matriz[v][2] = 0 -> realizar busca em v
    while(isToDiscover()) {
      int v = getSearchVertice();
      if (v != -1) dfs(v);
    }
  }

  private void dfs(int v) {
    t++;
    // td = t
    matrix[v][2] = t;

    // para todo vertice w vizinho de [v]
      // se matriz[w][2] = 0 (v nao descoberto)
        // aresta {v, w} -> arvore
        // matriz[w][3] = v
        // busca em w
      // senao se matriz[w][1] = 0 && w != matriz[v][3] (w nao acabou e nao e pai de v -> w e ancestral mas nao pai de v)
        // aresta {v, w} -> retorno
    
    t++;
    // tt = t
    matrix[v][1] = t;
  }

  // utilities -------------------------------------------------------------------------

  /**
   * Method to verify if there's vertices yet to discover
   * @return boolean
   */
  private boolean isToDiscover() {
    for (int i = 0; i < graph.getVertices(); i ++) {
      if (matrix[i][2] == 0) return true;
    }
    return false;
  }

  private int getSearchVertice() {
    for (int i = 0; i < graph.getVertices(); i ++) {
      if (matrix[i][2] == 0) return i;
    }
    return -1;
  }
}
