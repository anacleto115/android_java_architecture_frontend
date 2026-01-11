
package com.example.lib_presentation_context.VwModels;

import com.example.lib_presentation_context.Core.VModel;
import java.util.HashMap;
import com.example.lib_domain_context.Enumerables.Types;
import com.example.lib_domain_context.LogHelper;
import com.example.lib_domain_entities.Models.PersonTypes;
import com.example.lib_presentation_core.Core.FactoryHelper;
import com.example.lib_presentation_core.Interfaces.VwModels.IPersonTypesViewModel;

public class PersonTypesViewModel extends VModel<PersonTypes> implements IPersonTypesViewModel
{
    public PersonTypesViewModel(HashMap<String, Object> data)
    {
        super(data);
    }

    @Override
    public HashMap<String, Object> Load(HashMap<String, Object> data)
    {
        try
        {
            data = super.Load(data);
            data.put("Type", Types.PersonTypes);
            if (!data.containsKey("IHelper"))
                IHelper = IHelper == null ? FactoryHelper.Get(data) : IHelper;
        }
        catch (Exception ex)
        {
            LogHelper.Log(ex);
        }
        return data;
    }

    @Override public PersonTypes CreateNew() throws Exception { return new PersonTypes(); }
}