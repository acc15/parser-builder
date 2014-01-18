package ru.vmsoftware.parser.builder.matchers;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class AnyCharMatcher extends SingleCharMatcher {


    public static AnyCharMatcher getInstance() {
        return instance;
    }

    private AnyCharMatcher() {
    }

    private static final AnyCharMatcher instance = new AnyCharMatcher();

    @Override
    protected boolean charMatches(char ch) {
        return true;
    }

    @Override
    public String toString() {
        return "<any>";
    }
}
