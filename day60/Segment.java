import java.util.*;
class Segment{
    static int []arr;
    static int[]tree;
    static int[]lazy;
    static int n;
    public static void build(int ind, int start, int end){
        //1 based indexing..
        if(start==end){
            tree[ind]=arr[start];
            return;
        }
        int mid=(start+end)/2;
        build(2*ind,start,mid);
        build(2*ind+1,mid+1,end);
        tree[ind]=tree[2*ind]+tree[2*ind+1];
    }
    public static void propogate(int ind, int start,int end){
        //lazy updates..
        if(lazy[ind]!=0){
            tree[ind]+=(end-start+1)*lazy[ind];
            if(start!=end){
                lazy[2*ind]+=lazy[ind];
                lazy[2*ind+1]+=lazy[ind];
            }
            lazy[ind]=0;
        }
    }
    static void updateRange(int ind, int start, int end, int l, int r,int val){
        //add val to all indices b/w[l,r]
        if(end<l || start>r){
            return;
        }
        if(l<=start && end>=r){
            lazy[ind]+=val;
            propogate(ind,start,end);
            return;
        }
        int mid=(start+end)/2;
        updateRange(2*ind,start,mid,l,r,val);
        updateRange(2*ind+1, mid+1, end, l, r, val);
        tree[ind]=tree[2*ind]+tree[2*ind+1];
    }
    public static int query(int ind, int start, int end, int l, int r){
        //return the sum of elements bw [l,r]
        if(end<l || start>r)return 0;
        if(l<=start && end<=r)return tree[ind];
        int mid=(start+end)/2;
        return query(2*ind,start,mid,l,r)+query(2*ind+1,mid+1,end,l,r);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter size ofarray: ");
        n=sc.nextInt();
        arr=new int[n];
        System.out.println("Enter elements");
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        tree=new int[4*n];
        lazy=new int[4*n];
        build(1,0,n-1);
        while(true){
            System.out.println("\n1. Range Sum Query");
            System.out.println("2. Range Update");
            System.out.println("3. Point Update");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            if(choice==1){
                int l=sc.nextInt();
                int r=sc.nextInt();
                int ans=query(1,0,n-1,l,r);
                System.out.println("Sum = "+ans);
            }

        
        }


    }
}