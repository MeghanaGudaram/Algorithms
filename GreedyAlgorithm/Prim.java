import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import java.util.Queue;
import java.io.BufferedReader;
import java.io.FileReader;
public class Prim
{

	private static int V=0, E=0;
	private static int maxv      = 100, maxdegree = 50;
	private static int edges[][] = new int[maxv + 1][maxdegree];
	private static float[][] finalmatrix = new float[30][30];
	private static int degree[]  = new int[maxv + 1];
	private static boolean processed[]  = new boolean[maxv];
	private static boolean discovered[] = new boolean[maxv];
	private static int parent[]     = new int[maxv];
	private static int conarray[] = new int[V];
	int minKey(float key[], Boolean mstSet[], int c)
	{
		// find next index with minimum weight
		float min = Integer.MAX_VALUE;
		int min_index=-1;

		for (int v = 0; v < c; v++)
		{
			if (mstSet[v] == false && key[v] < min)
			{
				min = key[v];
				min_index = v;
			}
		}
		return min_index;
	}

    // A function to print the constructed MST
	void printMST(int parent[], int n, float graph[][], int c, int array[])
	{
		System.out.println("Edge   Weight");
		for (int i = 1; i < c; i++)
			System.out.println((parent[i]+1)+" - "+ (i+1)+"    "+
                               graph[i][parent[i]]);
		float[][] newgraph = new float[c][c];
		for(int i=0; i<c;i++)
		{
			for(int j=0; j<c;j++)
			{
				newgraph[i][j] = 0;
			}
		}
		// newgraph is the adjacency matrix with minimum spanning tree
    for (int i = 1; i < c; i++)
    {
    	newgraph[parent[i]][i] = graph[i][parent[i]];
    	newgraph[i][parent[i]] = graph[i][parent[i]];
    }
    for(int i=0; i<c; i++)
		{
			for(int j=0; j<c; j++)
			{
				finalmatrix[array[i]-1][array[j]-1]=newgraph[i][j];
				System.out.print(newgraph[i][j] + "  ");
			}
			System.out.println();
		}
    }

    //  Function to find MST
    void primMST(float graph[][], int c, int array[])
    {
        // Array to store constructed MST with parent
        int parent[] = new int[c];

        // Key values minimum weight values
        float key[] = new float [c];

        // array to see if vertex is set with edge
        Boolean mstSet[] = new Boolean[c];

        // Initialize all keys(weights) as INFINITE
        for (int i = 0; i < c; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;     // Make key 0 so that this vertex is

        parent[0] = -1; // First node is always root of MST

        // The MST will have c vertices(of each component)
        for (int count = 0; count < c-1; count++)
        {
            // Pick thd minimum key vertex not picked yet
            int u = minKey(key, mstSet, c);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < c; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                if (graph[u][v]!=0 && mstSet[v] == false &&
                    graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, c, graph, c, array);
    }

    void insert_edge(int x, int y, boolean directed)
    {
        if (degree[x] > maxdegree)
            System.out.printf(
                    "Warning: insertion (%d, %d) exceeds max degree\n", x, y);
        edges[x][degree[x]] = y;
        degree[x]++;
        if (!directed)
            insert_edge(y, x, true);
    }

    void print_CCGraph(int c)
    {
        for (int i = 1; i <= c; i++)
        {
            System.out.printf("%d: ", i);
            for (int j = degree[i] - 1; j >= 0; j--)
                System.out.printf(" %d", edges[i][j]);
            System.out.printf("\n");
        }
    }

public static void main (String[] args)
{
		Prim p=new Prim();
		BufferedReader br;
		for (int i = 1; i <= maxv; i++)
			degree[i] = 0;
		int edj=0;
		String sCurrentLine;
		try {
			br = new BufferedReader(new FileReader("graph2.txt"));
			V = Integer.parseInt(br.readLine());
			E = Integer.parseInt(br.readLine());
			System.out.println("no.of Verticdfes:" + V);
			System.out.println("no.of Edges:" + E);
			float input[][] = new float[E][3];
			while ((sCurrentLine = br.readLine()) != null) {
				input[edj][0]=Float.parseFloat(sCurrentLine.split(",")[0]);
				input[edj][1]=Float.parseFloat(sCurrentLine.split(",")[1]);
				input[edj][2]=Float.parseFloat(sCurrentLine.split(",")[2]);
				p.insert_edge((int)input[edj][0], (int)input[edj][1], false);
				edj++;
			}

		if(V > 0 && E > 0)
		{
			p.initialize_search();
			for (int i = 1; i <= V; i++)
			{
				if (!discovered[i])
				{
					// run Prim's for each component
					ArrayList<Integer> comp=p.connected_components(i);
					int c = comp.size();
					Collections.sort(comp);
					float graph[][] = new float[c][c];
					int array[] = new int[c];
					for(int ii=0; ii<c; ii++)
					{
						for(int jj=0; jj<c; jj++)
						{
							graph[ii][jj] = 0;
						}
						array[ii] = comp.get(ii);
					}
					// set adjacency matrix with input array
					for(int ii=0; ii<E; ii++)
					{
						for(int jj=0; jj<c; jj++)
						{
							if(array[jj]==input[ii][0])
							{
								for(int kk=0; kk<c;kk++)
								{
									if(array[kk]==input[ii][1])
									{
										graph[jj][kk] = input[ii][2];
										graph[kk][jj] = input[ii][2];

									}
								}
							}
						}
					}
					System.out.println("Adjacency Matrix:");
					for(int ii=0; ii<c; ii++)
					{
						for(int jj=0; jj<c; jj++)
						{
							System.out.print(graph[ii][jj] + "  ");
						}
						System.out.println();
					}
					System.out.println("Applying Prim's min spanning tree:");
					p.primMST(graph, c, array);
				}
			}
		}
		System.out.println("Minimum spanning forest is: ");
		//print final minimum spanning forest
		for(int ii=0;ii<V;ii++)
		{
			for(int jj=0; jj < V; jj++)
			{
				System.out.print(finalmatrix[ii][jj] + "  ");
			}
			System.out.println();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
// Breadth First Search to find connected components
	ArrayList<Integer> bfs(int start)
    {
        Queue<Integer> q = new LinkedList<Integer>();
        ArrayList<Integer> comp = new ArrayList<Integer>();
        int i, v;
        q.offer(start);
        discovered[start] = true;
        while (!q.isEmpty())
        {
            v = q.remove();
            System.out.printf(" %d", v);
            comp.add(v);
            processed[v] = true;
            for (i = degree[v]-1; i >= 0; i--)
            {
                if (!discovered[edges[v][i]])
                {
                    q.offer(edges[v][i]);
                    discovered[edges[v][i]] = true;
                    parent[edges[v][i]] = v;
                }
            }
        }
        return comp;
    }
//initialize search of bfs
    void initialize_search()
    {
        for (int i = 1; i <= V; i++)
        {
            processed[i] = discovered[i] = false;
            parent[i] = -1;
        }
    }
//reduntand function to print connected components
    ArrayList<Integer> connected_components(int i)
    {
        System.out.printf("Component %d:", i);
        ArrayList<Integer> comp=bfs(i);
        System.out.printf("\n");
        return comp;

    }

}
