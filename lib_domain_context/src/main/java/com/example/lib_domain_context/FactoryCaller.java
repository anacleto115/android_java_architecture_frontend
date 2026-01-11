
package com.example.lib_domain_context;

import java.util.HashMap;

public class FactoryCaller
{
    public static IFactory<ICaller> IFactoryCaller;

    public static ICaller Get(HashMap<String, Object> data)
    {
        if (IFactoryCaller == null)
            return null;

        return IFactoryCaller.Get(data);
    }
}