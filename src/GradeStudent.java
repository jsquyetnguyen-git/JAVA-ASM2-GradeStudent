import java.util.Scanner;

public class GradeStudent {

    public static void main(String[] args) {
        boolean totalWeight = false;
        double[] midArr = new double[2];
        double[] finalArr = new double[2];
        double[] homeArr = new double[2];
        Scanner cs = new Scanner(System.in);

        // Show outline of program.
        begin();

        do {
            // --MID--
            System.out.println("Midterm:");
            midArr = midterm(cs);
            System.out.println();

            // --FINAL--
            System.out.println("Final:");
            finalArr = finalTerm(cs);
            System.out.println();

            // --HOMEWORK--
            System.out.println("Homework:");
            homeArr = homework(cs);
            System.out.println();

            // Condition of total weight
            if ((midArr[1] + finalArr[1] + homeArr[1]) > 100) {
                System.out.println(
                        "<<NOTICE>> The total weight of midterm, final term, and homework must be 100. Please input again.");
                totalWeight = true;
            }

        } while (totalWeight);

        // --REPORT--
        System.out.println();
        report(midArr[0], finalArr[0], homeArr[0]);
    }

    // Method of showing program outline
    public static void begin() {
        System.out.println("This program reads exam/homework scores\nand reports your overall course grade.\n ");
    }

    // Method of getting and calculating the midterm score.
    public static double[] midterm(Scanner cs) {
        int shiftAmount = 0;

        System.out.print("Weight (0-100)? ");
        int weight = cs.nextInt();

        System.out.print("Score earned? ");
        int midScoreEarned = cs.nextInt();

        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int temp = cs.nextInt();
        if (temp == 1) {
            System.out.print("Shift amount? ");
            shiftAmount = cs.nextInt();
        }

        // show results
        double midTotalPoint = Math.min(shiftAmount + midScoreEarned, 100);
        System.out.println("+ Total points\t = " + (int) midTotalPoint + " / 100");
        double midWeightedScore = (midTotalPoint / 100) * weight;
        System.out.printf("+ Weighted score = %.1f / " + weight + "\n", midWeightedScore);

        return new double[] { midWeightedScore, weight };
    }

    // Method of getting and calculating the finalterm score.
    public static double[] finalTerm(Scanner cs) {
        int shiftAmount = 0;

        System.out.print("Weight (0-100)? ");
        int weight = cs.nextInt();
        System.out.print("Score earned? ");
        int finalScoreEarned = cs.nextInt();

        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int temp = cs.nextInt();
        if (temp == 1) {
            System.out.print("Shift amount? ");
            shiftAmount = cs.nextInt();
        }

        // show results
        double finalTotalPoint = Math.min(shiftAmount + finalScoreEarned, 100);
        System.out.println("+ Total points\t = " + (int) finalTotalPoint + " / 100");
        double finalWeightedScore = (finalTotalPoint / 100) * weight;
        System.out.printf("+ Weighted score = %.1f / " + weight + "\n", finalWeightedScore);

        return new double[] { finalWeightedScore, weight };
    }

    // Method of getting and calculating the homework score.
    public static double[] homework(Scanner cs) {

        System.out.print("Weight (0-100)? ");
        int weight = cs.nextInt();

        System.out.print("Number of assignments? ");
        int numAss = cs.nextInt();

        int totalScore = 0, totalMax = 0;
        for (int i = 1; i <= numAss; i++) {
            System.out.print("Assignment " + i + " score and max?");
            int score = cs.nextInt();
            int max = cs.nextInt();
            totalScore += Math.min(score, 150);
            totalMax += Math.min(max, 150);

        }

        // Section points
        System.out.print("How many sections did you attend? ");
        int sectionNum = cs.nextInt();
        int sectionPoint = Math.min(sectionNum * 5, 30);
        System.out.println("+ Section points = " + sectionPoint + " / 30");

        // Total points
        int totalPoints = totalScore + sectionPoint;
        System.out.println("+ Total points\t = " + totalPoints + " / " + (totalMax + (int) 30));

        // Weighted score

        double homeworkScore = totalPoints / (totalMax + (double) 30) * weight;
        System.out.printf("+ Weighted socre = %.1f / " + weight + "\n", homeworkScore);

        return new double[] { homeworkScore, weight };
    }

    // Method of reporting.
    public static void report(double mids, double finals, double homes) {
        // Overall
        double overallScore = mids + finals + homes;
        System.out.printf("Overall percentage = %.1f", overallScore);
        System.out.println("");

        // GPA and message
        if (overallScore >= 85) {
            System.out.println("Your grade will be at least: 3.0\n[ Good job! You are distinguish. ]");
        } else if (overallScore >= 75 && overallScore <= 84.99) {
            System.out.println("Your grade will be at least: 2.0\n[ Good job! You are awesome. ]");
        } else if (overallScore >= 74.99 && overallScore <= 60) {
            System.out.println("Your grade will be at least: 0.7\n[ Congratulation, You passed! ]");
        } else {
            System.out.println("Your grade will be at least: 0.0\n[ Not pass! Try once again. ]");
        }
    }
}
