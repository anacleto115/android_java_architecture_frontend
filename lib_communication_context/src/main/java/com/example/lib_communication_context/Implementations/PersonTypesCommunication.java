
package com.example.lib_communication_context.Implementations;

import java.util.HashMap;
import com.example.lib_communication_context.Core.Communication;
import com.example.lib_communication_core.Interfaces.IPersonTypesCommunication;

public class PersonTypesCommunication extends Communication implements IPersonTypesCommunication
{
    public PersonTypesCommunication(HashMap<String, Object> data)
    {
        super();
    }

    @Override public HashMap<String, Object> Load(HashMap<String, Object> data)
    {
        data = super.Load(data);
        Service = "srw_persons";
        Name = "PersonTypes";
        return data;
    }
}