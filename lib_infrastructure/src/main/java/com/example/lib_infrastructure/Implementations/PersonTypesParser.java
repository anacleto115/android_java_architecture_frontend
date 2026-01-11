
package com.example.lib_infrastructure.Implementations;

import android.database.Cursor;
import java.util.HashMap;
import com.example.lib_domain_entities.Models.PersonTypes;
import com.example.lib_domain_context.IParser;

public class PersonTypesParser implements IParser<PersonTypes>
{
    @Override
    public PersonTypes CreateEntity(Object ItemArray) throws Exception
    {
        Cursor resultSet = (Cursor)ItemArray;
        
        PersonTypes entity = new PersonTypes();
        entity.SetId(resultSet.getInt(0));
        entity.SetName(resultSet.getString(1));
        return entity;
    }

    @Override
    public PersonTypes ToEntity(HashMap<String, Object> data) throws Exception
    {
        PersonTypes entity = new PersonTypes();
        entity.SetId(Integer.parseInt(data.get("Id").toString())); 
        if (data.containsKey("Name"))      
            entity.SetName(data.get("Name").toString());    
        return entity;
    }

    @Override
    public HashMap<String, Object> ToDictionary(PersonTypes entity) throws Exception 
    {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("Id", String.valueOf(entity.GetId()));
        if (entity.GetName() != null && !entity.GetName().equals(""))      
            data.put("Name", String.valueOf(entity.GetName())); 
        return data;
    }

    @Override
    public boolean Validate(PersonTypes entity) throws Exception
    {
        if (entity.GetName() == null || entity.GetName().equals(""))
            return false;
        return true;
    }
}