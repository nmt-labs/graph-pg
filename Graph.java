import java.io.FileNotFoundException;
import java.util.Scanner;
import representation.*;
import search.*;
import disjoint.*;

public class Graph {
  public static Scanner sc = new Scanner(System.in);
  public static int op;
  public static String fileOp, activity, rep;
  public static String smallFile = "run-files/graph-test-100.txt";
  public static String largeFile = "run-files/graph-test-50000.txt";
  public static String tinyFile = "run-files/graph-test-5.txt";
  public static void main(String[] args) throws FileNotFoundException {
    if(args.length == 1 && args[0].equals("-h")) {
      System.err.println("Usage: java Graph [0] [1] [2]");
      System.err.println("[0] graph file: -t to tiny file; -s to small file; -l to large file");      
      System.err.println("[1] action: -r to representation; -s to search; -d to disjoint path");
      System.err.println("[2] type of representation: -i to inverse matrix; -a to adjacence matrix; -r to reverse star");
      return;
    }
    else if(args.length != 3) {
      System.err.println("Arguments out of bounds. 3 arguments expected but " + args.length + " was given.");
      System.err.println("Type \" java Graph -h\" to show commands");
      return;
    } else {
      fileOp = args[0];
      activity = args[1];
      rep = args[2];

      switch(fileOp) {
        case "-s": fileOp = smallFile;
          break;
        case "-l": fileOp = largeFile;
          break;
        case "-t": fileOp = tinyFile;
          break;
        default: System.err.println("Command [0] not found.");
          System.err.println("Type \" java Graph -h\" to show commands");
          return;
      }

      switch(activity) {
        case "-r":
          IncidenceMatrix im;
          AdjacenceMatrix am;
          ReverseStar rs;
          if (rep.equals("-i")) {
            im = new IncidenceMatrix(fileOp);
            im.printInfo();
          }
          else if (rep.equals("-a")) {
            am = new AdjacenceMatrix(fileOp);
            am.printInfo();
          }
          else if (rep.equals("-r")) {
            rs = new ReverseStar(fileOp);
            rs.getVertex();
            rs.getVertexPredecessor();
          }
          else {
            System.err.println("Command [2] not found.");
            System.err.println("Type \" java Graph -h\" to show commands");
            return;
          }
          break;

        case "-s":
          DepthFirstSearch dfs = new DepthFirstSearch(fileOp);
          dfs.print();
          System.out.println("Choose a vertex: ");
          op = sc.nextInt();
          dfs.printArcs(op);
          break;
        case "-d": 
          DisjointPath dp = new DisjointPath(fileOp);
          dp.print();
          break;
        default: System.err.println("Command [1] not found");
          System.err.println("Type \" java Graph -h\" to show commands");
          return;
      }

      sc.close();
    }
  }
}
