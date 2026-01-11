
package com.example.lib_domain_context;

import java.util.HashMap;
import com.example.lib_domain_context.Enumerables.Loading;

public interface IScreen
{
    void Loading(Loading action);
    void MoveFocus();
    void Change(HashMap<String, Object> data);
}