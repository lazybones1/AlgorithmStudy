import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int max = 0;
		int[] data = new int[N];
		int input = 0;
		for(int i = 0; i < N; i++) {
			input = Integer.parseInt(br.readLine());
			data[i] = input;
			max = input>max?input:max;
		}
//		System.out.println(Arrays.toString(data));
//		System.out.println(max);
		
		int count = 1; //자기자신 개수 1
		int stick = data[N-1];
		for(int i = N-1; i>=0; i--) {
			if(data[i]>stick) {
				count++;
				stick = data[i];
			}else if(data[i] == max && data[i] != stick) {
				count++;
				break;
			}
		}
		System.out.println(count);
	}
}