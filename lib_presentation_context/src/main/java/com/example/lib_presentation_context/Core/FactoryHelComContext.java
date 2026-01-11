
package com.example.lib_presentation_context.Core;

import com.example.lib_presentation_context.Implementations.Helpers.Communications.PersonTypesHelper;
import java.util.HashMap;
import com.example.lib_domain_context.IHelper;
import com.example.lib_domain_context.Enumerables;
import com.example.lib_domain_context.IFactory;

public class FactoryHelComContext implements IFactory<IHelper>
{
    @Override
    public IHelper Get(HashMap<String, Object> data) 
    {
        Enumerables.Types type = (Enumerables.Types)data.get("Type");
        switch (type)
        {
            case PersonTypes: return new PersonTypesHelper(data);
            default: return null;
        }
    }    
}