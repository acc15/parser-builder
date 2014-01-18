package ru.vmsoftware.parser.builder;

import java.util.*;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-19-01
 */
public class CaptureData {

    private final Map<String,List<Object>> values = new HashMap<String, List<Object>>();

    public <T> T getValue() {
        return getValue(null);
    }

    public <T> List<T> getValues() {
        return getValues(null);
    }

    public <T> T getValue(String name) {
        final List<T> list = getValues(name);
        return list.isEmpty() ? null : list.get(0);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getValues(String name) {
        final List<T> list = (List<T>) values.get(name);
        return list != null ? list : Collections.<T>emptyList();
    }

    public void addValue(String name, Object val) {
        List<Object> list = values.get(name);
        if (list == null) {
            list = new ArrayList<Object>();
            values.put(name, list);
        }
        list.add(val);
    }

    public void addValue(Object val) {
        addValue(null, val);
    }



}
