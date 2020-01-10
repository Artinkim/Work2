import java.io.*;
import java.util.*;
public class LOADBALANC {
	static State[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		list = new State[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			list[i] = new State(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())/2);
		}
		Arrays.sort(list);
		int ret = list.length;
		for(int i = 0; i < n; i++) {
			ArrayList<State> below = new ArrayList<State>();
			ArrayList<State> above = new ArrayList<State>();
			for(int j = 0; j < n; j++) {
				if(list[j].y <= list[i].y) {
					below.add(list[j]);
				}
				else {
					above.add(list[j]);
				}
			}
			int belowIndex = 0;
			int aboveIndex = 0;
			while(belowIndex < below.size() || aboveIndex < above.size()) {
				int xBorder = Integer.MAX_VALUE;
				if(belowIndex < below.size()) {
					xBorder = Math.min(xBorder, below.get(belowIndex).x);
				}
				if(aboveIndex < above.size()) {
					xBorder = Math.min(xBorder, above.get(aboveIndex).x);
				}
				while(belowIndex < below.size() && below.get(belowIndex).x == xBorder) {
					belowIndex++;
				}
				while(aboveIndex < above.size() && above.get(aboveIndex).x == xBorder) {
					aboveIndex++;
				}
				ret = Math.min(ret, Math.max(Math.max(belowIndex, below.size() - belowIndex), Math.max(aboveIndex, above.size() - aboveIndex)));
			}
		}
		pw.println(ret);
		pw.close();
	}

	static class State implements Comparable<State> {
		public int x,y;

		public State(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public int compareTo(State s) {
			return x - s.x;
		}
	}

}