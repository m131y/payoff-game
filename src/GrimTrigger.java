public class GrimTrigger implements Player {
    private final boolean[] opponentHistory;
    private int recordIndex = 0;
    private boolean betrayedBefore = false;

    /**
     * @param maxRounds 전체 라운드 수
     */
    public GrimTrigger(int maxRounds) {
        this.opponentHistory = new boolean[maxRounds];
        // 배열 기본값은 false
    }

    @Override
    public boolean cooperate(int round) {
        // 이미 한 번이라도 배신당한 적이 있으면 영원히 배신
        if (betrayedBefore) {
            return false;
        }

        // 2라운드 이후에 상대가 직전 라운드에 배신했다면
        if (round > 1 && !opponentHistory[round - 2]) {
            betrayedBefore = true;
            return false;
        }

        // 그 외에는 협력
        return true;
    }

    @Override
    public void recordOpponentMove(boolean opponentMove) {
        // 호출 순서대로 배열에 기록
        opponentHistory[recordIndex++] = opponentMove;
    }
}