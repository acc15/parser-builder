package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class AllMatcher implements TokenMatcher {

    private TokenMatcher[] matchers;

    public AllMatcher(TokenMatcher[] matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean match(CharIterator iter) {
        final int initialPos = iter.position();
        int longestPos = initialPos;
        for (TokenMatcher tm: matchers) {
            if (!tm.match(iter)) {
                return false;
            }
            longestPos = Math.max(longestPos, iter.position());
            iter.moveTo(initialPos);
        }
        iter.moveTo(longestPos);
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (TokenMatcher tm: matchers) {
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(tm);
        }
        return sb.insert(0,'(').append(')').toString();
    }
}
