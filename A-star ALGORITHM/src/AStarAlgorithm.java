import java.util.*;

public class AStarAlgorithm {
    private static final double NEIGHBOR_THRESHOLD = 2000000.0; // 2000 km threshold

    public static List<Satellite> findShortestPath(Satellite start, Satellite goal, List<Satellite> satellites) {
        Map<Satellite, Double> gScore = new HashMap<>();
        Map<Satellite, Double> fScore = new HashMap<>();
        Map<Satellite, Satellite> cameFrom = new HashMap<>();
        Map<Satellite, List<Satellite>> graph = buildGraph(satellites);

        PriorityQueue<Satellite> openSet = new PriorityQueue<>(Comparator.comparingDouble(fScore::get));
        gScore.put(start, 0.0);
        fScore.put(start, heuristic(start, goal));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Satellite current = openSet.poll();
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            for (Satellite neighbor : graph.get(current)) {
                double tentativeGScore = gScore.get(current) + current.distanceTo(neighbor);

                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, goal));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return Collections.emptyList(); // Return empty path if no path found
    }

    private static double heuristic(Satellite a, Satellite b) {
        return a.distanceTo(b);
    }

    private static List<Satellite> reconstructPath(Map<Satellite, Satellite> cameFrom, Satellite current) {
        List<Satellite> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    private static Map<Satellite, List<Satellite>> buildGraph(List<Satellite> satellites) {
        Map<Satellite, List<Satellite>> graph = new HashMap<>();
        for (Satellite sat : satellites) {
            List<Satellite> neighbors = new ArrayList<>();
            for (Satellite other : satellites) {
                if (!sat.equals(other) && sat.distanceTo(other) <= NEIGHBOR_THRESHOLD) {
                    neighbors.add(other);
                }
            }
            graph.put(sat, neighbors);
            System.out.println(sat.getName() + " has " + neighbors.size() + " neighbors within threshold."); // Debug output
        }
        return graph;
    }
}
