public class SuspiciousTitForTat implements Player {
    private final boolean[] opponentHistory;
    private int recordIndex = 0;

    public SuspiciousTitForTat(int maxRounds) {
        this.opponentHistory = new boolean[maxRounds];
    }

    @Override
    public boolean cooperate(int round) {
        // 1라운드에 배신 → 이후는 Tit‑for‑Tat
        if (round == 1) {
            return false;
        }
        // 이후엔 상대의 직전 행동 따라 하기
        return opponentHistory[round - 2];
    }

    @Override
    public void recordOpponentMove(boolean opponentMove) {
        opponentHistory[recordIndex++] = opponentMove;
    }
}