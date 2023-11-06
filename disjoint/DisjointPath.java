package disjoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import representation.ReverseStar;

public class DisjointPath {
  public static Scanner sc;
  private ReverseStar graph;

  public DisjointPath(String file) throws FileNotFoundException {
    sc = new Scanner(new File(file));
    graph = new ReverseStar(file);
  }

  public void print() {
    System.out.println("teste");
  }
}
