// Operation	Time
// Build	O(n)
// getMax	O(log n)
// Update	O(log n)

// Space → O(n) (≈ 4n array)

class SegmentTreeMax {
    int[] tree;
    int n;

    // Constructor
    SegmentTreeMax(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    // Build tree
    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;

            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);

            tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    // Range Max Query
    int getMax(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    int query(int node, int start, int end, int l, int r) {
        // No overlap
        if (r < start || end < l) {
            return Integer.MIN_VALUE;
        }

        // Complete overlap
        if (l <= start && end <= r) {
            return tree[node];
        }

        // Partial overlap
        int mid = (start + end) / 2;
        int leftMax = query(2 * node + 1, start, mid, l, r);
        int rightMax = query(2 * node + 2, mid + 1, end, l, r);

        return Math.max(leftMax, rightMax);
    }

    // Update index with new value
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

            tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }
}

public class SegmentTreeMax {
    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 4, 9, 3};

        SegmentTreeMax st = new SegmentTreeMax(arr);

        // Query max from index 1 to 4
        System.out.println("Max (1,4): " + st.getMax(1, 4)); // 9

        // Update index 2 → value = 10
        st.update(2, 10);

        // Query again
        System.out.println("After update Max (1,4): " + st.getMax(1, 4)); // 10
    }
}