
package com.example.lib_communication_context.Core;

import java.util.HashMap;
import com.example.lib_communication_context.Implementations.PersonTypesCommunication;
import com.example.lib_domain_context.ICommunication;
import com.example.lib_domain_context.Enumerables;
import com.example.lib_domain_context.IFactory;

public class FactoryCommunicationContext implements IFactory<ICommunication>
{
    @Override
    public ICommunication Get(HashMap<String, Object> data) 
    {
        Enumerables.Types type = (Enumerables.Types)data.get("Type");
        switch (type)
        {
            case PersonTypes: return new PersonTypesCommunication(data);
            default: return null;
        }
    }    
}