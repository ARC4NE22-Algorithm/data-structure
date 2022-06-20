public class Main {
    public static void stackTest() {
        // Test Stack
        Stack<Integer> stack = new Stack<>();
        stack.append(20);
        System.out.println(stack.pop());
    }

    public static void queueTest() {
        // Test Queue
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(55);
        System.out.println(queue.peekHead());
        System.out.println(queue.dequeue());
    }

    public static void disjointSetTest() {
        // Test DisjointSet
        DisjointSet dSet = new DisjointSet(6);
        dSet.dump(DisjointSet.DumpType.ALL);

        dSet.union(0, 1);
        dSet.union(2, 3);
        dSet.union(3, 4);
        dSet.union(4, 5);
        dSet.union(5, 6);
        dSet.dump(DisjointSet.DumpType.ALL);

        dSet.union(0, 2);
        dSet.dump(DisjointSet.DumpType.ALL);

        for(int i = 0; i < dSet.size(); i++) {
            System.out.println(dSet.find(i));
        }
        dSet.dump(DisjointSet.DumpType.ALL);

        for(int i = 0; i < dSet.size(); i++) {
            System.out.println(dSet.find2(i));
        }
        dSet.dump(DisjointSet.DumpType.ALL);
    }

    public static void main(String[] args) {
        stackTest();
        queueTest();
        disjointSetTest();
    }
}
