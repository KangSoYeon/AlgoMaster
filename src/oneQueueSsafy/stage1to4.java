package oneQueueSsafy;

import java.net.*;
import java.util.Arrays;
import java.io.*;
public class stage1to4 {

	// User and Game Server Information
	static final String NICKNAME = "강소연";
	static final String HOST = "127.0.0.1";
	static final int PORT = 1447; // Do not modify
	
	// predefined variables(Do not modify these values)
	static final int TABLE_WIDTH = 254;
	static final int TABLE_HEIGHT = 124;
	static final int NUMBER_OF_BALLS = 5;
	static final int[][] HOLES = {{0, 0}, {130, 0}, {260, 0}, {0, 130}, {130, 130}, {260, 130}};
	static double angle, power;
	
	public static void main(String[] args) {
		
		Socket socket = null;
		String recv_data = null;
		byte[] bytes = new byte[1024];
		int[][] balls = new int[NUMBER_OF_BALLS][2];

		try {
			socket = new Socket();
			System.out.println("Trying Connect: " + HOST + ":" + PORT);
			socket.connect(new InetSocketAddress(HOST, PORT));
			System.out.println("Connected: " + HOST + ":" + PORT);
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			String send_data = "9901/" + NICKNAME;
			bytes = send_data.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("Ready to play.");
			
			while (socket != null) {
				
				bytes = new byte[1024];
				
				int readByteCount = is.read(bytes);
				recv_data = new String(bytes, 0, readByteCount, "UTF-8");
				System.out.println("Data Received: " + recv_data);
				
				String[] split_data = recv_data.split("/");
				if (split_data[0].equals("9909")) break;
				
				int idx = 0;
				try {
					for (int i = 0; i < NUMBER_OF_BALLS; i++) {
						for (int j = 0; j < 2; j++) {
							balls[i][j] = Integer.parseInt(split_data[idx++]);
						}
					}
				}
				catch (Exception e) {
					bytes = new byte[1024];
					balls = new int[NUMBER_OF_BALLS][2];
					bytes = "9902/9902".getBytes("UTF-8");
					os.write(bytes);
					os.flush();
					System.out.println("Received Data has been currupted, Resend Requested.");
					continue;
				}
				
				angle = 0;
				power = 0;
				
				////////////////////////////////////////////////////////////////////
				// 주어진 정보(balls)를 바탕으로 샷 방향(angle)과 세기(power)를 결정하는 코드 작성 //
				//////////////////////////////////////////////////////////////////
				
				//stage1
				//angle = 47;
				//power = 60;
				
				//stage2
				//angle = 105.3;
				//power = 115;
				
	/*			for(int i=0; i<NUMBER_OF_BALLS; i++)
					System.out.println(Arrays.toString(balls[i]));*/
				
				//stage3
				for(int i=1; i<=3; i++) {
					//angle과 power를 구해오는 함수shoot
					if(balls[i][0] ==0 && balls[i][1]==0) continue;
					shoot(balls[0], balls[i]);
					break;
				}
				
				String merged_data = angle + "/" + power;
				bytes = merged_data.getBytes("UTF-8");
				
				os.write(bytes);
				os.flush();
				System.out.println("Data Sent: " + merged_data);
				
			}
			os.close();
			is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void shoot(int[] go, int[] target) {
		angle = getDegree(go, target);
		power = getDistance(go, target);
	}

	private static double getDistance(int[] go, int[] target) {
		double p=0;
		double temp = Math.pow(go[0]-target[0], 2) + Math.pow(go[1]-target[1], 2);
		double distance = Math.sqrt(temp);
		//p = (distance / Math.sqrt(2)) + distance*0.21; //힘 : 거리 = 루트2 : 1
		p = distance / (Math.sqrt(2)-0.05); //힘 : 거리 = 루트2 : 1
		return p;
	}

	private static double getDegree(int[] go, int[] target) {
		//평행이동 해서 각도값 return
		double mx = go[0];
		double my = go[1];
		double mtx = target[0] - mx; //평행이동
		double mty = target[1] - my;
		
		double deg = (Math.atan2(mtx, mty))*180/Math.PI;
		
		if(mtx>0 && mty>0) {//1사분면
			//각도 조금 더 더하기
			deg += 1;
		}else if(mtx<0 && mty>0) { //2사분면
			deg -= 1;
		}else if(mtx<0 && mty<0) { //3사분면
			deg += 3;
		}else if(mtx>0 && mty<0) { //4사분면
			deg +=1.5;
		}
		return deg;
		
	}
}
