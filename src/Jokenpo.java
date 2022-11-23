import java.util.Random;
import java.util.Scanner;

public record Jokenpo(
        Player user,
        Player IA,
        int rounds
) {

    public void toPlay() {
        System.out.println("\n******* SEJA BEM-VINDO(A), " + user.getName() + " *******\n");

        for (int i = 1; i <= rounds ; i++) {

            int choiceUser = choiceUser();
            if (choiceUser < 1 || choiceUser > 3) {
                System.out.println("\nJOGADA INVÁLIDA! (1, 2 ou 3: ");
                System.out.println("\tPONTO PARA " + IA.getName() + "\n");
                IA.incrementScore();
                //continue;
            }

            int choiceIA = choiceIA();

            System.out.print("\n" + choiceUser + " X " + choiceIA + "\n");

            int result = choiceUser - choiceIA;
            roundWinner(result);

        }

        showFinalResult();
    }

    public void showFinalResult() {
        System.out.println("\n****************************************************************\n");

        Integer finalScoreUser = user.getScore();
        Integer finalScoreIa = IA.getScore();

        System.out.println("\n\tPLACAR FINAL: " + user.getName() + " " + finalScoreUser + " X " + finalScoreIa + " " + IA.getName());

        if (finalScoreUser == finalScoreIa) {
            System.out.println("\t\t\tEMPATE!");
        } else {
            String finalWinner = (finalScoreUser > finalScoreIa) ? user.getName() : IA.getName();
            System.out.println("\t\tVENCEDOR: " + finalWinner.toUpperCase());
        }
    }

    private void roundWinner (int result) {
        String roundWinner;

        if (result == 0) {
            roundWinner = "EMPATE!";
        } else {
            if (result == -1 || result == 2) {
                roundWinner = IA.getName();
                IA.incrementScore();
            } else {
                roundWinner = user.getName();
                user.incrementScore();
            }
        }
    }

    private int choiceIA() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

    private int choiceUser() {
        Scanner scan = new Scanner(System.in);

        System.out.println("1 - PEDRA ");
        System.out.println("2 - PAPEL ");
        System.out.println("3 - TESOURA ");

        System.out.print("Informe sua jogada: ");

        return scan.nextInt();
    }
}
