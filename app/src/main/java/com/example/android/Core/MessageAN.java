
package com.example.android.Core;

import com.example.lib_domain_context.IMessage;
import com.example.lib_domain_context.MessagesHelper;

public class MessageAN implements IMessage
{
    @Override
    public Object Show(Object message) 
    {
        return true;
    }

    @Override
    public Object Show(Object message, MessagesHelper.Message type)
    {
        return true;
    }
}