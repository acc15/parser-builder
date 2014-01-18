package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.ParserConfig;
import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class NothingMatcher implements TokenMatcher {

    public static NothingMatcher getInstance() {
        return instance;
    }

    private NothingMatcher()

    {
    }

    private static final NothingMatcher instance = new NothingMatcher();
    @Override
    public boolean match(CharIterator iter, ParserConfig config) {
        return false;
    }

    @Override
    public String toString() {
        return "<nothing>";
    }
}
