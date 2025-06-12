public class Alternator implements Player {
    @Override
    public boolean cooperate(int round) {
        // 홀수 라운드 C, 짝수 라운드 D
        return (round % 2 == 1);
    }

    @Override
    public void recordOpponentMove(boolean opponentMove) {
        // 히스토리 불필요
    }
}