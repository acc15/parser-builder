package ru.vmsoftware.parser.builder.matchers;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class CharValueMatcher extends SingleCharMatcher {
    private int value;

    public CharValueMatcher(int value) {
        this.value = value;
    }

    @Override
    protected boolean charMatches(char ch) {
        return ch == value;
    }

    @Override
    public String toString() {
        return Character.toString((char)value);
    }
}
