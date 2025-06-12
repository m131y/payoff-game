public class Nine implements Player{
    final boolean[] opponentHistory;
    int recordIndex = 0;

    public Nine(int maxRounds){
        this.opponentHistory = new boolean[10];
    }

    @Override
    public boolean cooperate(int round) {
        if(round == 1) {
            return false;
        } else if (round == 10) {
            return false;
        }
        return opponentHistory[round-2]; //round는 1~10, index는 0~9
    }

    @Override
    public void recordOpponentMove(boolean opponentMove) {
        opponentHistory[recordIndex++] = opponentMove;
    }
}
