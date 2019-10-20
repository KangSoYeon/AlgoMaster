package classAfter1010;

import java.io.*;
import java.util.*;

class Shark{
   int x;
   int y;
   int size;
   int cnt;
   public Shark(int x, int y, int size, int cnt) {
      this.x = x;
      this.y = y;
      this.size = size;
      this.cnt = cnt;
   }
   
   
}

public class BaekJ16236_아기상어_원석 {
   static int N,time;
   static int[][] map;
   static Shark baby;
   
   
   public static void hunt() {
      int[] di = {-1,1,0,0}, dj = {0,0,-1,1};
      ArrayList<int[]> list = new ArrayList<>();
      Queue<int[]> que = new LinkedList<>();
      que.offer(new int[] {baby.x,baby.y,0});
      boolean[][] visit = new boolean[N][N];
      visit[baby.x][baby.y] = true;
      while(!que.isEmpty()) {
         int size = que.size();
         
         for(int i=0;i<size;i++) {
            int[] curr= que.poll();
            for(int dir=0;dir<4;dir++) {
               int nx= curr[0] + di[dir];
               int ny= curr[1] + dj[dir];
               if(nx >=0 && nx <N && ny >= 0 && ny <N && !visit[nx][ny] && map[nx][ny] <= baby.size) {
                  visit[nx][ny] = true;
                  que.offer(new int[] {nx,ny,curr[2]+1});
                  if(map[nx][ny] > 0 && map[nx][ny] < baby.size)
                     list.add(new int[] {nx,ny,curr[2]+1});
               }
            }
         }
         if(list.size() != 0)
            break;
      }
      Collections.sort(list,new Comparator<int[]>() {
         @Override
         public int compare(int[] o1, int[] o2) {
            if(o1[0] == o2[0]) {
               return Integer.compare(o1[1],o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
         }
      });
      baby.x = list.get(0)[0];
      baby.y = list.get(0)[1];
      baby.cnt++;
      time += list.get(0)[2];
      map[baby.x][baby.y]=0;
      
      if(baby.cnt == baby.size) {
         baby.size++;
         baby.cnt = 0;
      }
   }
   public static boolean callMom() {
      for(int i=0;i<N;i++) {
         for(int j=0;j<N;j++) {
            if(map[i][j] < baby.size && map[i][j] != 0)
               return true;
         }
      }
      return false;
   }
   
   
   public static void main(String[] args) throws Exception{
      
      BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(bf.readLine());
      map = new int[N][N];
      time = 0;
      StringTokenizer st = null;
      
      for(int i=0;i<N;i++) {
         st = new StringTokenizer(bf.readLine());
         for(int j=0;j<N;j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
            if(map[i][j] == 9) {
               baby = new Shark(i,j,2,0);
               map[i][j] = 0;
            }
         }
      }
      
      
      while(true) {
         if(!callMom())
            break;
         hunt();
      }
      System.out.println(time);
      
   }
   
   
   

}