import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FlightRoutes {
    private static class Graph {
        private final Map<String, List<Edge>> adj;
        private final List<Edge> edges;

        public Graph() {
            adj = new HashMap<>();
            edges = new ArrayList<>();
        }

        public void addEdge(String src, String dest, int weight) {
            adj.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(src, dest, weight));
            adj.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Edge(dest, src, weight));
            edges.add(new Edge(src, dest, weight));
        }

        public List<Edge> getMST() {
            Collections.sort(edges);
            UnionFind uf = new UnionFind(adj.size());
            List<Edge> mst = new ArrayList<>();

            for (Edge edge : edges) {
                if (uf.union(edge.src, edge.dest)) {
                    mst.add(edge);
                }
            }

            return mst;
        }

        public List<String> findRoute(String start, String end) {
            List<Edge> mst = getMST();
            Map<String, List<String>> mstAdj = new HashMap<>();
            for (Edge edge : mst) {
                mstAdj.computeIfAbsent(edge.src, k -> new ArrayList<>()).add(edge.dest);
                mstAdj.computeIfAbsent(edge.dest, k -> new ArrayList<>()).add(edge.src);
            }

            List<String> route = new ArrayList<>();
            Set<String> visited = new HashSet<>();
            if (dfsRoute(start, end, mstAdj, visited, route)) {
                return route;
            } else {
                return Collections.emptyList();
            }
        }

        private boolean dfsRoute(String current, String end, Map<String, List<String>> mstAdj, Set<String> visited, List<String> route) {
            route.add(current);
            if (current.equals(end)) {
                return true;
            }

            visited.add(current);

            for (String neighbor : mstAdj.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbor) && dfsRoute(neighbor, end, mstAdj, visited, route)) {
                    return true;
                }
            }

            route.remove(route.size() - 1);
            return false;
        }
    }

    private static class Edge implements Comparable<Edge> {
        String src, dest;
        int weight;

        public Edge(String src, String dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    private static class UnionFind {
        private final Map<String, String> parent;
        private final Map<String, Integer> rank;

        public UnionFind(int size) {
            parent = new HashMap<>(size);
            rank = new HashMap<>(size);
        }

        public String find(String node) {
            if (!parent.containsKey(node)) {
                parent.put(node, node);
                rank.put(node, 0);
            }

            if (!node.equals(parent.get(node))) {
                parent.put(node, find(parent.get(node)));
            }

            return parent.get(node);
        }

        public boolean union(String node1, String node2) {
            String root1 = find(node1);
            String root2 = find(node2);

            if (root1.equals(root2)) {
                return false;
            }

            int rank1 = rank.get(root1);
            int rank2 = rank.get(root2);

            if (rank1 > rank2) {
                parent.put(root2, root1);
            } else if (rank1 < rank2) {
                parent.put(root1, root2);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank1 + 1);
            }

            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        ZipFile zipFile = new ZipFile("routes.csv.zip");
        ZipEntry zipEntry = zipFile.getEntry("routes.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(zipEntry)));
        String line;
        Graph flightRoutes = new Graph();

        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            String src = parts[2];
            String dest = parts[4];
            int weight;
            try {
                weight = Integer.parseInt(parts[7].trim());
            } catch (NumberFormatException e) {
                weight = 0;
            }
            flightRoutes.addEdge(src, dest, weight);
        }
        br.close();
        zipFile.close();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter start station: ");
        String start = scanner.nextLine().trim(); 
        System.out.print("Enter end station: "); 
        String end = scanner.nextLine().trim();
        
        List<String> route = flightRoutes.findRoute(start, end);
        if (!route.isEmpty()) {
            System.out.println("MST route between " + start + " and " + end + ":");
            for (String airport : route) {
                System.out.print(airport + " ");
            }
        } else {
            System.out.println("No route found between " + start + " and " + end);
        }
    }
}
