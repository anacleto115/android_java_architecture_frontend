
package com.example.lib_domain_context;

import java.util.HashMap;

public interface IHelper 
{
    HashMap<String, Object> Load(HashMap<String, Object> data);
    HashMap<String, Object> Select(HashMap<String, Object> data);
    HashMap<String, Object> Insert(HashMap<String, Object> data);
    HashMap<String, Object> Update(HashMap<String, Object> data);
    HashMap<String, Object> Delete(HashMap<String, Object> data);
}