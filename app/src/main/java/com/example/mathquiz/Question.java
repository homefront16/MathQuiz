package com.example.mathquiz;

import java.util.Random;

public class Question {

    private int firstNumber;
    private int secondNumber;
    private int answer;

    // There are four possible choices for the user to pick
    private int[] answerArray;

    // which of the four positions is correct, 0, 1, 2, 3
    private int answerPosition;

    // The maximum value of firstNumber or secondNumber
    private int upperLimit;

    // String output of the problem. eg "4 + 9 = "
    private String questionPhrase;

    public Question(int upperLimit){
        this.upperLimit = upperLimit;
        Random randomNumber = new Random();

        this.firstNumber = randomNumber.nextInt(upperLimit);
        this.secondNumber = randomNumber.nextInt(upperLimit);
        this.answer = this.firstNumber + this.secondNumber;
        this.questionPhrase = firstNumber + " + " + secondNumber + " = ";

        this.answerPosition = randomNumber.nextInt(4);
        this.answerArray = new int[] {0, 1, 2, 3};

        this.answerArray[0] = answer + 1;
        this.answerArray[1] = answer + 10;
        this.answerArray[2] = answer - 5;
        this.answerArray[3] = answer - 2;

        this.answerArray = shuffleArray(this.answerArray);

        answerArray[answerPosition] = answer;

    }

    private int[] shuffleArray(int[] answerArray){
        int index, temp;
        Random randomNumber = new Random();
        for(int i = answerArray.length - 1; i < 0; i--){
            index = randomNumber.nextInt(i + 1);
            temp = answerArray[index];
            answerArray[index] = answerArray[i];
            answerArray[i] = temp;
        }
        return answerArray;
    }
    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }
}
