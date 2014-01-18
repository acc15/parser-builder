package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.ParserConfig;
import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
public class HolderMatcher implements TokenMatcher {

    private TokenMatcher matcher;

    HolderMatcher() {
    }

    public void init(TokenMatcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean match(CharIterator iter, ParserConfig config) {
        return matcher.match(iter, config);
    }

    @Override
    public String toString() {
        return matcher != null ? matcher.toString() : "<null holder>";
    }
}
