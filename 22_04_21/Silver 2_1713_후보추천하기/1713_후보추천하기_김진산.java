import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*BOJ_1713_후보추천하기*/
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());//사진틀의 개수

        int idx = 0; //사진틀 인덱스용
        Picture pictures[] = new Picture[N]; //사진틀 배열

        st = new StringTokenizer(br.readLine(), " ");
        int recoNum = Integer.parseInt(st.nextToken()); //전체 학생의 총 충전 횟수

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i<recoNum; i++){ //추천받은 학생 번호
            int c = Integer.parseInt(st.nextToken());
            boolean exist = false;

            int minIdx = 0; //제거할 인덱스
            int maxday = Integer.MAX_VALUE; //가장 오래된 
            int minVote = Integer.MAX_VALUE; // 가장 적은 투표수

            for (int j = 0; j<idx; j++){ //사진틀 탐색
                if (pictures[j].num == c){ //사진틀에 있는 학생인 경우
                    pictures[j].cnt++; //투표수 증가
                    exist = true;
                    break;
                }
                else{
                    if(pictures[j].cnt < minVote){ //최소 사진 찾기
                        minIdx = j;
                        minVote = pictures[j].cnt;
                        maxday = pictures[j].day;
                    } else if(pictures[j].cnt == minVote && pictures[j].day < maxday){ //오래된 사진 찾기
                        minIdx = j;
                        maxday = pictures[j].day;
                    }
                }
            }
            if (!exist){ //사진틀에 등록되지 않은 학생인 경우
                if (idx < N){ //새로운 사진 추가 가능한 경우
                    pictures[idx++] = new Picture(c, 1, i);
                }else{ //기존 사진 제거 필요한 경우
                    pictures[minIdx] = new Picture(c, 1, i);
                }
            }
//            System.out.println(minIdx + " " + minVote + " " + maxday);
//            System.out.println(Arrays.toString(pictures));
        }

        for (int i = idx; i<N; i++){ //sort시 nullpointer 방지하기 위해 쓰레기값으로 채우기
            pictures[idx] = new Picture(99999, 99999, 9999);
        }

        if (idx > 1){ //학생 번호순 정렬
            Arrays.sort(pictures, (o1, o2) -> o1.num - o2.num);
        }
        
        for (int i = 0; i<idx; i++){
            System.out.print(pictures[i].num + " ");
        }

        br.close();
    }

    static class Picture{
        int num, cnt, day;
        public Picture(int num, int cnt, int day){
            this.cnt = cnt;
            this.num = num;
            this.day = day;
        }

        @Override
        public String toString() {
            return "Picture{" +
                    "num=" + num +
                    ", cnt=" + cnt +
                    ", day=" + day +
                    '}';
        }
    }
}