import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

class Point4 {
	int x;
	int y;
	Point4(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
public class LoadBalance {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
		FileWriter fw = new FileWriter("balancing.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Point4[] points = new Point4[N];
		long averageX = 0, averageY = 0;
		for(int i = 0;i<N;i++) {
			 st = new StringTokenizer(br.readLine());
			 points[i] = new Point4(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			 averageX += points[i].x;
			 averageY += points[i].y;
		}
		averageX = averageX/N;
		averageY = averageY/N;
		int a = 0,b=0,c=0,d=0;
		for(int i = 0;i<N;i++) {
			if(points[i].x<=averageX && points[i].y<=averageY) {
				a++;
			}
			if(points[i].x>=averageX && points[i].y<=averageY) {
				b++;
			}
			if(points[i].x>=averageX && points[i].y>=averageY) {
				c++;
			}
			if(points[i].x<=averageX && points[i].y>=averageY) {
				d++;
			}
		}
		a = Math.max(a, b);
		a = Math.max(a, c);
		a = Math.max(a, d);
		fw.write(a+"");
		fw.close();
	}
}
