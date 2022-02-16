package day0207;

import java.util.Scanner;

public class Main_16561_3ÀÇ¹è¼ö {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int n = sc.nextInt();
		
		int k = n/3;
		int ans = (k-2)*(k-1)/2;
		System.out.println(ans);
	}

}
