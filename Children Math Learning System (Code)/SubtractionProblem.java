class SubtractionProblem extends MathProblem {
    public void generateProblem() {
        operand1 = (int) (Math.random() * 100);
        operand2 = (int) (Math.random() * 100); // Generate random operand between 0 and 100
        if (operand1 < operand2) { // Ensure operand1 is greater than operand2 for positive result
            int temp = operand1;
            operand1 = operand2;
            operand2 = temp;
        }
        answer = operand1 - operand2;
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == answer; // Check user's answer
    }

    public String getProblemStatement() {
        return operand1 + " - " + operand2 + " = ?"; // Return subtraction problem in readable format
    }
}
