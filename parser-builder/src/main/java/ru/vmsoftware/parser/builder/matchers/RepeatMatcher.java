package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class RepeatMatcher implements TokenMatcher {

    private int min, max;
    private TokenMatcher matcher;

    public RepeatMatcher(int min, int max, TokenMatcher matcher) {
        this.min = min;
        this.max = max;
        this.matcher = matcher;
    }

    @Override
    public boolean match(CharIterator iter) {
        int matchCount = 0;
        int lastMatchPos = iter.position();
        while ((max < 0 || matchCount < max) && matcher.match(iter)) {
            lastMatchPos = iter.position();
            ++matchCount;
        }
        iter.moveTo(lastMatchPos);
        return matchCount >= min;
    }

    @Override
    public String toString() {
        return "{" + min + "," + (max < 0 ? "*" : max) + "}";
    }
}
