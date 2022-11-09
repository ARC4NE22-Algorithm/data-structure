import java.util.Arrays;

public class DisjointSet {
    private int[] parents;
    private int[] ranks;
    private int[] nodeCounts;

    public enum DumpType {
        PARENTS,
        RANKS,
        NODE_COUNTS,
        ALL
    }

    /**
     * Constructor of DisjointSet
     * @param n Size of DisjointSet
     */
    public DisjointSet(int n) {
        parents = new int[n];   // 0 ~ n - 1
        ranks = new int[n];
        nodeCounts = new int[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i;
            nodeCounts[i] = 1;
        }
    }

    /**
     * Union both 'a' and 'b' nodes
     * @param a index of 'a' node
     * @param b index of 'b' node
     */
    public void union(int a, int b) {
        // Size out of bounds
        if(a < 0 || a > parents.length - 1) return;
        if(b < 0 || b > parents.length - 1) return;

        // Get parents
        int aRoot = find2(a);
        int bRoot = find2(b);

        // same root
        if(aRoot == bRoot) return;

        // rank compression (use depth)
        if(ranks[aRoot] < ranks[bRoot]) {
            //swap
            int temp = aRoot;
            aRoot = bRoot;
            bRoot = temp;
        }
        parents[bRoot] = aRoot;
        nodeCounts[aRoot] += nodeCounts[bRoot];

        if(ranks[aRoot] == ranks[bRoot]) {
            ranks[aRoot]++;
        }
    }

    /**
     * Find a parent of 'a' node
     * @param a index of 'a' node
     * @return parent of 'a' node
     */
    public int find(int a) {
        if(a < 0 || a > parents.length - 1) return -1;
        return parents[a];
    }

    /**
     * Find a parent of 'a' node
     * Path compression is performed with find
     * @param a index of 'a' node
     * @return root parent of 'a' node
     */
    public int find2(int a) {
        if(a < 0 || a > parents.length - 1) return -1;
        if(a == parents[a]) return a;
        else return parents[a] = find2(parents[a]);  // path compression
    }

    /**
     * Compare parent of both 'a' and 'b' nodes
     * @param a index of 'a' node
     * @param b index of 'b' node
     * @return
     */
    public boolean compareParent(int a, int b) {
        return find(a) == find(b);
    }

    /**
     * Compare parent of both 'a' and 'b' nodes
     * Path compression is performed with find
     * @param a index of 'a' node
     * @param b index of 'b' node
     * @return
     */
    public boolean compareParent2(int a, int b) {
        return find2(a) == find2(b);
    }

    /**
     * Get node count of children
     * @param a index of 'a' node
     * @return
     */
    public int getNodeCount(int a) {
        return nodeCounts[a];
    }

    /**
     * Size of DisjointSet (Node count of DisjointSet)
     * @return
     */
    public int size() {
        return parents.length;
    }

    /**
     * Print information of DisjointSet
     * @param type
     */
    public void dump(DumpType type) {
        StringBuilder sb = new StringBuilder();

        switch(type) {
            case PARENTS:
                sb.append("Parents: ");
                sb.append(Arrays.toString(parents));
                break;

            case RANKS:
                sb.append("Ranks: ");
                sb.append(Arrays.toString(ranks));
                break;

            case NODE_COUNTS:
                sb.append("Node counts: ");
                sb.append(Arrays.toString(nodeCounts));
                break;

            case ALL:
                dump(DumpType.PARENTS);
                dump(DumpType.RANKS);
                dump(DumpType.NODE_COUNTS);
                break;
        }

        System.out.println(sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DisjointSet{");
        sb.append("parents=");
        sb.append(Arrays.toString(parents));
        sb.append('}');

        return sb.toString();
    }
}