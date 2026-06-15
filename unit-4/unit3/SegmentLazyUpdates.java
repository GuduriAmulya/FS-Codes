class SegmentTreeLazy {
    int[] tree;
    int[] lazy;
    int n;
    SegmentTreeLazy(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        lazy = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }
    // Build
    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
    // Push lazy updates
    void push(int node, int start, int end) {
        if (lazy[node] != 0) {
            // Apply pending update
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    // Range Update
    void updateRange(int l, int r, int val) {
        updateRangeUtil(0, 0, n - 1, l, r, val);
    }

    void updateRangeUtil(int node, int start, int end, int l, int r, int val) {
        push(node, start, end);

        // No overlap
        if (r < start || end < l) return;

        // Complete overlap
        if (l <= start && end <= r) {
            lazy[node] += val;
            push(node, start, end);
            return;
        }

        // Partial overlap
        int mid = (start + end) / 2;
        updateRangeUtil(2 * node + 1, start, mid, l, r, val);
        updateRangeUtil(2 * node + 2, mid + 1, end, l, r, val);

        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    // Range Query
    int query(int l, int r) {
        return queryUtil(0, 0, n - 1, l, r);
    }

    int queryUtil(int node, int start, int end, int l, int r) {
        push(node, start, end);

        // No overlap
        if (r < start || end < l) return 0;

        // Complete overlap
        if (l <= start && end <= r) return tree[node];

        int mid = (start + end) / 2;
        int left = queryUtil(2 * node + 1, start, mid, l, r);
        int right = queryUtil(2 * node + 2, mid + 1, end, l, r);

        return left + right;
    }
}

public class SegmentLazyUpdates {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};

        SegmentTreeLazy st = new SegmentTreeLazy(arr);

        // Add +10 to range [1, 3]
        st.updateRange(1, 3, 10);

        // Query sum [1, 3]
        System.out.println(st.query(1, 3)); // (3+10)+(5+10)+(7+10)=45
    }
}