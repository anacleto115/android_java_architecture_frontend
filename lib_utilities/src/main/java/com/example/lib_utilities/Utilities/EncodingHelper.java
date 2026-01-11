
package com.example.lib_utilities.Utilities;

import android.os.Build;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodingHelper
{
    public static String ToString(byte[] data)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            return Base64.getEncoder().encodeToString(data);
        return new String(data, StandardCharsets.US_ASCII);
    }

    public static byte[] ToBytes(String data)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            return Base64.getDecoder().decode(data);
        return data.getBytes(StandardCharsets.US_ASCII);
    }
}