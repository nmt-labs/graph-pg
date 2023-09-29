package representation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReverseStar {
    public static Scanner sc;
    private int vertexAmt, arcsAmt;
    private int[] origin, destination, pointer, rev_pointer;

    public int getVertexAmt() {
      return vertexAmt;
    }
    public int[] getPointer() {
      return pointer;
    }
    public int getArcsAmt() {
      return arcsAmt;
    }
    public int[] getDestination() {
      return destination;
    }

    public ReverseStar(String file) throws FileNotFoundException {
        sc = new Scanner(new File(file));

        vertexAmt = sc.nextInt();
        arcsAmt = sc.nextInt();
        origin = new int[arcsAmt + 1];
        destination = new int[arcsAmt + 1];

        int i = 1; // always ignore position 1
        while (sc.hasNextInt()) {
            origin[i] = sc.nextInt();
            destination[i++] = sc.nextInt();
        }
        sc.close();

        forwardStar();
        reverseStar();
    }

    private void forwardStar() {

        // ---Create arc_dest & pointer arrays---
        pointer = new int[vertexAmt + 2];

        // ---Fill pointer array---
        int pos = 1;
        while (pos <= pointer.length) {
            if (pos == 1) {
                pointer[pos] = 1;
            } else if (pos < pointer.length) {
                pointer[pos] = getFirstInstance(pos, origin);
            } else if (pos == pointer.length) {
                pointer[pos - 1] = (arcsAmt + 1);
            }
            pos++;
        }
    }

    private void reverseStar() {

        // ---Sort destination array---
        sort(origin, destination);

        //int arc_src[] = origin;
        rev_pointer = new int[vertexAmt + 2];

        // ---Fill pointer array---
        int pos = 1;
        while (pos <= rev_pointer.length) {
            if (pos == 1) {
                rev_pointer[pos] = 1;
            } else if (pos < rev_pointer.length) {
                rev_pointer[pos] = getFirstInstance(pos, destination);
            } else if (pos == rev_pointer.length) {
                rev_pointer[pos - 1] = (arcsAmt + 1);
            }
            pos++;
        }
    }

    /**
     * This function returns the position in which the @@target parameter is first found
     * @param target
     * @param origin
     * @return
     */
    private static int getFirstInstance(int target, int origin[]) {
        int it = 1; // Always skip position 0.
        int value = 0;

        while (it < origin.length) {
            if (origin[it] == target) {
                value = it;
                it = origin.length; // break operation
            }
            it++;
        }
        return value;
    }

    /**
     * This method prints the highest degree and to which vertex it belongs
     * @param pointer
     * @param origin
     * @param destination
     */
    public void getVertex() {
        int highestDegree = 0;
        int vertex = 0;

        for (int i = 1; i < pointer.length; i++) {
            if (i + 1 < pointer.length) {
                int curr = pointer[i];
                int next = pointer[i + 1];
                int diff = (next - curr);

                if (diff > highestDegree) {
                    vertex = i;
                    highestDegree = diff;
                }
            }
        }

        // ---Based on the vertex, get it's children---
        int sucessors[] = new int[highestDegree];
        int pos = pointer[vertex];

        for (int i = 0; i < highestDegree; i++) {
            sucessors[i] = destination[pos++];
        }

        System.out.println();
        System.out.println("Output vertex: " + (vertex));
        System.out.println("Degree: " + highestDegree);
        System.out.println("Sucessors: ");
        for (int i = 0; i < sucessors.length; i++)
          System.out.print(sucessors[i] + " ");
    }

    /**
     * Method that gets the entry degree & the predecessors
     * @param rev_pointer
     * @param destination
     * @param origin
     */
    public void getVertexPredecessor() {
        int highestDegree = 0;
        int vertex = 0;

        // Look for highest entry degree among vertexes
        for (int i = 1; i < rev_pointer.length; i++) {
            if (i + 1 < rev_pointer.length) {
                int curr = rev_pointer[i];
                int next = rev_pointer[i + 1];
                int diff = next - curr;

                if (diff > highestDegree) {
                    highestDegree = diff;
                    vertex = i;
                }
            }
        }

        // Get predecessors list
        int predecessors[] = new int[highestDegree];
        int pos = rev_pointer[vertex];

        for (int i = 0; i < predecessors.length; i++) {
            predecessors[i] = origin[pos++];
        }

        System.out.println();
        System.out.println("Input vertex: " + (vertex + 1));
        System.out.println("Degree: " + highestDegree);
        System.out.println("Predecessors: ");
        for (int i = 0; i < predecessors.length; i++)
          System.out.print(predecessors[i] + " ");

    }

    /**
     * Quicksort algorithim
     * @param origin
     * @param destination
     */
    private static void sort(int[] origin, int[] destination) {
        int n = destination.length;

        // One by one move boundary of unsorted subarray
        for (int i = 1; i < n; i++) {
            // Find minimum element in unsorted array
            int min_idx = i;

            for (int j = i + 1; j < n; j++) {
                if (destination[j] < destination[min_idx]) {
                    min_idx = j;
                }
            }

            // Swap position on destination
            int temp = destination[min_idx];
            destination[min_idx] = destination[i];
            destination[i] = temp;

            // Swap position on origin
            temp = origin[min_idx];
            origin[min_idx] = origin[i];
            origin[i] = temp;
        }
    }
}
