import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point5 {
	int x;
	int y;
	int c;
	Point5(int x,int y, int c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}
}
public class MilkPails {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("pails.in"));
		FileWriter fw = new FileWriter("pails.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Point5> queue = new LinkedList<Point5>();
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		queue.add(new Point5(X,Y,0));
		Point5 current;
		int answer = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			current = queue.poll();
			if(current.c < K) {
				queue.add(new Point5(0,Y,current.c+1));
				queue.add(new Point5(X,0,current.c+1));
				if(current.x+current.y > X) {
					queue.add(new Point5(X,current.y-X+current.x,current.c+1));
				} else {
					queue.add(new Point5(current.x+current.y,0,current.c+1));
				}
				if(current.x+current.y > Y) {
					queue.add(new Point5(current.x-Y+current.y,Y,current.c+1));
				} else {
					queue.add(new Point5(0,current.x+current.y,current.c+1));
				}
			}
			if(current.c==K) {
				answer = Math.min(answer, Math.abs(M-(current.x+current.y)));
			}
		}
		fw.write(answer+"");
		fw.close();
	}
}
