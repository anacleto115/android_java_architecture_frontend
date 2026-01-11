
package com.example.lib_utilities.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert 
{
    public static Date StringToDate(String value) throws Exception
    {
        Date date = StringToDate(value, "yyyy-MM-dd HH:mm:ss");
        if (date != null)
            return date;
        date = StringToDate(value, "yyyy-MM-dd'T'HH:mm:ss");
        if (date != null)
            return date;
        date = StringToDate(value, "yyyy-MM-dd");
        return date;
    }
    
    public static Date StringToDate(String value, String format) throws Exception
    {
        Date date = null;
        try
        {
            SimpleDateFormat income = new SimpleDateFormat(format);
            date = income.parse(value);
            return date;
        }
        catch (Exception ex)
        {
            return date;
        }
    }

    public static String DateToString(Date value) throws Exception
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
}
