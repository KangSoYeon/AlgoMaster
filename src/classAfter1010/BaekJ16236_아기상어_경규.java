package classAfter1010;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJ16236_아기상어_경규 {
   static int N;
   static int[][] ocean;
   static Shark babyShark;
   static pos sharkPos;
   
   static class Shark{
      int size;
      int stomach;
      
      public Shark() {
         this.size = 2;
         this.stomach = 0;
      }
   }
   
   static class pos{
      int x, y, time;
      public pos(int x, int y, int time) {
         this.x = x;
         this.y = y;
         this.time = time;
      }
   }
   
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      
      N = Integer.parseInt(br.readLine());
      
      babyShark = new Shark();
      ocean = new int[N][N];
      
      for(int i = 0; i < N; i++) {
         st = new StringTokenizer(br.readLine());
         for(int j = 0; j < N; j++) {
            int tmp = Integer.parseInt(st.nextToken());
            if(tmp != 0) {
               if(tmp == 9) {
                  sharkPos = new pos(i, j, 0);
               }
               ocean[i][j] = tmp;
            }
         }
      }
      pos fish;
      while((fish = findFish()) != null) {
         //이동 후 냠냠쓰
         eatFish();
         //상어 위치 정리
         ocean[sharkPos.x][sharkPos.y] = 0;
         ocean[fish.x][fish.y] = 9;
         sharkPos.x = fish.x;
         sharkPos.y = fish.y;
         sharkPos.time += fish.time;
      }
      
      System.out.println(sharkPos.time);
   }
   
   public static pos findFish() {
      Queue<pos> q = new LinkedList<pos>();
      boolean[][] visited = new boolean[N][N];
      pos Fish = null;
      int level = 0;
      q.add(sharkPos);
      
      while(!q.isEmpty()) {
         int size = q.size();
         level++;
         for(int i = 0; i < size; i++) {
            pos cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            visited[x][y] = true;
            //상
            if(x-1 >= 0 && !visited[x-1][y] && ocean[x-1][y] <= babyShark.size) {
               if(ocean[x-1][y] < babyShark.size && ocean[x-1][y] != 0) { //먹이발견
                  if(Fish == null) Fish = new pos(x-1, y, level);
                  else {
                     if(Fish.x > x-1) {
                        Fish = new pos(x-1, y, level);
                     }else if(Fish.x == x-1) {
                        if(Fish.y > y) Fish = new pos(x-1, y, level);
                     }
                  }
               }else { // 지나갑니다.
                  q.offer(new pos(x-1, y, level));
                  visited[x-1][y] = true;
               }
            }
            //하
            if(x+1 < N && !visited[x+1][y] && ocean[x+1][y] <= babyShark.size) {
               if(ocean[x+1][y] < babyShark.size && ocean[x+1][y] != 0) { //먹이발견
                  if(Fish == null) Fish = new pos(x+1, y, level);
                  else {
                     if(Fish.x > x+1) {
                        Fish = new pos(x+1, y, level);
                     }else if(Fish.x == x+1) {
                        if(Fish.y > y) Fish = new pos(x+1, y, level);
                     }
                  }
               }else { // 지나갑니다.
                  q.offer(new pos(x+1, y, level));
                  visited[x+1][y] = true;
               }
            }
            //좌
            if(y-1 >= 0 && !visited[x][y-1] && ocean[x][y-1] <= babyShark.size) {
               if(ocean[x][y-1] < babyShark.size && ocean[x][y-1] != 0) { //먹이발견
                  if(Fish == null) Fish = new pos(x, y-1, level);
                  else {
                     if(Fish.x > x) {
                        Fish = new pos(x, y-1, level);
                     }else if(Fish.x == x) {
                        if(Fish.y > y-1) Fish = new pos(x, y-1, level);
                     }
                  }
               }else { // 지나갑니다.
                  q.offer(new pos(x, y-1, level));
                  visited[x][y-1] = true;
               }
            }
            //우
            if(y+1 < N && !visited[x][y+1] && ocean[x][y+1] <= babyShark.size) {
               if(ocean[x][y+1] < babyShark.size && ocean[x][y+1] != 0) { //먹이발견
                  if(Fish == null) Fish = new pos(x, y+1, level);
                  else {
                     if(Fish.x > x) {
                        Fish = new pos(x, y+1, level);
                     }else if(Fish.x == x) {
                        if(Fish.y > y+1) Fish = new pos(x, y+1, level);
                     }
                  }
               }else { // 지나갑니다.
                  q.offer(new pos(x, y+1, level));
                  visited[x][y+1] = true;
               }
            }
         }
         if(Fish != null) break;
      }
      return Fish;
   }
   
   public static void eatFish() {
      babyShark.stomach++;
      if(babyShark.stomach == babyShark.size) {
         babyShark.size++;
         babyShark.stomach = 0;
      }
   }

}