class AdditionProblem extends MathProblem {
    public void generateProblem() {
        operand1 = (int) (Math.random() * 101); // Random number between 0 and 100
        operand2 = (int) (Math.random() * (101 - operand1)); // Random number so operand1 + operand2 <= 100
        answer = operand1 + operand2;
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == answer; // Check user's answer
    }

    public String getProblemStatement() {
        return operand1 + " + " + operand2 + " = ?"; // Return addition problem in readable format
    }
}
