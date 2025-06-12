public class TitForNTats implements Player {
    private final boolean[] opponentHistory;
    private final int n;
    private int recordIndex = 0;

    /**
     * @param maxRounds 전체 라운드 수
     * @param n         연속 n회 배신당해야 배신으로 응수
     */
    public TitForNTats(int maxRounds, int n) {
        this.opponentHistory = new boolean[maxRounds];
        this.n = n;
    }

    @Override
    public boolean cooperate(int round) {
        if (round <= n) {
            return true;  // 첫 n라운드는 관대하게 협력
        }
        // 직전 n라운드 모두 배신당했는지 검사
        for (int i = 1; i <= n; i++) {
            if (opponentHistory[round - 1 - i]) {
                // 한 번이라도 협력한 적이 있으면 협력
                return true;
            }
        }
        // n회 연속 배신당했으면 배신
        return false;
    }

    @Override
    public void recordOpponentMove(boolean opponentMove) {
        opponentHistory[recordIndex++] = opponentMove;
    }
}