import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class CowCode {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		int length = str.length();
		long N = Integer.parseInt(st.nextToken());
		long itterations = (long) Math.ceil((Math.log(N)/Math.log(2)))-1;
		for(int i = 0;i<Math.pow(2, itterations);i++) {
			str = str.charAt(str.length()-1)+str.substring(0,(str.length()-1));
		}
		FileWriter fw = new FileWriter("cowcode.out");
		System.out.println(itterations);
		System.out.println(str);
		System.out.println(N-((length)*((int) Math.pow(2, itterations-1))));
		fw.write(str.charAt((int) (N-1))+"");
		fw.close();
	}
}
