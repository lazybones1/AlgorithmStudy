import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

class Main {
    /*
    formula : 주어지는 문자열
    visited[] : 방문 여부 확인
    map : 괄호쌍 저장 맵 (여는괄호 인덱스, 닫는괄호 인덱스)
    ansmap : 결과값 모음
     */
    static String formula;
    static boolean[] visited;
    static Map<Integer, Integer> map;
    static Map<String, Integer> ansmap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        formula = br.readLine();

        Stack<Integer> stack = new Stack<>();
        map = new HashMap();
        ansmap = new HashMap<>();
        visited = new boolean[formula.length()+1];

        //괄호쌍 추적
        for (int i = 0; i<formula.length(); i++){
            char c = formula.charAt(i);
            if(c=='('){
                stack.push(i);
            }else if(c==')'){
                //여는 괄호 값으로 닫는괄호 찾을 예정
                map.put(stack.pop(), i);
            }
        }

        //현재 값은 결과값으로 사용하지 않음
        ansmap.put(formula, 0);

        solve(0,"");

        //사전순 정렬
        String[] arr = ansmap.keySet().toArray(new String[map.size()]);
        Arrays.sort(arr);

        //출력
        for(int i = 1; i<arr.length; i++){
            bw.write(arr[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int start, String s){
        if(start == formula.length()){
            if(!s.equals(formula)){
                if(!ansmap.containsKey(s)){
                    //중복 없이 추가
                    ansmap.put(s, 0);
                }
            }
            return;
        }

        char c = formula.charAt(start);

        if(c == '('){
            //괄호 제거 안한 경우
            solve(start+1,  s + Character.toString(c));

            //괄호 제거한 경우
            //Hashmap을 사용하여 쌍이되는 닫는 괄호 사용 막기
            visited[map.get(start)] =true;
            solve(start+1, s);
            visited[map.get(start)] =false;

        } else if(c==')'){
            if(visited[start]){
                //출력하면 안되는 원소
                solve(start+1, s);
            } else{
                //출력해야하는 원소
                solve(start+1, s + Character.toString(c));
            }
        } else{
            solve(start+1, s + Character.toString(c));
        }
    }
}