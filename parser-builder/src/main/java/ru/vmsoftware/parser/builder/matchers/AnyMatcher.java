package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.ParserConfig;
import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class AnyMatcher implements TokenMatcher {

    private TokenMatcher[] matchers;

    public AnyMatcher(TokenMatcher[] matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean match(CharIterator iter, ParserConfig config) {
        final int initialPos = iter.position();
        for (TokenMatcher tm: matchers) {
            if (tm.match(iter, config)) {
                return true;
            }
            iter.moveTo(initialPos);
        }
        return false;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (TokenMatcher tm: matchers) {
            if (sb.length() > 0) {
                sb.append('|');
            }
            sb.append(tm);
        }
        return sb.insert(0,'(').append(')').toString();
    }
}
