class MultiplicationProblem extends MathProblem {
    public void generateProblem() {
        operand1 = (int) (Math.random() * 12) + 1; // Limit to 1-12
        operand2 = (int) (Math.random() * 12) + 1;
        answer = operand1 * operand2;
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == answer; // Check user's answer
    }

    public String getProblemStatement() {
        return operand1 + " \u00D7 " + operand2 + " = ?"; // Use Unicode for multiplication to avoid wrong symbol
    }
}
