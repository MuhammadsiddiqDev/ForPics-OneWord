package uz.isystem.forpics_oneword.core.model.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import uz.isystem.forpics_oneword.R;

public class CreateDialogIsWin extends AlertDialog {

    View view;

    public CreateDialogIsWin(@NonNull Context context) {
        super(context);

        view = LayoutInflater.from(context).inflate(R.layout.dialog_iswin, null, false);
        setView(view);
    }
}
