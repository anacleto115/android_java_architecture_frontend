
package com.example.lib_domain_context;

public class LogHelper 
{
    public static ILogHelper ILogHelper;
    
    public static void Log(Exception exception)
    {
        if (ILogHelper == null)
            return;

        ILogHelper.Log(exception, false);
    }

    public static void Log(Exception exception, boolean subError)
    {
        if (ILogHelper == null)
            return;

        ILogHelper.Log(exception, subError);
    }
}