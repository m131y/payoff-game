public class Prober implements Player {
    private final boolean[] opponentHistory;
    private int recordIndex = 0;
    private boolean exploitMode = false;

    public Prober(int maxRounds) {
        this.opponentHistory = new boolean[maxRounds];
    }

    @Override
    public boolean cooperate(int round) {
        // 1–4라운드: D, C, C, C
        if (round == 1)    return false;
        if (round >= 2 && round <= 4) return true;

        // 5라운드 직전까지 모두 협력했으면 영구 배신
        if (round == 5) {
            if (opponentHistory[1] && opponentHistory[2] && opponentHistory[3]) {
                exploitMode = true;
            }
        }
        if (exploitMode) {
            return false;
        }
        // 그 외에는 Tit‑for‑Tat
        return opponentHistory[round - 2];
    }

    @Override
    public void recordOpponentMove(boolean opponentMove) {
        opponentHistory[recordIndex++] = opponentMove;
    }
}