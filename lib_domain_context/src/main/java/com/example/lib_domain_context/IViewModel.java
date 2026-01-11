
package com.example.lib_domain_context;

import java.util.HashMap;
import java.util.List;
import com.example.lib_domain_context.Enumerables.Action;

public interface IViewModel<T>
{
    void SetCurrent(T v);
    T GetCurrent();
    void SetCurrentCopy(T v);
    T GetCurrentCopy();
    void SetList(List<T> v);
    List<T> GetList();

    HashMap<String, Object> Select(HashMap<String, Object> data);
    HashMap<String, Object> New(HashMap<String, Object> data);
    HashMap<String, Object> Modify(HashMap<String, Object> data);
    HashMap<String, Object> Save(HashMap<String, Object> data);
    HashMap<String, Object> Delete(HashMap<String, Object> data);
    HashMap<String, Object> Close(HashMap<String, Object> data);
}