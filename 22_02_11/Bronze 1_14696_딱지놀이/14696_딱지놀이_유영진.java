package day0211;

import java.util.Scanner;

public class Main_14696_µüÁö³îÀÌ {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] aNum, bNum; 
		
		int N = sc.nextInt();
		
		for(int n=1;n<=N;n++) {
			aNum = new int[5];
			bNum = new int[5];
			
			int a = sc.nextInt();
			for(int i=0;i<a;i++) {
				int fig = sc.nextInt();
				aNum[fig]++;
			}
			
			int b = sc.nextInt();
			for(int i=0;i<b;i++) {
				int fig = sc.nextInt();
				bNum[fig]++;
			}
			
			for(int i=4;i>0;i--) {
				if(i==1) {
					if(aNum[i]==bNum[i]) System.out.println("D");
					
				}
				if(aNum[i]>bNum[i]) {
					System.out.println("A");
					break;
				}else if(aNum[i]<bNum[i]) {
					System.out.println("B");
					break;
				}else continue; 
			}
		}
		
		
		
	}

}
