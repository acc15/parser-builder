package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.ParserConfig;
import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class SequenceSkipMatcher implements TokenMatcher {

    private TokenMatcher[] matchers;

    public SequenceSkipMatcher(TokenMatcher[] matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean match(CharIterator iter, ParserConfig config) {
        for (TokenMatcher matcher: matchers) {
            final int startPos = iter.position();
            if (matcher.match(iter, config)) {
                continue;
            }
            iter.moveTo(startPos);
            if (!config.getSkip().match(iter, config)) {
                return false;
            }
            if (!matcher.match(iter, config)) {
                return false;
            }
        }
        config.getSkip().match(iter, config);
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (TokenMatcher tm: matchers) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(tm);
        }
        return sb.insert(0, '{').append('}').toString();
    }
}
