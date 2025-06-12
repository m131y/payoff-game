public class Pavlov implements Player {
    // Payoff 상수 (Main.java와 동일하게 설정)
    private static final int R = 3; // 양쪽 협력
    private static final int T = 5; // 내가 배신, 상대 협력
    private static final int P = 1; // 양쪽 배신
    private static final int S = 0; // 내가 협력, 상대 배신

    // 마지막 내 선택과 그때 받은 보상을 저장
    private boolean lastMyMove = true;
    private int lastPayoff = R;

    /**
     * @param maxRounds 전체 라운드 수 (Pavlov 알고리즘 자체는 불필요하나
     *                  Main에서 생성자 시그니처를 맞추기 위해 받습니다)
     */
    public Pavlov(int maxRounds) {
        // 전략 유지에 별도 히스토리 배열은 필요 없습니다
    }

    @Override
    public boolean cooperate(int round) {
        if (round == 1) {
            // 첫 라운드는 무조건 협력
            lastMyMove = true;
        } else {
            // “Win‑Stay, Lose‑Shift”
            // 보상 R 또는 P(= 공동 협력 또는 공동 배신)일 때는 현재 전략 유지
            if (lastPayoff == R || lastPayoff == P) {
                // lastMyMove 그대로 유지
            } else {
                // 그 외(내가 협력했으나 상대가 배신해 S를 얻었거나,
                // 내가 배신했으나 상대도 배신해 P를 제외한 T를 얻었을 때)
                // 전략 전환
                lastMyMove = !lastMyMove;
            }
        }
        return lastMyMove;
    }

    @Override
    public void recordOpponentMove(boolean opponentMove) {
        // 직전 라운드 보상을 계산하여 저장
        if (lastMyMove && opponentMove) {
            lastPayoff = R;
        } else if (lastMyMove && !opponentMove) {
            lastPayoff = S;
        } else if (!lastMyMove && opponentMove) {
            lastPayoff = T;
        } else {
            lastPayoff = P;
        }
    }
}