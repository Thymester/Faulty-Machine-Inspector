package UI;

public class LearnHowPlay {
    public static void howToPlay() {
        System.out.println("""
                           -=-=-=-=-= How to Play =-=-=-=-=-
                           1. Score - You start at 0 and with every machine you get 100 points. If you get a part wrong, you get -5 points, when you 
                           use hint you get -25 points. The maximum score is 700.
                           
                           2. Guessing the Part - To guess the part you must input the name of that part. It is not case sensitive; however, you must
                           give exact spelling of the component that is bad, or you will end up getting points deducted.
                           
                           3. Streak - When you get a right answer without using help, you get +1 to your streak. The maximum streak is 7.
                           
                           4. Gameplay Sequence - The first three machines are with hints built in (you can do away with the help command here). 
                           When you reach machine 4, you now have to remember the parts of the machine and guess which part is out. You can use hints
                           to give you a hint at the problem.
                           -=-=-=-=-= End of How To Play =-=-=-=-=-
                           """);
    }
}