package uz.isystem.forpics_oneword.core.model;

import java.util.ArrayList;
import java.util.Collections;

public class ImageData {
    private String answer;
    private String variant;

    private ArrayList<Integer> images;

    public ImageData() {
    }

    public ImageData(String answer, String variant, ArrayList<Integer> images) {
        this.answer = answer;
        this.variant = variant;
        Collections.shuffle(images);
        this.images = images;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ImageData{" +
                "answer='" + answer + '\'' +
                ", variant='" + variant + '\'' +
                ", images=" + images +
                '}';
    }
}
