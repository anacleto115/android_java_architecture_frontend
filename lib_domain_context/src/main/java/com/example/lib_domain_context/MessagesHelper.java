
package com.example.lib_domain_context;

public class MessagesHelper 
{
    public enum Message
    {
        MESSAGE, QUESTION
    }
    
    private static IMessage IMessage;
        
    public static void Set(IMessage iMessage) { IMessage = iMessage; }

    public static Object Show(Object message)
    {
        if (IMessage == null)
            return false;
        return IMessage.Show(message, Message.MESSAGE);
    }
    
    public static Object Show(Object message, Message type)
    {
        if (IMessage == null)
            return false;
        return IMessage.Show(message, type);
    }
}
