
package com.example.lib_utilities.Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JLinqList<T>
{
    private Collection<T> iterable;
    private List<T> listGeneric;

    public JLinqList(Collection<T> iterable) { this.iterable = iterable; }
    public JLinqList(Collection<T> iterable, List<T> listGeneric)
    {
        this.iterable = iterable;
        this.listGeneric = listGeneric;
    }

    private void setList(Collection<T> iterable)
    {
        this.iterable = iterable;
        this.listGeneric = listGeneric;
    }

    public List<T> toList() throws Exception
    {
        if (listGeneric == null)
            listGeneric = new ArrayList<>(iterable);
        return listGeneric;
    }

    public T[] toArray() throws Exception { return (T[])iterable.toArray(); }
    public JLinqList where(Predicate<? super T> expresion) throws Exception
    {
        if (expresion == null)
            return this;

        listGeneric = new ArrayList<>();
        Iterator iterator = iterable.iterator();
        T item = null;
        while (iterator.hasNext())
        {
            item = (T)iterator.next();
            if (expresion.test(item))
                listGeneric.add(item);
        }
        iterator = null;
        this.setList(listGeneric);
        return this; //new JLinqList(listGeneric, listGeneric);
    }

    public List<Boolean> select(Predicate<? super T> expresion) throws Exception
    {
        if (expresion == null)
            return new ArrayList<>();

        List<Boolean> response = new ArrayList<>();
        Iterator iterator = iterable.iterator();
        T item = null;
        while (iterator.hasNext())
        {
            item = (T)iterator.next();
            if (expresion.test(item))
                response.add(true);
            else
                response.add(false);
        }
        iterator = null;
        return response;
    }

    public List orderBy(Comparator<? super T> expresion) throws Exception
    {
        if (expresion == null)
            return listGeneric;

        listGeneric = new ArrayList<>(iterable);
        Collections.sort(listGeneric, expresion);
        return listGeneric;
    }

    public List orderByDesc(Comparator<? super T> expresion) throws Exception
    {
        if (expresion == null)
            return listGeneric;

        listGeneric = new ArrayList<>(iterable);
        Collections.sort(listGeneric, expresion);
        Collections.reverse(listGeneric);
        return listGeneric;
    }

    public List reverse() throws Exception
    {
        Collections.reverse(listGeneric);
        return listGeneric;
    }

    // Any, All, Contains -----------------------------------------------------
    public Boolean all(Predicate<? super T> expresion) throws Exception
    {
        if (expresion == null)
            return false;

        List<Boolean> response = new ArrayList<>();
        Iterator iterator = iterable.iterator();
        T item = null;
        while (iterator.hasNext())
        {
            item = (T)iterator.next();
            if (!expresion.test(item))
            {
                iterator = null;
                return false;
            }
        }
        iterator = null;
        return true;
    }

    public Boolean constain(T obj) throws Exception
    {
        listGeneric = new ArrayList<>(iterable);
        listGeneric = listGeneric.stream().filter(x -> x == obj).collect(Collectors.toList());
        return false;
    }

    public Boolean any(Predicate<? super T> expresion) throws Exception
    {
        if (expresion == null)
            return false;

        List<Boolean> response = new ArrayList<>();
        Iterator iterator = iterable.iterator();
        T item = null;
        while (iterator.hasNext())
        {
            item = (T)iterator.next();
            if (expresion.test(item))
            {
                iterator = null;
                return true;
            }
        }
        iterator = null;
        return false;
    }

    // Join -------------------------------------------------------------------
    public JLinqList join(Function<? super T, ? extends Stream<? extends T>> expression) throws Exception
    {
        listGeneric = new ArrayList<>(iterable);
        listGeneric = listGeneric.stream().flatMap(expression).collect(Collectors.toList());
        this.setList(listGeneric);
        return this; // new JLinqList(listGeneric, listGeneric);
    }

    // GroupBy, ToLookup ------------------------------------------------------
    public Map<Object, Long> groupBy(Function<? super T, ? extends Object> expresion) throws Exception { return toLookup(expresion); }

    public Map<Object, Long> toLookup(Function<? super T, ? extends Object> expresion) throws Exception
    {
        listGeneric = new ArrayList<>(iterable);
        return listGeneric.stream().collect(Collectors.groupingBy(expresion, Collectors.counting()));
    }

    // Take, Skip, TakeWhile, SkipWhile ---------------------------------------
    public JLinqList take(int size) throws Exception
    {
        listGeneric = new ArrayList<>();
        Iterator iterator = iterable.iterator();
        T item = null;
        int count = 0;
        while (iterator.hasNext())
        {
            count++;
            item = (T)iterator.next();
            listGeneric.add(item);
            if (count >= size)
            {
                iterator = null;
                return new JLinqList(listGeneric, listGeneric);
            }
        }
        iterator = null;
        this.setList(listGeneric);
        return this; // new JLinqList(listGeneric, listGeneric);
    }

    public JLinqList skip(int size) throws Exception
    {
        listGeneric = new ArrayList<>();
        Iterator iterator = iterable.iterator();
        T item = null;
        int count = 0;
        while (iterator.hasNext())
        {
            count++;
            item = (T)iterator.next();
            if (count <= size)
                continue;
            listGeneric.add(item);
        }
        iterator = null;
        this.setList(listGeneric);
        return this; // new JLinqList(listGeneric, listGeneric);
    }

    public JLinqList takeWhile(Predicate<? super T> expresion) throws Exception { return where(expresion); }

    public JLinqList skipWhile(Predicate<? super T> expresion) throws Exception
    {
        if (expresion == null)
            return this;

        listGeneric = new ArrayList<>();
        Iterator iterator = iterable.iterator();
        T item = null;
        while (iterator.hasNext())
        {
            item = (T)iterator.next();
            if (!expresion.test(item))
                listGeneric.add(item);
        }
        iterator = null;
        this.setList(listGeneric);
        return this; // new JLinqList(listGeneric, listGeneric);
    }

    // Distinct, Union, Intersect, Except -------------------------------------
    public JLinqList union(List<T> list) throws Exception
    {
        if (list == null)
            return this;

        listGeneric = new ArrayList<>(iterable);
        listGeneric.addAll(list);
        this.setList(listGeneric);
        return this; //new JLinqList(listGeneric, listGeneric);
    }

    public JLinqList intersect(List<T> list) throws Exception
    {
        if (list == null)
            return this;

        listGeneric = new ArrayList<>(iterable);
        listGeneric.retainAll(list);
        this.setList(listGeneric);
        return this; // new JLinqList(listGeneric, listGeneric);
    }

    public JLinqList distinct() throws Exception
    {
        listGeneric = new ArrayList<>(iterable);
        listGeneric = listGeneric.stream().distinct().collect(Collectors.toList());
        this.setList(listGeneric);
        return this; // new JLinqList(listGeneric, listGeneric);
    }

    public JLinqList except(List<T> list) throws Exception
    {
        if (list == null)
            return this;

        listGeneric = new ArrayList<>(iterable);
        listGeneric.removeAll(list);
        this.setList(listGeneric);
        return this; // new JLinqList(listGeneric, listGeneric);
    }

    // First, FirstOrDefault, Last, LastOrDefault, ElementAt, ElementAtOrDefault
    // Single, SingleOrDefault -------------------------------------------------
    public T first() throws Exception { return first(null); }
    public T firstOrDefault() throws Exception { return first(null); }
    public T firstOrDefault(Predicate<? super T> expresion) throws Exception { return first(expresion); }
    public T first(Predicate<? super T> expresion) throws Exception
    {
        listGeneric = new ArrayList<>();
        Iterator iterator = iterable.iterator();
        T item = null;
        while (iterator.hasNext())
        {
            item = (T)iterator.next();
            if (expresion == null || expresion.test(item))
            {
                iterator = null;
                return item;
            }
        }
        iterator = null;
        return null;
    }

    public T last() throws Exception { return last(null); }
    public T lastOrDefault() throws Exception { return last(null); }
    public T lastOrDefault(Predicate<? super T> expresion) throws Exception { return last(expresion); }
    public T last(Predicate<? super T> expresion) throws Exception
    {
        listGeneric = new ArrayList<>(iterable);
        Collections.reverse(listGeneric);
        Iterator iterator = listGeneric.iterator();
        T item = null;
        while (iterator.hasNext())
        {
            item = (T)iterator.next();
            if (expresion == null || expresion.test(item))
            {
                iterator = null;
                return item;
            }
        }
        iterator = null;
        return item;
    }

    public T elementAtOrDefault(int position) throws Exception { return elementAt(position); }
    public T elementAt(int position) throws Exception
    {
        listGeneric = new ArrayList<>(iterable);
        if (position > listGeneric.size())
            return null;
        return listGeneric.get(position);
    }

    public T single() throws Exception { return single(null); }
    public T singleOrDefault() throws Exception { return single(null); }
    public T singleOrDefault(Predicate<? super T> expresion) throws Exception { return single(expresion); }
    public T single(Predicate<? super T> expresion) throws Exception
    {
        List<T> temp = where(expresion).toList();
        if (temp.size() > 1)
            throw new Exception("The list has more than one item");
        return temp.get(0);
    }

    // Count, Sum, Min, Max, Average, Aggregate ---------------------------------
    public int count() throws Exception { return count(null); }
    public int count(Predicate<? super T> expresion) throws Exception
    {
        if (expresion == null)
            return iterable.size();
        listGeneric = this.where(expresion).toList();
        return listGeneric.size();
    }

    public int sum(ToIntFunction<? super T> expresion) throws Exception
    {
        if (expresion == null)
            throw new Exception("The expresion is null");

        listGeneric = new ArrayList<>(iterable);
        return listGeneric.stream().mapToInt(expresion).sum();
    }

    public int min(ToIntFunction<? super T> expresion) throws Exception
    {
        if (expresion == null)
            throw new Exception("The expresion is null");

        listGeneric = new ArrayList<>(iterable);
        return listGeneric.stream().mapToInt(expresion).min().getAsInt();
    }

    public int max(ToIntFunction<? super T> expresion) throws Exception
    {
        if (expresion == null)
            throw new Exception("The expresion is null");

        listGeneric = new ArrayList<>(iterable);
        return listGeneric.stream().mapToInt(expresion).max().getAsInt();
    }

    public double average(ToIntFunction<? super T> expresion) throws Exception
    {
        if (expresion == null)
            throw new Exception("The expresion is null");

        listGeneric = new ArrayList<>(iterable);
        return listGeneric.stream().mapToInt(expresion).average().getAsDouble();
    }

    // AsEnumerable, ToDictionary, Cast<TResult> ---------------
    public List<T> cast() throws Exception  { return toList(); }

    public Collection<T> asEnumerable() throws Exception  { return iterable; }

    public Map<String, T> toDictionary(Function<? super T, ? extends String> expresion) throws Exception
    {
        listGeneric = new ArrayList<>(iterable);
        return listGeneric.stream().collect(Collectors.toMap(expresion, Function.identity()));
    }
}