package uz.isystem.forpics_oneword.core.model.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import uz.isystem.forpics_oneword.R;
import uz.isystem.forpics_oneword.core.model.MemoryHelper;

public class CreateDialog extends AlertDialog {

    private final View view;
    private CardView cardView;
    private RadioButton theme1, theme2, theme3;

    public CreateDialog(@NonNull Context context) {

        super(context);

        loadView();

        view = LayoutInflater.from(context).inflate(R.layout.dialog_setting, null, false);
        setView(view);

        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeId(R.drawable.basic11);
//                setThemeData();
            }
        });
        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeId(R.drawable.basic8);
//                setThemeData();

            }
        });
        theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeId(R.drawable.basic7);
//                setThemeData();

            }
        });

    }

    private void loadView() {
        cardView = findViewById(R.id.main_dialog);

        theme1 = findViewById(R.id.theme1);
        theme2 = findViewById(R.id.theme2);
        theme3 = findViewById(R.id.theme3);
    }

}
