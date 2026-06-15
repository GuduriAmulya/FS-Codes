// TC: for building O(N)
//TC:Range sum =O(logN)
//Tc:Update=LogN
//Space Complexity:o(n)
//recursion stack=logN
class SegmentTree {
    int[] tree;
    int n;
    // Constructor
    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n]; // safe size
        build(arr, 0, 0, n - 1);
    }
    // Build Segment Tree
    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            // Left child
            build(arr, 2 * node + 1, start, mid);
            // Right child
            build(arr, 2 * node + 2, mid + 1, end);
            // Merge
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    // Range Sum Query
    int query(int l, int r) {
        return queryUtil(0, 0, n - 1, l, r);
    }

    int queryUtil(int node, int start, int end, int l, int r) {
        // No overlap
        if (r < start || end < l) {
            return 0;
        }
        // Complete overlap
        if (l <= start && end <= r) {
            return tree[node];
        }
        // Partial overlap
        int mid = (start + end) / 2;
        int leftSum = queryUtil(2 * node + 1, start, mid, l, r);
        int rightSum = queryUtil(2 * node + 2, mid + 1, end, l, r);
        return leftSum + rightSum;
    }

    // Update value at index
    void update(int index, int value) {
        updateUtil(0, 0, n - 1, index, value);
    }

    void updateUtil(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (index <= mid) {
                updateUtil(2 * node + 1, start, mid, index, value);
            } else {
                updateUtil(2 * node + 2, mid + 1, end, index, value);
            }
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
}
public class SegMentSum {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};//0 indexed

        SegmentTree st = new SegmentTree(arr);

        // Query sum from index 1 to 3
        System.out.println("Sum (1,3): " + st.query(1, 3)); // 3+5+7 = 15

        // Update index 1 → value = 10
        st.update(1, 10);

        // Query again
        System.out.println("After update Sum (1,3): " + st.query(1, 3)); // 10+5+7 = 22
    }
}