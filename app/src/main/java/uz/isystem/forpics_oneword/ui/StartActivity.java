package uz.isystem.forpics_oneword.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import uz.isystem.forpics_oneword.R;
import uz.isystem.forpics_oneword.core.model.manager.CreateDialog;

public class StartActivity extends AppCompatActivity {

    private Button button, infoButton, settingButton;
    private View infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        infoButton = findViewById(R.id.info_button);
        infoView = findViewById(R.id.info_view);
        settingButton = findViewById(R.id.setting_button);

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateDialog dialog = new CreateDialog(StartActivity.this);
                dialog.show();
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoView.setVisibility(View.VISIBLE);
            }
        });

        infoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoView.setVisibility(View.GONE);
            }
        });

        button = findViewById(R.id.start_game);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}