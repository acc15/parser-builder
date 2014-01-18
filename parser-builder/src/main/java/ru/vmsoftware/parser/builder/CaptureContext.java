package ru.vmsoftware.parser.builder;

import java.util.List;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-19-01
 */
public interface CaptureContext {

    CharSequence getCapture();
    <T> T getValue();
    <T> List<T> getValues();
    <T> T getValue(String name);
    <T> List<T> getValues(String name);
    void addValue(String name, Object e);
    void addValue(Object e);

}
