package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.CaptureListener;
import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class CaptureMatcher implements TokenMatcher {

    private TokenMatcher matcher;
    private CaptureListener listener;

    public CaptureMatcher(TokenMatcher matcher, CaptureListener listener) {
        this.matcher = matcher;
        this.listener = listener;
    }

    @Override
    public boolean match(CharIterator iter) {
        final int startPos = iter.position();
        if (!matcher.match(iter)) {
            return false;
        }
        listener.onMatch(iter.subSequence(startPos));
        return true;
    }

    @Override
    public String toString() {
        return "<capture: " + matcher + ">";
    }
}
