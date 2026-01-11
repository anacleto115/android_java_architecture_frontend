
package com.example.lib_utilities.Utilities;

public interface ICacheHelper
{
    void Add(String key, Object value);
    void Instance();
    boolean Contains(String key);
    Object Get(String key);
    void Remove(String key);
}