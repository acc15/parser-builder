package ru.vmsoftware.parser.builder;

import ru.vmsoftware.parser.builder.iterators.CharIterator;
import ru.vmsoftware.parser.builder.iterators.CharSequenceIterator;
import ru.vmsoftware.parser.builder.matchers.TokenMatcher;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-19-01
 */
public class ParserBuilder {

    private CharIterator iter;
    private TokenMatcher matcher;
    private ParserConfig config = new ParserConfig();

    private ParserBuilder(CharIterator iter) {
        this.iter = iter;
    }

    public ParserBuilder skip(TokenMatcher skip) {
        config.setSkip(skip);
        return this;
    }

    public ParserBuilder by(TokenMatcher matcher) {
        this.matcher = matcher;
        return this;
    }

    public boolean match() {
        return matcher.match(iter, config);
    }

    public <T> T getResult() {
        if (!matcher.match(iter, config)) {
            return null;
        }
        return config.getCapture().getValue();
    }

    public static ParserBuilder parse(CharSequence seq) {
        return new ParserBuilder(new CharSequenceIterator(seq));
    }

}
