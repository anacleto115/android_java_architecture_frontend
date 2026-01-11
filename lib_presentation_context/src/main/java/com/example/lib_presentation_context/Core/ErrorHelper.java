
package com.example.lib_presentation_context.Core;

import com.example.lib_language.Bundle;

public class ErrorHelper
{
    public static String GetMessage(String key)
    {
        String response = Bundle.Get("RsMessages", key);
        if (response == null ||
            response.equals(""))
            return Bundle.Get("RsMessages", "lbTryAgain");
        return response;
    }
}