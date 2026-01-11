
package com.example.lib_domain_context;

import java.util.HashMap;

public interface IParser<T>
{
    T CreateEntity(Object ItemArray) throws Exception;
    T ToEntity(HashMap<String, Object> data) throws Exception;
    HashMap<String, Object> ToDictionary(T entity) throws Exception;
    boolean Validate(T entity) throws Exception;
}