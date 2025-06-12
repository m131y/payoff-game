public class NiceUntilTested implements Player {
    private int betrayCount = 0;
    private boolean betrayMode = false;
    final boolean[] opponentHistory;
    int recordIndex = 0;

    public NiceUntilTested(int rounds) {
        this.opponentHistory = new boolean[10];
    }

    @Override
    public boolean cooperate(int round) {
        if (betrayMode) return false; // 배신 모드 들어가면 무조건 배신

        if (round == 1) return true;
        if (round == 2) return opponentHistory[0];

        // 상대가 2번 연속 배신하면 일시 배신
        boolean last = opponentHistory[round - 2];
        boolean prev = opponentHistory[round - 3];

        if (!last && !prev) {
            return false;
        }

        return true;
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
