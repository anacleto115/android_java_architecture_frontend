
package com.example.lib_domain_context;

import com.example.lib_domain_context.MessagesHelper.Message;

public interface IMessage 
{
    Object Show(Object message);
    Object Show(Object message, Message type);
}