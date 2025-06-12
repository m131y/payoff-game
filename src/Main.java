public class Main {
    public static void main(String[] args) {
        final int R = 3;  // 둘 다 협력,
        final int T = 5;  // 나는 배신, 상대방 협력
        final int P = 1;  // 나는 배신, 상대방 배신
        final int S = 0;  // 나는 협력, 상대반 배신

        int rounds = 10;

        Player playerA = new Nine(rounds);
        Player playerB = new B();

        int totalA = 0;
        int totalB = 0;

        for (int round = 1; round < rounds; round++) {
            boolean choiceA = playerA.cooperate(round);
            boolean choiceB = playerB.cooperate(round);

            if (choiceA && choiceB) {
                totalA += R;
                totalB += R;
            } else if (choiceA && !choiceB) {
                totalA += S;
                totalB += T;
            } else if (!choiceA && choiceB) {
                totalA += T;
                totalB += S;
            } else {
                totalA += P;
                totalB += P;
            }
            playerA.recordOpponentMove(choiceB);
            playerB.recordOpponentMove(choiceA);
            System.out.printf(
                    "Round %2d: A=%s, B=%s → 점수 A=%d, B=%d%n",
                    round,
                    choiceA ? "협력" : "배신",
                    choiceB ? "협력" : "배신",
                    totalA,
                    totalB
            );
        }

        System.out.println("=== 최종 점수 ===");
        System.out.printf("Player A: %d%nPlayer B: %d%n", totalA, totalB);
    }
}