import java.io.FileNotFoundException;
import java.util.Scanner;
import representation.*;
import search.*;

public class Graph {
  public static Scanner sc = new Scanner(System.in);
  public static int op;
  public static String fileOp;
  public static String smallFile = "run-files/graph-test-100.txt";
  public static String largeFile = "run-files/graph-test-50000.txt";
  public static String tinyFile = "run-files/graph-test-5.txt";
  public static void main(String[] args) throws FileNotFoundException {

    System.out.println("Choose test file");
    System.out.println("1 - small file (100)");
    System.out.println("2 - large file (50000)");
    System.out.println("3 - tiny file (5)");
    op = sc.nextInt();

    switch(op) {
      case 1: fileOp = smallFile;
      case 2: fileOp = largeFile;
      case 3: fileOp = tinyFile;
    }

    System.out.println("Choose activity");
    System.out.println("1 - Graph representation");
    System.out.println("2 - Graph search");
    op = sc.nextInt();

    switch(op) {
      case 1:
        System.out.println("Choose type");
        System.out.println("1 - Incidence matrix");
        System.out.println("2 - Adjacence matrix");

        op = sc.nextInt();
        IncidenceMatrix im;
        AdjacenceMatrix am;
        if (op == 1) {
          im = new IncidenceMatrix(fileOp);
          im.printInfo();
        }
        else {
          am = new AdjacenceMatrix(fileOp);
          am.printInfo();
        }

      case 2:
        DepthFirstSearch dfs = new DepthFirstSearch(fileOp);
    }

    sc.close();
  }
}
