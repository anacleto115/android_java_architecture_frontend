
package com.example.lib_utilities.Utilities;

import java.util.Collection;

public class JLinq
{
    public static <T> JLinqList<T> from(Collection<T> list) throws Exception { return new JLinqList<>(list); }
}