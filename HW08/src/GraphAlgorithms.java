import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Han Choi
 * @version 11.0.2
 * @userid hchoi395
 * @GTID 903598626
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (graph == null || !graph.getVertices().contains(start) || start == null) {
            throw new IllegalArgumentException("Input is null or start doesn't exist in graph");
        }
        Queue<Vertex<T>> queue = new LinkedList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        List<Vertex<T>> list = new LinkedList<>();
        visitedSet.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            Vertex<T> temp = queue.poll();
            list.add(temp);
            for (VertexDistance<T> w : graph.getAdjList().get(temp)) {
                if (!visitedSet.contains(w.getVertex())) {
                    visitedSet.add(w.getVertex());
                    queue.add(w.getVertex());
                }
            }
        }
        return list;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (graph == null || !graph.getVertices().contains(start) || start == null) {
            throw new IllegalArgumentException("Input is null or start doesn't exist in graph");
        }
        List<Vertex<T>> list = new LinkedList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        dfsHelper(start, graph, visitedSet, list);
        return list;
    }

    /**
     * Private helper method for dfs.
     * @param vertex the vertex to begin the dfs on
     * @param graph the graph to search through
     * @param visitedSet set containing the visited vertexes
     * @param list list of vertices in visited order
     * @param <T> the generic typing of the data
     */
    private static <T> void dfsHelper(Vertex<T> vertex, Graph<T> graph, Set<Vertex<T>> visitedSet, List<Vertex<T>> list) {
        visitedSet.add(vertex);
        list.add(vertex);
        for (VertexDistance<T> w : graph.getAdjList().get(vertex)) {
            if (!visitedSet.contains(w.getVertex())) {
                dfsHelper(w.getVertex(), graph, visitedSet, list);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (graph == null || !graph.getVertices().contains(start) || start == null) {
            throw new IllegalArgumentException("Input is null or start doesn't exist in graph");
        }
        Map<Vertex<T>, Integer> map = new HashMap<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        for (Vertex<T> v : graph.getAdjList().keySet()) {
            if (v.equals(start)) {
                map.put(v, 0);
            } else {
                map.put(v, Integer.MAX_VALUE);
            }
        }
        pq.add(new VertexDistance<>(start, 0));
        while (!pq.isEmpty()) {
            VertexDistance<T> temp = pq.remove();
            for (VertexDistance<T> w : graph.getAdjList().get(temp.getVertex())) {
                int distance = w.getDistance() + temp.getDistance();
                if (map.get(w.getVertex()) > distance) {
                    map.put(w.getVertex(), distance);
                    pq.add(new VertexDistance<>(w.getVertex(), distance));
                }
            }
        }
        return map;
    }

    /**
     * Runs Kruskal's algorithm on the given graph and returns the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you. A Disjoint Set will keep track of which vertices are
     * connected given the edges in your current MST, allowing you to easily
     * figure out whether adding an edge will create a cycle. Refer
     * to the DisjointSet and DisjointSetNode classes that
     * have been provided to you for more information.
     *
     * You should NOT allow self-loops or parallel edges into the MST.
     *
     * By using the Disjoint Set provided, you can avoid adding self-loops and
     * parallel edges into the MST.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Set, and any class that implements the aforementioned
     * interfaces.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("input is null");
        }
        Set<Edge<T>> edgeSet = new HashSet<>();
        DisjointSet<Vertex<T>> disjointSet = new DisjointSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>(graph.getEdges());
        while (!pq.isEmpty() && edgeSet.size() < graph.getEdges().size() - 1) {
            Edge<T> temp = pq.remove();
            Vertex<T> u = temp.getU();
            Vertex<T> v = temp.getV();
            if (disjointSet.find(u) != disjointSet.find(v)) {
                disjointSet.union(disjointSet.find(u), disjointSet.find(v));
                edgeSet.add(temp);
                Edge<T> e = new Edge<>(v, u, temp.getWeight());
                edgeSet.add(e);
            }
        }
        if (edgeSet.size() < 2 * (graph.getVertices().size() - 1));
        return edgeSet;
    }
}