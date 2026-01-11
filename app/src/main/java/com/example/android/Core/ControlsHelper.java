
package com.example.android.Core;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import com.example.android.MainActivity;
import com.example.lib_utilities.Utilities.Convert;
import java.util.Calendar;
import java.util.Date;
import com.example.lib_utilities.Utilities.Callback;

public class ControlsHelper
{
    public static void DatePickerPopup(Context context, Button ctBorn, Callback<Date> callback)
    {
        ctBorn.setOnClickListener(view ->
        {
            DatePickerDialog.OnDateSetListener setListener = (datePicker, year, month, day) ->
            {
                try
                {
                    String string_date = year + "-" + (month+1) + "-" + day +" HH:mm:ss";
                    Date date = Convert.StringToDate(string_date);
                    if (date == null)
                        return;;
                    callback.Execute(date);
                }
                catch (Exception ex)
                {

                }
            };

            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(context,
                    android.R.style.Theme_Holo_Dialog,
                    setListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
    }

    public static void FilePickerPopup(Context context, Button ctFile, Callback<byte[]> callback)
    {
        ctFile.setOnClickListener(view ->
        {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent = Intent.createChooser(intent, "");
            MainActivity.callback = callback;
            MainActivity.activity.startActivityForResult(intent, 1);
        });
    }

    public interface ISetPopupWindow
    {
        void SetPopupWindow(PopupWindow v);
    }

    public static void FragmentPopup(Context context, Fragment form, Button ctButton)
    {
        ctButton.setOnClickListener(control ->
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = form.onCreateView(inflater, null, null);
            PopupWindow popupWindow = new PopupWindow(view, 800, 800, true);
            popupWindow.setFocusable(false);
            popupWindow.setTouchable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            ((ISetPopupWindow)form).SetPopupWindow(popupWindow);
        });
    }
}