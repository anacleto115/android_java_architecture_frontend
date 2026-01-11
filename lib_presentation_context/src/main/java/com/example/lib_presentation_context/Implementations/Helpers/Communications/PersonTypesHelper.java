
package com.example.lib_presentation_context.Implementations.Helpers.Communications;

import com.example.lib_presentation_context.Core.HelperCommunication;
import com.example.lib_presentation_core.Interfaces.Helpers.IPersonTypesHelper;
import java.util.HashMap;
import com.example.lib_communication_core.Core.FactoryCommunication;
import com.example.lib_domain_entities.Models.PersonTypes;
import com.example.lib_infrastructure.Implementations.PersonTypesParser;
import com.example.lib_domain_context.Enumerables.Types;

public class PersonTypesHelper extends HelperCommunication<PersonTypes> implements IPersonTypesHelper
{
    public PersonTypesHelper(HashMap<String, Object> data)
    {
        super();
    }

    @Override 
    public HashMap<String, Object> Load(HashMap<String, Object> data) 
    {
        data = super.Load(data);
        this.IParser = new PersonTypesParser();
        data.put("Type", Types.PersonTypes);
        if (!data.containsKey("ICommunication"))
            this.ICommunication = this.ICommunication == null ? FactoryCommunication.Get(data) : this.ICommunication;
        data.remove("Type");
        return data;
    }
}