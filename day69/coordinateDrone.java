/*
A delivery drone starts from a charging station located at coordinate (0, 0) 
at time T = 0. The drone can move only in four directions:
East, North, West, South

Initially, the drone is moving towards the East at a speed of 1 unit per time unit.

During its journey, the drone receives some turn instructions at specific time units. 
Each instruction tells the drone whether to turn left or right at that time.

You are given:
- The current time N
- The number of turn instructions L
- A list of L instructions, where each instruction contains:
	- the time at which the drone turns
	- the direction of turn: 0 means left turn, 1 means right turn

Your task is to find the drone’s current position at time N and 
return: x-coordinate + y-coordinate

Sample Input-1: 
2 
1
1 0 

Sample Output-1: 
2 

Explanation: 
---------------
At time =0, position =(0,0). 
At time = 1, position = (1, 0). Here, he takes a left turn. 
At time = 2 position = (1,1). 

Sample Input-2:
2
1
1 1
 
Sample Output-2:
0 

Explanation: 
---------------
At time =0, position =(0,0). 
At time = 1, position = (1, 0). Here, he takes a right turn. 
At time = 2, position = (1, -1).


TC:

10
3
2 0
5 1
8 0

ans:
time=0=>(0,0)
till t=2 (2,0) now turn left.. meaning north.
dir=(0+1)%4=>1 dx[1]=0, dy[1]=1=>north..
in next iteration i=1..
so while((2)t<5)y+=1..y=3 
so at t=5 (2,3)
now dir=1 meaning right.. dir=(1+3)%4=>0 dx[0]=1, dy[0]=0
while(t(5)<8){ x+=1.. =>x=5,y=3(5,3)\
now time=8.. and turn left
dir=(0+1)%4=>1 dx[1]=0, dy[1]=1
while(t(8)<n(10)) y+=1.. no x change.. y=3+2=>5 (5,5)

ans=5+5=10


*/

import java.util.*;
class coordinateDrone{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int l=sc.nextInt();
        int[][]instr=new int[l][2];
        for(int i=0;i<l;i++){
            instr[i][0]=sc.nextInt();
            instr[i][1]=sc.nextInt();
        }
        Arrays.sort(instr,(a,b)->a[0]-b[0]);//ascend of position..
        int x=0;
        int y=0;
        int time=0;
        int dir=0;//moving east initially..
        int dx[]={1,0,-1,0};//when east x+=1, North x doest change, west=>x-=1, south =>no change
        int dy[]={0,1,0,-1};
        for(int i=0;i<l;i++){
            int t=instr[i][0];
            while(time<t && time<N){
                x+=dx[dir];
                y+=dy[dir];
                time++;
            }
            if(time==N){
                break;
            }
            if(instr[i][1]==0){
                //left.. +1..
                dir=(dir+1)%4;
            }
            else{
                dir=(dir+3)%4;//right.
            }
        }
        while(time<N){
            x+=dx[dir];
            y+=dy[dir];
            time++;
        }
        System.out.println(x+y);
    }
}