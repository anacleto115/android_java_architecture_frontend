
package com.example.lib_presentation_context.Core;

import java.util.HashMap;
import com.example.lib_domain_context.Enumerables;
import com.example.lib_domain_context.IHelper;
import com.example.lib_domain_context.IFactory;

public class FactoryHelperContext implements IFactory<IHelper>
{
    //private FactoryHelAppContext FactoryHelAppContext = new FactoryHelAppContext();
    private FactoryHelComContext FactoryHelComContext = new FactoryHelComContext();

    @Override
    public IHelper Get(HashMap<String, Object> data) 
    {
        //if (data.containsKey("Architecture") &&
        //    (Enumerables.Architecture)data.get("Architecture") == Enumerables.Architecture.StandAlone)
        //    return FactoryHelAppContext.Get(data);
        return FactoryHelComContext.Get(data);
    }
}