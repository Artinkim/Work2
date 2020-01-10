import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int x,y,p;
	Pair(int x,int y, int p) {
		this.x = x;
		this.y = y; 
		this.p = p;
	}
}

public class MooCast {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
		FileWriter fw = new FileWriter("moocast.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> queue = new LinkedList<Integer>();
		int N = Integer.parseInt(st.nextToken());
		Pair[] arr = new Pair[N];
		ArrayList<Integer>[] arr2 = new ArrayList[N];
		boolean[] visited = new boolean[N];
		for(int i = 0;i<N;i++) {
			 st = new StringTokenizer(br.readLine());
			 arr[i] = new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			 arr2[i] = new ArrayList<Integer>();
		}
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				if(Point2D.distance(arr[i].x, arr[i].y, arr[j].x, arr[j].y) <= arr[i].p) {
					arr2[i].add(j);
				}
			}
		}
		int temp;
		int count = 0;
		int finalCount = 0;
		for(int i = 0;i<N;i++) {
			count = 0;
			queue.add(i);
			visited[i] = true;
			while(!queue.isEmpty()) {
				count++;
				temp = queue.poll();
				for(int j = 0;j<arr2[temp].size();j++) {
					if(!visited[arr2[temp].get(j)]) 
						queue.add(arr2[temp].get(j));
						visited[arr2[temp].get(j)] = true;
				}
			}
			finalCount = Math.max(finalCount, count);
			Arrays.fill(visited, false);
		}
		fw.write(finalCount+"");
		fw.close();
	}
}
