package ru.vmsoftware.parser.builder.iterators;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-17-01
 */
public interface CharIterator {
    boolean hasChar();
    char getChar();
    void next();
    int position();
    void moveTo(int pos);
    CharSequence subSequence(int startPos);
}
