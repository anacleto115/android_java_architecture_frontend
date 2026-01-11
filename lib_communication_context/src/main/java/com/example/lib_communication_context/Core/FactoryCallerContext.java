
package com.example.lib_communication_context.Core;

import com.example.lib_domain_context.ICaller;
import com.example.lib_domain_context.IFactory;
import java.util.HashMap;

public class FactoryCallerContext implements IFactory<ICaller>
{
    @Override
    public ICaller Get(HashMap<String, Object> data)
    {
        return new CallerServices();
    }
}
