package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.ParserConfig;
import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
abstract class SingleCharMatcher implements TokenMatcher {

    protected abstract boolean charMatches(char ch);

    @Override
    public boolean match(CharIterator iter, ParserConfig config) {
        if (iter.hasChar() && charMatches(iter.getChar())) {
            iter.next();
            return true;
        }
        return false;
    }
}
