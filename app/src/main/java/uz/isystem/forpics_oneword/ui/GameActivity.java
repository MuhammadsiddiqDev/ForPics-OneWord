package uz.isystem.forpics_oneword.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import uz.isystem.forpics_oneword.R;
import uz.isystem.forpics_oneword.core.model.ImageData;
import uz.isystem.forpics_oneword.core.model.manager.CreateDialogIsWin;
import uz.isystem.forpics_oneword.core.model.manager.GameManager;

public class GameActivity extends AppCompatActivity {

    StringBuilder answerBuilder = new StringBuilder();
    private RelativeLayout imageGroup, variantGroup;
    private LinearLayout answerGroup;
    private ArrayList<ImageData> data;
    private GameManager manager;
    private TextView currentLevelView, totalScoreView, deltaView;
    private Button clearButton, strongHelpButton, weakHelpButton;
    private Button buttonCancel, buttonOk;
    private int countSing = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        loadViews();
        loadData();

        startGame();

        loadAction();
    }

    private void startGame() {
        clearView();
        if (manager.hasData()) {
            loadToViews();
        } else {
            Toast.makeText(this, "Game over", Toast.LENGTH_LONG).show();
        }
    }

    private void clearView() {
        for (int i = 0; i < variantGroup.getChildCount(); i++) {
            Button button = (Button) variantGroup.getChildAt(i);
            button.setVisibility(View.VISIBLE);
            button.setText("");
        }
        for (int i = 0; i < answerGroup.getChildCount(); i++) {
            Button button = (Button) answerGroup.getChildAt(i);

            button.setVisibility(View.GONE);
            button.setText("");
        }

        answerBuilder.delete(0, answerBuilder.length());
        countSing = 0;
        enableVariantButtons();
    }

    private void loadAction() {

        for (int i = 0; i < variantGroup.getChildCount(); i++) {
            Button button = (Button) variantGroup.getChildAt(i);
            button.setOnClickListener(this::setVariantClick);
        }
        for (int i = 0; i < answerGroup.getChildCount(); i++) {
            Button button = (Button) answerGroup.getChildAt(i);
            button.setOnClickListener(this::onAnswerClick);
        }

        clearButton.setOnClickListener(this::onClearButtonClick);
        strongHelpButton.setOnClickListener(this::onStrongHelpClick);
        weakHelpButton.setOnClickListener(this::onWeakHelpClick);
    }

    private void onOkClick(View view) {

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void onCancelClick(View view) {

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void onWeakHelpClick(View view) {
    }

    private void onStrongHelpClick(View view) {
    }

    private void onClearButtonClick(View view) {

        countSing = 0;

        for (int i = 0; i < answerGroup.getChildCount(); i++) {
            Button buttonAnswer = (Button) answerGroup.getChildAt(i);
            if (buttonAnswer.getVisibility() == View.VISIBLE && buttonAnswer.getText().toString().length() != 0) {
                String answerText = buttonAnswer.getText().toString();
                for (int j = 0; j < variantGroup.getChildCount(); j++) {
                    Button buttonVariant = (Button) variantGroup.getChildAt(j);
                    if (buttonVariant.getText().toString().equalsIgnoreCase(answerText) && buttonVariant.getVisibility() == View.INVISIBLE) {
                        buttonVariant.setVisibility(View.VISIBLE);
                        buttonAnswer.setText("");
                        break;
                    }
                }
            }
        }
    }

    public void setVariantClick(View view) {
        Button button = (Button) view;

        for (int i = 0; i < answerGroup.getChildCount(); i++) {
            Button answer = (Button) answerGroup.getChildAt(i);
            if (answer.getText().toString().isEmpty() && answer.getVisibility() == View.VISIBLE) {
                answer.setText(button.getText());
                countSing++;
                button.setVisibility(View.INVISIBLE);
                break;
            }
        }
        if (countSing == manager.getCurrentAnswerLength()) {
            for (int i = 0; i < answerGroup.getChildCount(); i++) {
                Button answer = (Button) answerGroup.getChildAt(i);
                String textAnswer = answer.getText().toString();
                if (!textAnswer.isEmpty()) {
                    answerBuilder.append(textAnswer);
                }
            }
            int allAnswers = manager.getCurrentLevel();

            boolean isWin = manager.checkAnswer(answerBuilder.toString());
            if (isWin) {

                if (allAnswers == data.size()) {
                    CreateDialogIsWin dialogIsWin = new CreateDialogIsWin(GameActivity.this);
                    dialogIsWin.show();
                }

                startGame();
            } else {
                disableVariantButtons();
                loadIndicators();

                Toast.makeText(this, "Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void disableVariantButtons() {

        for (int i = 0; i < variantGroup.getChildCount(); i++) {
            Button variant = (Button) variantGroup.getChildAt(i);
            variant.setEnabled(false);
        }
    }

    private void enableVariantButtons() {
        for (int i = 0; i < variantGroup.getChildCount(); i++) {
            Button variant = (Button) variantGroup.getChildAt(i);
            variant.setEnabled(true);
        }
    }

    private void loadIndicators() {
        deltaView.setText("\uD83E\uDE99 +" + manager.getCurrentScore());
    }

    public void onAnswerClick(View view) {
        enableVariantButtons();
        Button button = (Button) view;

        String answerText = button.getText().toString();

        for (int i = 0; i < variantGroup.getChildCount(); i++) {
            Button variant = (Button) variantGroup.getChildAt(i);
            if (answerText.equalsIgnoreCase(variant.getText().toString()) && variant.getVisibility() == View.INVISIBLE) {
                variant.setVisibility(View.VISIBLE);
                countSing--;
                answerBuilder.delete(0, answerBuilder.length());
                break;
            }
        }
        button.setText("");
    }

    private void loadToViews() {

        ArrayList<Integer> images = manager.getCurrentImages();

        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = (ImageView) imageGroup.getChildAt(i);
            imageView.setImageResource(images.get(i));
        }
        currentLevelView.setText("Level:  " + manager.getCurrentLevel());
        totalScoreView.setText("\uD83D\uDCB0 " + manager.getTotalScore());
        deltaView.setText("\uD83E\uDE99 +" + manager.getCurrentScore());

        int totalVariantLength = manager.getCurrentAnswerLength();

        for (int i = 0; i < answerGroup.getChildCount(); i++) {
            Button button = (Button) answerGroup.getChildAt(i);
            if (i < totalVariantLength) {
                button.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.GONE);
            }
            button.setText("");
        }

        String variantText = manager.getCurrentVariant();

        for (int i = 0; i < variantGroup.getChildCount(); i++) {
            Button button = (Button) variantGroup.getChildAt(i);
            button.setText(variantText.charAt(i) + "");
            button.setVisibility(View.VISIBLE);
        }

    }

    private void loadData() {
        data = new ArrayList<>();

        ArrayList<Integer> image1 = new ArrayList<>();
        image1.add(R.drawable.photo_1_1);
        image1.add(R.drawable.photo_1_2);
        image1.add(R.drawable.photo_1_3);
        image1.add(R.drawable.photo_1_4);

        data.add(new ImageData(
                "fruits", "stfruoeiptsc", image1
        ));
        ArrayList<Integer> image2 = new ArrayList<>();
        image2.add(R.drawable.photo_2_1);
        image2.add(R.drawable.photo_2_2);
        image2.add(R.drawable.photo_2_3);
        image2.add(R.drawable.photo_2_4);
        data.add(new ImageData(
                "hotel", "shohtealuodf", image2
        ));
        ArrayList<Integer> image3 = new ArrayList<>();
        image3.add(R.drawable.photo_3_1);
        image3.add(R.drawable.photo_3_2);
        image3.add(R.drawable.photo_3_3);
        image3.add(R.drawable.photo_3_4);
        data.add(new ImageData(
                "city", "bcnwirguytld", image3
        ));

        ArrayList<Integer> image4 = new ArrayList<>();
        image4.add(R.drawable.photo_4_1);
        image4.add(R.drawable.photo_4_2);
        image4.add(R.drawable.photo_4_3);
        image4.add(R.drawable.photo_4_4);
        data.add(new ImageData(
                "life", "lvaiafdeskor", image4
        ));

        ArrayList<Integer> image5 = new ArrayList<>();
        image5.add(R.drawable.photo_5_1);
        image5.add(R.drawable.photo_5_2);
        image5.add(R.drawable.photo_5_3);
        image5.add(R.drawable.photo_5_4);
        data.add(new ImageData(
                "child", "cbhayiglbuda", image5
        ));

        ArrayList<Integer> image6 = new ArrayList<>();
        image6.add(R.drawable.photo_6_1);
        image6.add(R.drawable.photo_6_2);
        image6.add(R.drawable.photo_6_3);
        image6.add(R.drawable.photo_6_4);
        data.add(new ImageData(
                "secret", "ishecarethpa", image6
        ));

        ArrayList<Integer> image7 = new ArrayList<>();
        image7.add(R.drawable.photo_7_1);
        image7.add(R.drawable.photo_7_2);
        image7.add(R.drawable.photo_7_3);
        image7.add(R.drawable.photo_7_4);
        data.add(new ImageData(
                "photo", "spihocemtgoa", image7
        ));

        ArrayList<Integer> image8 = new ArrayList<>();
        image8.add(R.drawable.photo_8_1);
        image8.add(R.drawable.photo_8_2);
        image8.add(R.drawable.photo_8_3);
        image8.add(R.drawable.photo_8_4);
        data.add(new ImageData(
                "forest", "iftorweoysdt", image8
        ));

        ArrayList<Integer> image9 = new ArrayList<>();
        image9.add(R.drawable.photo_9_1);
        image9.add(R.drawable.photo_9_2);
        image9.add(R.drawable.photo_9_3);
        image9.add(R.drawable.photo_9_4);
        data.add(new ImageData(
                "knife", "tkcsoniofked", image9
        ));

        ArrayList<Integer> image10 = new ArrayList<>();
        image10.add(R.drawable.photo_10_1);
        image10.add(R.drawable.photo_10_2);
        image10.add(R.drawable.photo_10_3);
        image10.add(R.drawable.photo_10_4);
        data.add(new ImageData(
                "famous", "orfasnmaotus", image10
        ));
        manager = new GameManager(data);

        ArrayList<Integer> image11 = new ArrayList<>();
        image11.add(R.drawable.photo_11_1);
        image11.add(R.drawable.photo_11_2);
        image11.add(R.drawable.photo_11_3);
        image11.add(R.drawable.photo_11_4);
        data.add(new ImageData(
                "sun", "dswhutenrclo", image11
        ));
        manager = new GameManager(data);

        ArrayList<Integer> image12 = new ArrayList<>();
        image12.add(R.drawable.photo_12_1);
        image12.add(R.drawable.photo_12_2);
        image12.add(R.drawable.photo_12_3);
        image12.add(R.drawable.photo_12_4);
        data.add(new ImageData(
                "drive", "adocfvuaitre", image12
        ));
        manager = new GameManager(data);

    }

    private void loadViews() {
        buttonCancel = findViewById(R.id.button_cancel);
        buttonOk = findViewById(R.id.button_ok);

        imageGroup = findViewById(R.id.image_group);
        variantGroup = findViewById(R.id.variant_group);
        answerGroup = findViewById(R.id.answer_group);

        clearButton = findViewById(R.id.clear_button);
        strongHelpButton = findViewById(R.id.strong_help_button);
        weakHelpButton = findViewById(R.id.weak_help_button);

        currentLevelView = findViewById(R.id.current_level);
        totalScoreView = findViewById(R.id.total_score);
        deltaView = findViewById(R.id.delta_score);
    }
}