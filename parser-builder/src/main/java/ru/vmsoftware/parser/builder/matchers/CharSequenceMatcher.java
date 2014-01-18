package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.ParserConfig;
import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
class CharSequenceMatcher implements TokenMatcher {

    private CharSequence charSequence;

    public CharSequenceMatcher(CharSequence charSequence) {
        this.charSequence = charSequence;
    }

    @Override
    public boolean match(CharIterator iter, ParserConfig config) {
        for (int i=0; i<charSequence.length(); i++) {
            final char ch = charSequence.charAt(i);
            if (!iter.hasChar()) {
                return false;
            }
            if (iter.getChar() != ch) {
                return false;
            }
            iter.next();
        }
        return true ;
    }

    @Override
    public String toString() {
        return "\"" + charSequence + "\"";
    }
}
