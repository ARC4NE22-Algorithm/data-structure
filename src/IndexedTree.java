class IndexedTree {
    private long[] nodes;
    private int S;

    public IndexedTree(long[] arr) {
        S = 1;
        while(S < arr.length) {
            S = S << 1;
        }
        nodes = new long[S << 1];

        for(int i = 0; i < arr.length; i++) {
            nodes[S + i] = arr[i];
        }

        for(int i = S - 1; i > 0; i--) {
            nodes[i] = nodes[i << 1] + nodes[(i << 1) + 1];
        }
    }

    public long query(int left, int right, int node, int queryLeft, int queryRight) {
        if(queryLeft > right || queryRight < left) {
            return 0;
        } else if(queryLeft <= left && right <= queryRight) {
            return nodes[node];
        } else {
            int mid = (left + right) >> 1;
            long leftValue = query(left, mid, node * 2, queryLeft, queryRight);
            long rightValue = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);

            return leftValue + rightValue;
        }
    }

    public void update(int left, int right, int node, int target, long diff) {
        if(left > target || right < target) {
            return;
        } else {
            nodes[node] += diff;
            if(left != right) { // 자식이 있으면 left == right
                int mid = (left +  right) >> 1;
                update(left, mid, (node << 1), target, diff);
                update(mid + 1, right, (node << 1) + 1, target, diff);
            }
        }
    }
}