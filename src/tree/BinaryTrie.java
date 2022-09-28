package tree;

public class BinaryTrie {

    // MAX_BIT_SIZE = log2(max input size)
    private static final int MAX_BIT_SIZE = 31;

    BinaryTrie[] nodes;
    BinaryTrie parent;
    int bitIdx, count;

    public BinaryTrie() {
        nodes = new BinaryTrie[2];
    }

    public boolean isLeaf() {
        return nodes[0] == null && nodes[1] == null;
    }

    /**
     * Add new value to BinaryTrie
     * @param value Value to add to BinaryTrie
     */
    public void add(int value) {
        BinaryTrie curr = this;

        for (int i = MAX_BIT_SIZE - 1; i > -1; i--) {
            int bitIdx = (value >> i) & 1;
            BinaryTrie nxt = curr.nodes[bitIdx];
            if (nxt == null) {
                nxt = new BinaryTrie();
                nxt.bitIdx = bitIdx;
                nxt.parent = curr;
                curr.nodes[bitIdx] = nxt;
            }
            curr.count++;
            curr = nxt;
        }
        curr.count++;
    }

    /**
     * Remove value from BinaryTrie
     * @param value Value to remove from BinaryTrie
     */
    public void remove(int value) {
        BinaryTrie curr = this;

        for (int i = MAX_BIT_SIZE - 1; i > -1; i--) {
            int bitIdx = (value >> i) & 1;
            if (curr.nodes[bitIdx] == null) {
                return;
            }
            curr = curr.nodes[bitIdx];
        }

        while (curr.isLeaf()) {
            int bitIdx = curr.bitIdx;
            curr = curr.parent;

            if (curr != null) {
                curr.nodes[bitIdx] = null;
            } else {
                break;
            }
        }
    }

    /**
     * check whether BinaryTrie has a value or not
     * @param value Value to check
     * @return Whether the value exists
     */
    public boolean contains(int value) {
        BinaryTrie curr = this;

        for (int i = MAX_BIT_SIZE - 1; i > -1; i--) {
            int bitIdx = (value >> i) & 1;
            if (curr.nodes[bitIdx] == null) {
                return false;
            }
            curr = curr.nodes[bitIdx];
        }
        return true;
    }

    /**
     * Find a minimum result of XOR operation
     * @param value Value to be XORed with the elements of the BinaryTrie
     * @return Minimum value of XOR result
     */
    public int xorMin(int value) {
        BinaryTrie curr = this;
        int xorValue = 0;

        for (int i = MAX_BIT_SIZE - 1; i > -1; i--) {
            int bitIdx = (value >> i) & 1;
            if (curr.nodes[bitIdx] == null) {
                bitIdx ^= 1;
                xorValue += (1 << i);
            }
            curr = curr.nodes[bitIdx];
        }

        return xorValue;
    }

    /**
     * Find a maximum result of XOR operation
     * @param value Value to be XORed with the elements of the BinaryTrie
     * @return Maximum value of XOR result
     */
    public int xorMax(int value) {
        BinaryTrie curr = this;
        int xorValue = 0;

        for (int i = MAX_BIT_SIZE - 1; i > -1; i--) {
            int oppositeIdx = ~(value >> i) & 1;
            if (curr.nodes[oppositeIdx] == null) {
                oppositeIdx ^= 1;
            } else {
                xorValue += (1 << i);
            }
            curr = curr.nodes[oppositeIdx];
        }

        return xorValue;
    }

    /**
     * Find a result of XOR operation (less than K)
     * @param value Value to be XORed with the elements of the BinaryTrie
     * @param K
     * @return XOR result less than K
     */
    public int xorLessThenK(int value, int K) {
        BinaryTrie curr = this;
        int count = curr.count;

        for (int i = MAX_BIT_SIZE - 1; i > -1; i--) {
            int mask = 1 << i;
            int bitIdx = (value >> i) & 1;
            int oppositeIdx = ~(value >> i) & 1;
            if (curr == null) break;

            if (mask <= K - 1) {
                K -= mask;
                curr = curr.nodes[oppositeIdx];
            } else {
                if (curr.nodes[oppositeIdx] != null) {
                    count -= curr.nodes[oppositeIdx].count;
                }
                curr = curr.nodes[bitIdx];
            }
        }
        return count;
    }
}
