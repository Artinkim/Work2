import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class CowCode2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		long N = Long.parseLong(st.nextToken());
		N--;
		char answer;
		long length;
		while(true) {
			if(N<str.length()) {
				answer = str.charAt((int)N);
				break;
			}
			length = str.length();
			while(2*length<=N) {
				length*=2;
			}
			if(length==N) {
				N--;
				continue;
			}
			N -= length+1;
		}
		FileWriter fw = new FileWriter("cowcode.out");
		fw.write(answer+"");
		fw.close();
	}
}