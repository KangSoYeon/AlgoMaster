
public class classTest {
	
	static class M{
		int x;
		int y;
		int dis;
		
		public M() {};
		public M(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}
	
	
	public static void main(String[] args) {
		M aa= new M(0,0,1);
		System.out.println(aa.x);
		System.out.println(aa.y);
		System.out.println(aa.dis);

	}

}
