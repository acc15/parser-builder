package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class InvertMatcher implements TokenMatcher {

    private TokenMatcher matcher;

    public InvertMatcher(TokenMatcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean match(CharIterator iter) {
        return !matcher.match(iter);
    }

    @Override
    public String toString() {
        return "!(" + matcher + ")";
    }
}
