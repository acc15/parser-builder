package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.CaptureListener;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
public class MatcherFactory {

    public static int UNBOUNDED = -1;

    public static TokenMatcher capture(CaptureListener listener, TokenMatcher matcher) {
        return new CaptureMatcher(matcher, listener);
    }

    public static TokenMatcher anyChar() {
        return AnyCharMatcher.getInstance();
    }

    public static TokenMatcher anyMatch(TokenMatcher... matchers) {
        return new AnyMatcher(matchers);
    }

    public static TokenMatcher allMatch(TokenMatcher... matchers) {
        return new AllMatcher(matchers);
    }

    public static TokenMatcher charValue(int ch) {
        return new CharValueMatcher(ch);
    }

    public static TokenMatcher charRange(int min, int max) {
        return new CharRangeMatcher(min, max);
    }

    public static TokenMatcher charInArray(char... chars) {
        return new CharArrayMatcher(chars);
    }

    public static TokenMatcher invert(TokenMatcher matcher) {
        return new InvertMatcher(matcher);
    }

    public static TokenMatcher repeat(int min, int max, TokenMatcher matcher) {
        return new RepeatMatcher(min, max, matcher);
    }

    public static TokenMatcher sequence(CharSequence charSequence) {
        return new CharSequenceMatcher(charSequence);
    }

    public static TokenMatcher nothing() {
        return NothingMatcher.getInstance();
    }

    public static TokenMatcher sequenceWithSkip(TokenMatcher skip, TokenMatcher... matchers) {
        return new SequenceMatcher(skip, matchers);
    }

    public static TokenMatcher sequence(TokenMatcher... matchers) {
        return new SequenceMatcher(nothing(), matchers);
    }

}
