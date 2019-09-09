package Chap13.bfs;

// bfs.java
// demonstrates breadth-first search
// to run this program: C>java BFSApp
////////////////////////////////////////////////////////////////
class Queue
   {
   private final int SIZE = 20;
   private int[] queArray;
   private int front;
   private int rear;
// -------------------------------------------------------------
   public Queue()            // constructor
      {
      queArray = new int[SIZE];
      front = 0;
      rear = -1;
      }
// -------------------------------------------------------------
   public void insert(int j) // put item at rear of queue
      {
      if(rear == SIZE-1)
         rear = -1;
      queArray[++rear] = j;
      }
// -------------------------------------------------------------
   public int remove()       // take item from front of queue
      {
      int temp = queArray[front++];
      if(front == SIZE)
         front = 0;
      return temp;
      }
// -------------------------------------------------------------
   public boolean isEmpty()  // true if queue is empty
      {
      return ( rear+1==front || (front+SIZE-1==rear) );
      }
// -------------------------------------------------------------
   }  // end class Queue
////////////////////////////////////////////////////////////////
class Vertex
   {
   public char label;        // label (e.g. 'A')
   public boolean wasVisited;
// -------------------------------------------------------------
   public Vertex(char lab)   // constructor
      {
      label = lab;
      wasVisited = false;
      }
// -------------------------------------------------------------
   }  // end class Vertex
////////////////////////////////////////////////////////////////
class Graph
   {
   private final int MAX_VERTS = 20;
   private Vertex vertexList[]; // list of vertices
   private int adjMat[][];      // adjacency matrix
   private int nVerts;          // current number of vertices
   private Queue theQueue;
// ------------------------------------------------------------
   public Graph()               // constructor
      {
      vertexList = new Vertex[MAX_VERTS];
                                          // adjacency matrix
      adjMat = new int[MAX_VERTS][MAX_VERTS];
      nVerts = 0;
      for(int j=0; j<MAX_VERTS; j++)      // set adjacency
         for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
            adjMat[j][k] = 0;
      theQueue = new Queue();
      }  // end constructor
// -------------------------------------------------------------
   public void addVertex(char lab)
      {
      vertexList[nVerts++] = new Vertex(lab);
      }
// -------------------------------------------------------------
   public void addEdge(int start, int end)
      {
      adjMat[start][end] = 1;
      adjMat[end][start] = 1;
      }
// -------------------------------------------------------------
   public void displayVertex(int v)
      {
      System.out.print(vertexList[v].label);
      }
// -------------------------------------------------------------

   public void bfs()                   // breadth-first search
      {                                // begin at vertex 0
      vertexList[0].wasVisited = true; // mark it
      displayVertex(0);                // display it
      theQueue.insert(0);              // insert at tail
      int v2;

      while( !theQueue.isEmpty() )     // until queue empty,
         {
         int v1 = theQueue.remove();   // remove vertex at head
         // until it has no unvisited neighbors
         while( (v2=getAdjUnvisitedVertex(v1)) != -1 )
            {                                  // get one,
            vertexList[v2].wasVisited = true;  // mark it
            displayVertex(v2);                 // display it
            theQueue.insert(v2);               // insert it
            }   // end while
         }  // end while(queue not empty)

      // queue is empty, so we're done
      for(int j=0; j<nVerts; j++)             // reset flags
         vertexList[j].wasVisited = false;
      }  // end bfs()
// -------------------------------------------------------------
   // returns an unvisited vertex adj to v
   public int getAdjUnvisitedVertex(int v)
      {
      for(int j=0; j<nVerts; j++)
         if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
            return j;
      return -1;
      }

      public void mst()  // minimum spanning tree ( breadth-first)
      {                                  // start at 0
         vertexList[0].wasVisited = true;   // mark it
         Queue queue = new Queue();
         queue.insert(0);                  // push it

         while( ! queue.isEmpty() )       // until stack empty
         {                               // get stack top
            int currentVertex =  queue.remove();
            // get next unvisited neighbor
            int v = getAdjUnvisitedVertex(currentVertex);
            if(v == -1)                     // if no more neighbors
               queue.remove();           //    pop it away
            else                            // got a neighbor
            {
               vertexList[v].wasVisited = true;

               queue.insert(v);                 // remove it
               // display edge
               displayVertex(currentVertex);     // from currentV
               displayVertex(v);                 // to v
               System.out.print(" ");
            }
         }  // end while(stack not empty)

         // queue is empty, so we're done
         for(int j=0; j<nVerts; j++)          // reset flags
            vertexList[j].wasVisited = false;
      }  // end mst()// end getAdjUnvisitedVertex()
// -------------------------------------------------------------
   }  // end class Graph
////////////////////////////////////////////////////////////////
class BFSApp
   {
   public static void main(String[] args)
      {
      Graph theGraph = new Graph();
      theGraph.addVertex('A');    // 0  (start for bfs)
      theGraph.addVertex('B');    // 1
      theGraph.addVertex('C');    // 2
      theGraph.addVertex('D');    // 3
      theGraph.addVertex('E');    // 4
      theGraph.addVertex('F');    // 5
      theGraph.addVertex('G');    // 6
      theGraph.addVertex('H');    // 7
      theGraph.addVertex('I');    // 8

      theGraph.addEdge(0, 1);     // AB
      theGraph.addEdge(0, 4);     // AE
      theGraph.addEdge(1, 3);     // BC
      theGraph.addEdge(1, 4);     // BE
      theGraph.addEdge(2, 4);     // CE
      theGraph.addEdge(2, 5);     // CG
      theGraph.addEdge(3, 4);     // DE
      theGraph.addEdge(3, 6);     // DG
      theGraph.addEdge(4, 7);     // EH
      theGraph.addEdge(4, 8);     // EI
      theGraph.addEdge(5, 8);     // FI
      theGraph.addEdge(6, 7);     // GH



      System.out.print("Visits: ");
      theGraph.bfs();             // breadth-first search
      System.out.println();
      theGraph.mst();
      }  // end main()
   }  // end class BFSApp
////////////////////////////////////////////////////////////////

