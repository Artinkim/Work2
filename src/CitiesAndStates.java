import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class CitiesAndStates {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		TreeMap<String, Integer> tm = new TreeMap<String,Integer>();
		int N = Integer.parseInt(st.nextToken());
		String[] codes = new String[N];
		String temp1, temp2;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			temp1 = st.nextToken().substring(0,2);
			temp2 = st.nextToken();
//			System.out.println(temp2+temp1);
			if(tm.containsKey(temp2+temp1)) {
				tm.put(temp2+temp1,tm.get(temp2+temp1)+1);
//				System.out.println(tm.get(temp2+temp1)+"C");
			} else {
				tm.put(temp2+temp1,1);
			}
			if(temp1.equals(temp2)) {
				codes[i] = "hi";
			} else {
				codes[i] = temp1+temp2;
			}
		}
		int count = 0;
		for(int i = 0;i<N;i++) {
			if(tm.containsKey(codes[i])) {
//				System.out.println(codes[i] + " " + tm.get(codes[i]));
				count +=tm.get(codes[i]);
			}
		}
//		System.out.println(tm);
		FileWriter fw = new FileWriter("citystate.out");
		fw.write(count/2+"");
		fw.close();
	}
}
