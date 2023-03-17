/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)============================================
import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------
public class Graph {
  int [][] a; int n;
  char v[];
  int deg[];
  Graph() {
    v = "ABCDEFGHIJKLMNOP".toCharArray();
    deg = new int[20];
    a = new int[20][20];
    n = 0;
   }

  void loadData(int k) {  //do not edit this function
    RandomAccessFile f;int i,j,x;
    String s;StringTokenizer t;
    a = new int[20][20];
    try {
     f = new RandomAccessFile("data.txt","r");
     for(i=0;i<k;i++) f.readLine();
     s = f.readLine();s = s.trim();
     n = Integer.parseInt(s);
     for(i=0;i<n;i++) {
       s = f.readLine();s = s.trim();
       t = new StringTokenizer(s);
       for(j=0;j<n;j++) { 
         x = Integer.parseInt(t.nextToken().trim());
         a[i][j] = x;
        }
       }
     f.close();
     }
    catch(Exception e) {}

   }

  void dispAdj() {
    int i,j;
    for(i=0;i<n;i++) {
      System.out.println();
      for(j=0;j<n;j++)
        System.out.printf("%4d",a[i][j]);
     }
   }

  void fvisit(int i, RandomAccessFile f) throws Exception {
    f.writeBytes("  "+v[i]);
   }

 void fdispAdj(RandomAccessFile f) throws Exception { 
    int i,j;
    f.writeBytes("n = "+n+"\r\n");
    for(i=0;i<n;i++) {
      f.writeBytes("\r\n");
      for(j=0;j<n;j++)  f.writeBytes("  " + a[i][j]);
     }
    f.writeBytes("\r\n");
   }

  void breadth(boolean [] en, int i, RandomAccessFile f) throws Exception {
    Queue q = new Queue();
    int r,j;
    q.enqueue(i); en[i]=true;
    while(!q.isEmpty()) {
      r = q.dequeue();
      fvisit(r,f);
      for(j=0;j<n;j++) {
        if(!en[j] && a[r][j]>0) {
         q.enqueue(j);en[j]=true;
        }
       }
     }
   }

  void breadth(int  k, RandomAccessFile f) throws Exception {
    boolean [] en = new boolean[20];
    int i;
    for(i=0;i<n;i++) en[i]=false;
    breadth(en,k,f);
    for(i=0;i<n;i++) 
      if(!en[i]) breadth(en,i,f);
   }

    void depth(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth(visited, i, f);
            }
        }
    }
    void depth(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth(visited, i, f);
            }
        }
    }

    void depth2(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit(k, f);
        visited[k] = true;
        for (int i = 1; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth(visited, i, f);
            }
        }
    }
    void depth2(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth2(visited, k+1, f);
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
  void f1() throws Exception {
    loadData(1);
    String fname = "f1.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    depth(0,f);
    f.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        depth2(0, f);

    //-------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }

  void dijkstra(int start, int end, RandomAccessFile f) throws Exception {
        // Initialize distance array with max value and source distance to 0
        int[] distance = new int[n];
        boolean[] settled = new boolean[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = 99;
            settled[i] = false;
            parent[i] = -1;
        }
        distance[start] = 0;

        // loop through all vertices to find shortest paths
        for (int count = 0; count < n - 1; count++) {
            // Find the vertex with the minimum distance
            int minDistance = 99;
            int minVertex = -1;
            for (int i = 0; i < n; i++) {
                if (!settled[i] && distance[i] < minDistance) {
                    minDistance = distance[i];
                    minVertex = i;
                }
            }

            // Mark the selected vertex as settled
            settled[minVertex] = true;

            // Update the distance of neighboring vertices
            for (int i = 0; i < n; i++) {
                int edgeWeight = a[minVertex][i];
                if (edgeWeight > 0 && !settled[i] && distance[minVertex] != 99
                        && distance[minVertex] + edgeWeight < distance[i]) {
                    distance[i] = distance[minVertex] + edgeWeight;
                    parent[i] = minVertex;
                }
            }
           
        }
        if (parent[end] == -1) {
                System.out.println("No path from source to destination");
                return;
            }
            Stack stack = new Stack();
            int node = end;
            while (node != -1) {
                stack.push(node);
                node = parent[node];
            }

            while (!stack.isEmpty()) {
                fvisit(stack.pop(), f);
        }
    }
//=================================================================

  void f2() throws Exception {
    loadData(13);
    String fname = "f2.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    f.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
      // You can use the statement fvisit(i,f); i = 0, 1, 2,...,n-1 to display the vertex i to file f2.txt 
      //  and statement f.writeBytes(" " + k); to write  variable k to the file f2.txt  
      dijkstra(2, 5, f);
      f.writeBytes("\r\n");
      dijkstra(0, 6, f);
      f.writeBytes("\r\n");
      dijkstra(3, 6, f);


    //-------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }

}
