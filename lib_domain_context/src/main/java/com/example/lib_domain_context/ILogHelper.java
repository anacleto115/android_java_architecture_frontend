
package com.example.lib_domain_context;

public interface ILogHelper
{
    void Log(Exception exception);
    void Log(Exception exception, boolean subError);
}