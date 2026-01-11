
package com.example.lib_domain_entities.Models;

import com.example.lib_domain_context.IEntities;

public class PersonTypes implements IEntities
{
    private int _Id;

    public void SetId(int v) { _Id = v; }
    public int GetId() { return _Id; }
    
    private String _Name;
    
    public void SetName(String v) { _Name = v; }
    public String GetName() { return _Name; }

    @Override public int Get_Id() { return _Id; }
    @Override public Object GetClone()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException ex)
        {
            return null;
        }
    }
}