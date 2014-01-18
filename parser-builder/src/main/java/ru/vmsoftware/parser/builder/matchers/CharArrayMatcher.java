package ru.vmsoftware.parser.builder.matchers;

import java.util.Arrays;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class CharArrayMatcher extends SingleCharMatcher {

    private char[] values;

    public CharArrayMatcher(char[] values) {
        this.values = values;
        Arrays.sort(this.values);
    }

    @Override
    protected boolean charMatches(char ch) {
        return Arrays.binarySearch(values, ch) >= 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (char ch: values) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(ch);
        }
        return sb.insert(0, '[').append(']').toString();
    }
}
