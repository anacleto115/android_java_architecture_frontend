
package com.example.lib_presentation_core.Core;

import java.util.HashMap;
import com.example.lib_domain_context.IHelper;
import com.example.lib_domain_context.IFactory;

public class FactoryHelper 
{
    public static IFactory<IHelper> IFactoryHelper;

    public static IHelper Get(HashMap<String, Object> data)
    {
        if (IFactoryHelper == null)
            return null;

        return IFactoryHelper.Get(data);
    }
}