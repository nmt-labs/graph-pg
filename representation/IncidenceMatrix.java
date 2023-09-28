package representation;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IncidenceMatrix {
  public static Scanner sc;
  int vertices, edges, outputDegree = 0, inputDegree = 0, outputVertex = 0, inputVertex = 0, aux;
  int[] outputSucessors, inputPredecessors;
  int[][] matrix;

  public IncidenceMatrix(String file) throws FileNotFoundException {
    sc = new Scanner(new File(file)); 

    vertices = sc.nextInt();
    edges = sc.nextInt();

    // create matrix
    matrix = new int[vertices][edges];

    // read until edge
    for (int i = 0; i < edges; i++) {
      // complete matrix
      // sc.nextInt() - 1 -> to complete the correct line from matrix
      matrix[sc.nextInt() - 1][i] = -1;
      matrix[sc.nextInt() - 1][i] = +1;
    }

    // close sc
    sc.close();
  }

  public void printInfo() {
    // output degree info
    aux = 0;
    // find biggest output degree value
    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < edges; j++) {
        if (matrix[i][j] < 0)
          aux++;
      }
      if (aux > outputDegree) {
        outputDegree = aux;
        // outputVertex will be the first occurrence
        outputVertex = i;
      }
      aux = 0;
    }

    outputSucessors = new int[outputDegree];
    aux = 0;
    // find biggest output degree vertex sucessors
    for (int j = 0; j < edges; j++) {
      if (matrix[outputVertex][j] < 0) {
        for (int i = 0; i < vertices; i++) {
          if (matrix[i][j] > 0) {
            outputSucessors[aux++] = i + 1;
            i = vertices;
          }
        }
      }
    }

    // input degree info
    aux = 0;
    // find biggest input degree value
    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < edges; j++) {
        if (matrix[i][j] > 0)
          aux++;
      }
      if (aux > inputDegree) {
        inputDegree = aux;
        // inputtVertex will be the first occurrence
        inputVertex = i;
      }
      aux = 0;
    }

    inputPredecessors = new int[inputDegree];
    aux = 0;
    // find biggest input degree vertex sucessors
    for (int j = 0; j < edges; j++) {
      if (matrix[inputVertex][j] > 0) {
        for (int i = 0; i < vertices; i++) {
          if (matrix[i][j] < 0) {
            inputPredecessors[aux++] = i + 1;
            i = vertices;
          }
        }
      }
    }

    // print results
    System.out.println();
    System.out.println("Output vertex: " + (outputVertex + 1));
    System.out.println("Degree: " + outputDegree);
    System.out.println("Sucessors: ");
    for (int i = 0; i < outputSucessors.length; i++)
      System.out.print(outputSucessors[i] + " ");

    System.out.println();
    System.out.println("Input vertex: " + (inputVertex + 1));
    System.out.println("Degree: " + inputDegree);
    System.out.println("Predecessors: ");
    for (int i = 0; i < inputPredecessors.length; i++)
      System.out.print(inputPredecessors[i] + " ");
  }
}