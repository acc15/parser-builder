package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.CaptureListener;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
public class MatcherFactory {

    public static int UNBOUNDED = -1;

    public static TokenMatcher capture(CaptureListener listener, TokenMatcher matcher) {
        return new CaptureMatcher(listener, matcher);
    }

    public static TokenMatcher anyChar() {
        return AnyCharMatcher.getInstance();
    }

    public static TokenMatcher any(TokenMatcher... matchers) {
        return new AnyMatcher(matchers);
    }

    public static TokenMatcher all(TokenMatcher... matchers) {
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

    public static TokenMatcher zeroOrOne(TokenMatcher matcher) {
        return new RepeatMatcher(0, 1, matcher);
    }

    public static TokenMatcher oneOrMore(TokenMatcher matcher) {
        return new RepeatMatcher(1, UNBOUNDED, matcher);
    }

    public static TokenMatcher zeroOrMore(TokenMatcher matcher) {
        return new RepeatMatcher(0, UNBOUNDED, matcher);
    }

    public static TokenMatcher sequence(CharSequence charSequence) {
        return new CharSequenceMatcher(charSequence);
    }

    public static TokenMatcher nothing() {
        return NothingMatcher.getInstance();
    }

    public static TokenMatcher sequence(TokenMatcher... matchers) {
        return new SequenceMatcher(matchers);
    }

    public static TokenMatcher tokens(TokenMatcher... tokens) {
        return new SequenceSkipMatcher(tokens);
    }

    public static HolderMatcher holder() {
        return new HolderMatcher();
    }

}
