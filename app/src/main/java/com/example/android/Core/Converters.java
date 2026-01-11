
package com.example.android.Core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import com.example.lib_utilities.Utilities.EncodingHelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converters
{
    public static String ConverterBool(boolean value)
    {
        try
        {
            if (value == true)
                return com.example.lib_language.Bundle.Get("RsMessages", "lbActive");
            return com.example.lib_language.Bundle.Get("RsMessages", "lbInactive");
        }
        catch (Exception ex)
        {
            return com.example.lib_language.Bundle.Get("RsMessages", "lbInactive");
        }
    }

    public static String ConverterDate(Date value)
    {
        String result = "";
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = dateFormat.format(value);
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public static void ConverterImage(String value, ImageView image)
    {
        try
        {
            if (value == null || value.equals(""))
                return;
            byte[] data = EncodingHelper.ToBytes(value);
            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            image.setImageBitmap(bmp);
        }
        catch (Exception ex)
        {

        }
    }
}