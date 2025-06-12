public class PeriodicDefector implements Player {
    private final int k;

    /**
     * @param k k의 배수 라운드에서만 배신
     */
    public PeriodicDefector(int k) {
        this.k = k;
    }

    @Override
    public boolean cooperate(int round) {
        return (round % k != 0);
    }

    @Override
    public void recordOpponentMove(boolean opponentMove) {
        // 히스토리 불필요
    }
}