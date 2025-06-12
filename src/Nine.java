/*
1라운드 협력
2~9라운드 -> TFT하다가 상대 2번연속협력시 배신
10라운드 배신
상대 배신횟수 4회넘어가면 무조건 배신모드
*/

public class Nine implements Player{
    final boolean[] opponentHistory;
    private int betrayCount = 0;
    private boolean betrayMode = false;
    int recordIndex = 0;

    public Nine(int maxRounds){
        this.opponentHistory = new boolean[10];
    }

    @Override
    public boolean cooperate(int round) {
        /*1에는 협력, 10에는 배신
        2는 TFT, 이후 TFT 유지하다 상대 2번 연속 협력시 배신
        */
        if (betrayMode) return false; // 배신 모드 들어가면 무조건 배신
        if(round == 1) {
            return true; //처음엔 협력
        } else if (round == 2) { //2회는 TFT
            return opponentHistory[round-2]; //round는 1~10, index는 0~9
        } else if (round == 10) { //마지막에 배신
            return false;
        }

        boolean last  = opponentHistory[round - 2]; // 직전 라운드
        boolean prev2 = opponentHistory[round - 3]; // 그 전 라운드

        if (last && prev2) { //2번 연속 협력시 배신
            return false;
        }
        return opponentHistory[round-2]; //round는 1~10, index는 0~9

    }

    @Override
    public void recordOpponentMove(boolean opponentMove) {
        opponentHistory[recordIndex++] = opponentMove;

        if (!opponentMove) {
            betrayCount++;
            if (betrayCount >= 4) {
                betrayMode = true; // 4회 이상 배신 시 배신 모드 진입
            }
        }
    }
}
