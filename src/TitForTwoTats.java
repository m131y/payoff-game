public class TitForTwoTats implements Player {
    private final boolean[] opponentHistory;
    private int recordIndex = 0;

    /**
     * @param maxRounds 전체 라운드 수
     */
    public TitForTwoTats(int maxRounds) {
        this.opponentHistory = new boolean[maxRounds];
        // 배열 기본값은 false
    }

    @Override
    public boolean cooperate(int round) {
        // 첫 두 라운드는 무조건 협력
        if (round <= 2) {
            return true;
        }

        // 직전 두 라운드에서 모두 배신했으면 이번 라운드에 배신
        boolean last  = opponentHistory[round - 2]; // 직전 라운드
        boolean prev2 = opponentHistory[round - 3]; // 그 전 라운드

        if (!last && !prev2) {
            return false;
        }

        // 그 외의 경우에는 협력
        return true;
    }

    @Override
    public void recordOpponentMove(boolean opponentMove) {
        // 호출 순서대로 배열에 기록
        opponentHistory[recordIndex++] = opponentMove;
    }
}