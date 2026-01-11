
package com.example.lib_domain_context;

import java.util.HashMap;

public interface IFactory<T>
{
    T Get(HashMap<String, Object> data);   
}