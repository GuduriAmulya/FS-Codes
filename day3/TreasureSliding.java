
// You are a treasure hunter exploring an ancient vault filled with 
// treasure boxes. The vault is represented as an array treasures of 
// n integers, where each integer corresponds to the value of a treasure. 
// You have a special key that allows you to scan and select treasures 
// from a sub-vault (a segment of the array) of size k. Additionally, 
// you have a magical power factor f and a priority filter x.

// The priority-weighted treasure sum of a sub-vault is calculated as follows:
// 	1. Count the occurrences of each treasure value in the sub-vault.
// 	2. Assign a priority score to each treasure based on its frequency 
// 	multiplied by the treasure's value raised to the power of f 
// 	(i.e., priority_score[treasure] = frequency[treasure] * (value^f)).
// 	3. Select only the top x treasures based on their priority scores. 
// 	If two treasures have the same priority score, the treasure with 
// 	the higher value is prioritized.
// 	4. Calculate the total value of the selected treasures.

// Your task is to return an integer array priority_sums of length n - k + 1, 
// where priority_sums[i] represents the priority-weighted treasure sum for 
// the sub-vault corresponding to treasures[i..i + k - 1].

// Input Format:
// ---------------
// Line-1: Four space separated integers, N, K, X, F
// Line-2: N space separated integers, boxes[].

// Output Format:
// -----------------
// An integer array, priority_sums[], of length n - k + 1


// Sample Input-1:
// -----------------
// 8 5 2 2
// 1 2 3 1 2 2 3 4

// Sample Output-1:
// --------------------
// [7, 9, 10, 7]

// Explanation:
// We calculate the priority-weighted treasure sum for each sub-vault:

// 1. Sub-vault 1: [1, 2, 3, 1, 2]
//    - Frequencies: {1: 2, 2: 2, 3: 1}
//    - Priority scores:
//      - 1 → 2 * (1^2) = 2
//      - 2 → 2 * (2^2) = 8
//      - 3 → 1 * (3^2) = 9
//    - Top 2 treasures by priority: 3 (score 9) and 2 (score 8).
//    - Total value: 2 + 3 + 2  = 7.

// 2. Sub-vault 2: [2, 3, 1, 2, 2]
//    - Frequencies: {2: 3, 3: 1, 1: 1}
//    - Priority scores:
//      - 2 → 3 * (2^2) = 12
//      - 3 → 1 * (3^2) = 9
//      - 1 → 1 * (1^2) = 1
//    - Top 2 treasures by priority: 2 (score 12) and 3 (score 9).
//    - Total value: 2 + 2 + 2 + 3 = 9.

// 3. Sub-vault 3: [3, 1, 2, 2, 3]
//    - Frequencies: {3: 2, 2: 2, 1: 1}
//    - Priority scores:
//      - 3 → 2 * (3^2) = 18
//      - 2 → 2 * (2^2) = 8
//      - 1 → 1 * (1^2) = 1
//    - Top 2 treasures by priority: 3 (score 18) and 2 (score 8).
//    - Total value: 3 + 2 + 2 + 3 = 10.

// 4. Sub-vault 4: [1, 2, 2, 3, 4]
//    - Frequencies: {1: 1, 2: 2, 3: 1, 4: 1}
//    - Priority scores:
//      - 2 → 2 * (2^2) = 8
//      - 3 → 1 * (3^2) = 9
//      - 4 → 1 * (4^2) = 16
//      - 1 → 1 * (1^2) = 1
//    - Top 2 treasures by priority: 4 (score 16) and 3 (score 9).
//    - Total value: 3 + 4  = 7.

// Sample Input-2:
// -----------------
// 6 3 2 1
// 5 5 6 7 5 6

// Sample Output-1:
// --------------------
// [16, 13, 13, 13]

// Constraints:
// 1. 1 <= n == treasures.length <= 50
// 2. 1 <= treasures[i] <= 50
// 3. 1 <= x <= k <= treasures.length
// 4. 1 <= f <= 10

import java.util.*;
class TreasureSliding{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();//window sz
        int x=sc.nextInt();//top x
        int f=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int ans[]=new int[n-k+1];
        int index=0;
        Map<Integer,Integer>mp=new HashMap<>();
        for(int i=0;i<k;i++){
            mp.put(arr[i],mp.getOrDefault(arr[i],0)+1);
        }
        int size=mp.size();
        PriorityQueue<int[]>pq=new PriorityQueue<>(Comparator.comparing(a->-a[1]));
        for(Map.Entry<Integer,Integer>e:mp.entrySet()){
            int key=e.getKey();
            int v=e.getValue();
            //System.out.println(key+" "+v);
            int power=(int)Math.pow(key,f);
            //System.out.println("k: "+key+" "+(int)(v*(Math.pow(key,f))));
            pq.offer(new int[]{key,v*power});
        }
        int i=0;
        int globalMax=0;
        while(i<x){
            int top1[]=pq.poll();
            globalMax+=top1[0]*mp.get(top1[0]);
            i++;
        }
        ans[index++]=globalMax;
        //System.out.println("max "+globalMax);
        int max=0;
        for(int j=k;j<n;j++){
            max=0;
            //System.out.println("Helo: ");
            mp.put(arr[j],mp.getOrDefault(arr[j],0)+1);
            System.out.println(mp.get(arr[j-k])+" "+arr[j-k]);
            if(mp.containsKey(arr[j-k])){
            mp.put(arr[j-k],mp.get(arr[j-k])-1);
            if(mp.get(arr[j-k])==0)mp.remove(arr[j-k]);
            }
            //printMp(mp);
            PriorityQueue<int[]>q=new PriorityQueue<>(Comparator.comparingInt(a->-a[1]));
            addtoPQ(q,mp,f);
            int d=0;
            while(d<x){
                int top1[]=q.poll();
                int val=top1[0]*mp.get(top1[0]);
                //System.out.println("top: "+top1[0]+" "+mp.get(top1[0]));
                max+=(val);
                d++;
                //System.out.println("max: "+max);
            }
            ans[index++]=max;
            max=0;
        }
        System.out.println(Arrays.toString(ans));
        
    }
    public static void addtoPQ(PriorityQueue<int[]>pq,Map<Integer,Integer>mp,int f){
        for(Map.Entry<Integer,Integer>e:mp.entrySet()){
            int key=e.getKey();
            int v=e.getValue();
            int power=(int)Math.pow(key,f);
            //System.out.println(key+" "+power+" "+v);
            pq.offer(new int[]{key,v*power});
        }
    }
    public static void printMp(Map<Integer,Integer>mp){
        System.out.println("Map: ");
        for(Map.Entry<Integer,Integer>e:mp.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }
    }
}