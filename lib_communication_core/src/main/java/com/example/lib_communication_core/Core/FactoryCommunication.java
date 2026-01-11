
package com.example.lib_communication_core.Core;

import java.util.HashMap;
import com.example.lib_domain_context.ICommunication;
import com.example.lib_domain_context.IFactory;

public class FactoryCommunication 
{
    public static IFactory<ICommunication> IFactoryCommunication;

    public static ICommunication Get(HashMap<String, Object> data)
    {
        if (IFactoryCommunication == null)
            return null;

        return IFactoryCommunication.Get(data);
    }
}