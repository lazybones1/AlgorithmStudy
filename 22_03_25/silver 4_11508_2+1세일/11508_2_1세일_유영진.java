package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_11508_2_1세일 {
	static Integer[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		arr= new Integer[n];
		for(int i=0;i<n;i++) {
			arr[i] =Integer.parseInt(bf.readLine());
		}
		Arrays.sort(arr,(a,b)->b-a);
		
		int ans= 0;
		for(int i=0;i<n;i++) {
			if(i%3==2) //3개중 마지막
				continue;
			ans+=arr[i];
		}
		
		System.out.println(ans);
	}

}
