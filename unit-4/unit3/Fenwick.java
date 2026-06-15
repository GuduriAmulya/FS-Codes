
class FenwickTree {
    int[] BIT;
    int n;

    FenwickTree(int n) {
        this.n = n;
        BIT = new int[n + 1]; // 1-based indexing
    }

    void update(int i, int val) {
        while (i <= n) {
            BIT[i] += val;
            i += (i & -i);
        }
    }
        int sum(int i) {
        int res = 0;
        while (i > 0) {
            res += BIT[i];
            i -= (i & -i);
        }
        return res;
    }

    int rangeSum(int l, int r) {
        return sum(r) - sum(l - 1);
    }
}