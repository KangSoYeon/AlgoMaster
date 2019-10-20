package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJ16236_아기상어_현정{

   static int N;
   static boolean[][] visited;
   static int[][] map;
   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      StringTokenizer st = null;
      
      int shark = 2;
      boolean isFind = false;
      map = new int[N][N];
      visited = new boolean[N][N];
      int sr = 0;
      int sc = 0; //상어 위치
      for(int i=0; i<N; i++) {
         st = new StringTokenizer(br.readLine());
         for(int j=0; j<N; j++) {
            map[i][j] = (int)(st.nextToken().charAt(0)-'0');
            
            if(map[i][j]<shark && map[i][j]!=0) {
               isFind = true; //먹을 수 있는 물고기가 있다
            }
            
            if(map[i][j]==9) {
               sr = i;
               sc = j;
               map[i][j]=0;
            }
            
         }
      }
      
      int Answer=0;
      
      if(isFind) {
         int[] current;
         int fish=0; // 물고기수

         while(true) {
            current = find(shark,sr,sc);
            if(current.length==2) break;
            sr = current[0];
            sc = current[1];
            Answer+=current[2];
            fish++;
            if(fish==shark) {
               fish=0;
               shark++;
            }
            map[sr][sc] = 0;
            visited = new boolean[N][N];
         }
         
      }
      
      System.out.println(Answer);
   }
   
   private static int[] find(int shark, int r, int c){
      int count = 1;
      // 북 서 동 남
      int[] dx = {-1,0, 0,1};
      int[] dy = {0,-1,  1, 0};
      visited[r][c] = true;
      boolean isReturn = false;
      int rr=N, rc=N;
      
      Queue<int[]> queue = new LinkedList<int[]>();
      
      queue.offer(new int[] {r,c});
      while(!queue.isEmpty()) {
         int qsize = queue.size();
         
         while(qsize-->0) { // 같은 레벨
            int[] current = queue.poll();
            
            for(int i=0; i<4; i++) {
               int nr = current[0] + dx[i];
               int nc = current[1] + dy[i];
               
               if(nr>=0 && nr<N && nc>=0 && nc<N && !visited[nr][nc] && map[nr][nc]<=shark) { //갈수 있는 길
                  if(map[nr][nc] > 0 && map[nr][nc]<shark) { // 먹을수 있는 물고기 존재
                     isReturn = true;
                     if(nr<rr) {
                        rr = nr;
                        rc = nc;
                     }else if(nr==rr && nc<rc) {
                        rr = nr;
                        rc = nc;
                     }
                     
                  }
                  queue.offer(new int[] {nr,nc});
                  visited[nr][nc]=true;
               }
            }
         }
         if(isReturn) {
            return new int[] {rr,rc,count};
         }
         count++;
         
      }
      
      
      return new int[] {-1,-1};
      
   }

}