package uz.isystem.forpics_oneword.core.model.manager;

import java.util.ArrayList;
import java.util.Collections;

import uz.isystem.forpics_oneword.core.model.ImageData;

public class GameManager {

    private final int totalQuestion;
    private final int MAX_SCORE = 40;
    private final int DELTA_SCORE = 5;
    private final int MIN_SCORE = 10;
    private final ArrayList<ImageData> data;
    private int currentPosition = 0;
    private int currentLevel = 1;
    private int totalTrues = 0;
    private int currentScore = MAX_SCORE;
    private int totalScore = 0;

    public GameManager(ArrayList<ImageData> data) {
        Collections.shuffle(data);
        this.data = data;
        totalQuestion = data.size();
    }

    private ImageData getCurrentData() {
        return data.get(currentPosition);
    }

    private String getCurrentAnswer() {
        return getCurrentData().getAnswer();
    }

    public int getCurrentAnswerLength() {
        return getCurrentAnswer().length();
    }

    public String getCurrentVariant() {
        return getCurrentData().getVariant();
    }

    public ArrayList<Integer> getCurrentImages() {
        return getCurrentData().getImages();
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public boolean checkAnswer(String answer) {
        boolean isTrue = getCurrentAnswer().equalsIgnoreCase(answer);
        if (isTrue) {
            totalScore += currentScore;
            currentScore = MAX_SCORE;
            currentLevel++;
            totalTrues++;
            if (currentPosition + 1 < totalQuestion) {
                currentPosition++;
            }
        } else {
            if (currentScore > MIN_SCORE) {
                currentScore -= DELTA_SCORE;
            }
        }
        return isTrue;
    }

    public boolean hasData() {
        return currentPosition - 1 < data.size();
    }
}
