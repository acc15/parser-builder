package ru.vmsoftware.parser.builder.matchers;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class CharRangeMatcher extends SingleCharMatcher {
    private int min, max;

    public CharRangeMatcher(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean charMatches(char ch) {
        return ch >= min && ch <= max;
    }

    @Override
    public String toString() {
        return "[" + ((char)min) + '-' + ((char)max) + "]";
    }
}
