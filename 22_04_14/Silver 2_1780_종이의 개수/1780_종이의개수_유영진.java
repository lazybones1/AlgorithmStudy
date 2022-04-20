package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1780_종이의개수 {
	 public static int[][] arr;
	    public static int[] ans = new int[3];
	    public static void main(String[] args) throws IOException {
	        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    
	        int n = Integer.parseInt(bf.readLine());
	        
	        arr = new int[n][n];
	        for(int i = 0; i < n; i++){
	        	StringTokenizer st = new StringTokenizer(bf.readLine());
	            for(int j = 0; j < n; j++){
	                arr[i][j] = Integer.parseInt(st.nextToken());
	            }
	        }

	        slice(n,0,0);
	        for(int i = 0; i < ans.length; i++){
	            System.out.println(ans[i]);
	        }
	    }

	    public static void slice(int size, int n, int m){

	    
	        if(numCheck(size,n,m)){
	            if(arr[n][m] == -1){
	                ans[0]++;
	            }else if(arr[n][m] == 0){
	                ans[1]++;
	            }else{
	                ans[2]++;
	            }
	      
	        }else{
	            int nsize = size/3;

	            slice(nsize, n, m); // 1사분면
	            slice(nsize, n,m+nsize); // 2사분면
	            slice(nsize, n,m+nsize*2); // 3사분면
	            
	            slice(nsize, n+nsize, m); // 4사분면
	            slice(nsize, n+nsize,m+nsize); // 5사분면
	            slice(nsize, n+nsize,m+nsize*2); // 6사분면
	            
	            slice(nsize, n+nsize*2, m); // 7사분면
	            slice(nsize, n+nsize*2,m+nsize); // 8사분면
	            slice(nsize, n+nsize*2,m+nsize*2); // 9사분면
	        }
	    }

	    public static boolean numCheck(int size, int n, int m){
	        int first = arr[n][m];
	        boolean check = true;
	        for(int i = n; i < n+size; i++){
	            for(int j = m; j < m+size; j++){
	                if(first != arr[i][j]){
	                    check = false;
	                    break;
	                }
	            }
	        }
	        return check;
	    }
}
