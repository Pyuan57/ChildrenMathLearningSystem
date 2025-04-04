import java.util.*;

abstract class MathProblem {
    protected int operand1, operand2, answer;

    public abstract void generateProblem(); // Method to generate the problem

    public abstract boolean checkAnswer(int userAnswer); // Method to check if the user's answer is correct

    public abstract String getProblemStatement(); // Method to return the problem in a string format
}
