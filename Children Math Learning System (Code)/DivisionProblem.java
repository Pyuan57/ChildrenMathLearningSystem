class DivisionProblem extends MathProblem {
    public void generateProblem() {
        operand2 = (int) (Math.random() * 10) + 1; // Random divisor between 1 and 10 (to avoid division by 0)
        answer = (int) (Math.random() * 12) + 1;  // Random answer between 1 and 12
        operand1 = answer * operand2;
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == answer; // Check user's answer
    }

    public String getProblemStatement() {
        return operand1 + " \u00F7 " + operand2 + " = ?"; // Use Unicode for division to avoid wrong symbol
    }
}
