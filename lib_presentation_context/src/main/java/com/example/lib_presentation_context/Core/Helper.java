
package com.example.lib_presentation_context.Core;

import com.example.lib_domain_context.IConfiguration;
import com.example.lib_domain_context.IParser;

import java.util.HashMap;

public class Helper<T>
{
    protected IParser<T> IParser;
    protected IConfiguration IConfiguration;

    public HashMap<String, Object> Load(HashMap<String, Object> data) 
    {
        data.put("Client", "Default");
        return data;
    }
}