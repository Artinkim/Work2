import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class HoofPaperScissors {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hps.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int temp = 0;
		int[] prefixSumP = new int[N];
		int[] prefixSumH = new int[N];
		int[] prefixSumS = new int[N];
		int h =0,p =0,s = 0;
		for (int i = 0; i < N; i++) {
			temp = br.readLine().charAt(0);
			System.out.println(temp);
			if(temp == 80) {//P
				p++;
			}
			prefixSumP[i] = p;
			if(temp == 72) {//H
				h++;
			}
			prefixSumH[i] = h;
			if(temp == 83) {//S
				s++;
			}
			prefixSumS[i] = s;
		}
		int count = 0;
		int highscore = 0;
		for (int i = 0; i < N; i++) {
			if(prefixSumP[i]>=prefixSumH[i] && prefixSumP[i]>=prefixSumS[i]) {
				count = prefixSumP[i];
				if(h-prefixSumH[i]>s-prefixSumS[i]) {
					count+= h-prefixSumH[i];
				} else {
					count+= s-prefixSumS[i];
				}
			}
			if(prefixSumH[i]>=prefixSumP[i] && prefixSumH[i]>=prefixSumS[i]) {
				count = prefixSumH[i];
				if(p-prefixSumP[i]>s-prefixSumS[i]) {
					count+= p-prefixSumP[i];
				} else {
					count+= s-prefixSumS[i];
				}
			}
			if(prefixSumS[i]>=prefixSumP[i] && prefixSumS[i]>=prefixSumH[i]) {
				count = prefixSumS[i];
				if(p-prefixSumP[i]>h-prefixSumH[i]) {
					count+= p-prefixSumP[i];
				} else {
					count+= h-prefixSumH[i];
				}
			}
			System.out.println(prefixSumH[i]);
			System.out.println(prefixSumS[i]);
			System.out.println(prefixSumP[i]);
			highscore = Math.max(highscore, count);
		}
		FileWriter fw = new FileWriter("hps.out");
		fw.write(highscore+"");
		fw.close();
		
	}
}
